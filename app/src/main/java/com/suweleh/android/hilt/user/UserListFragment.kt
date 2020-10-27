package com.suweleh.android.hilt.user

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.suweleh.android.hilt.R
import com.suweleh.android.hilt.mvi.MviView
import com.suweleh.android.hilt.user.adapter.UserListAdapter
import dagger.android.support.AndroidSupportInjection
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_list.*
import javax.inject.Inject

@AndroidEntryPoint
class UserListFragment : Fragment(), MviView<UserListViewState> {

    companion object {
        const val TAG = "UserListFragment"

        fun createInstance(): UserListFragment {
            return UserListFragment()
        }
    }

    private val viewModel: UserListViewModel by viewModels()

    private lateinit var adapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onStart() {
        super.onStart()
        viewModel.viewState.observe(this, Observer {
            render(it)
        })
        loadData(false)
    }

    private fun loadData(isPullToRefresh: Boolean) {
        viewModel.dispatch(UserListAction.FetchUserListAction(isPullToRefresh))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchText()
        setAdapter()
        setSwipeRefresh()
    }

    private fun setSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            loadData(true)
        }
    }

    private fun setSearchText() {
        searchText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.dispatch(UserListAction.SearchTitleAction(it))
                }
                return false
            }
        })
    }

    private fun setAdapter() {
        adapter = UserListAdapter()
        userListRecyclerView.layoutManager = LinearLayoutManager(
            requireContext()
        )
        val dividerItemDecoration = DividerItemDecoration(
            userListRecyclerView.context,
            (userListRecyclerView.layoutManager as LinearLayoutManager).orientation
        )
        userListRecyclerView.addItemDecoration(dividerItemDecoration)
        userListRecyclerView.adapter = adapter
    }

    override fun render(state: UserListViewState) {
        if (state.isLoading) {
            if (state.isPullToRefresh) {
                swipeRefreshLayout.isRefreshing = true
            }
        } else {
            layoutNotFound.visibility = View.GONE
            userListRecyclerView.visibility = View.VISIBLE
            swipeRefreshLayout.isRefreshing = false
            adapter.submitList(state.list)
            if (state.list.isEmpty()) {
                layoutNotFound.visibility = View.VISIBLE
                userListRecyclerView.visibility = View.GONE
            }
        }
    }
}
