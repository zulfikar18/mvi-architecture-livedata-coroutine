package com.suweleh.android.hilt.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.suweleh.android.hilt.R
import com.suweleh.android.hilt.mvi.MviView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_user_list.*
import javax.inject.Inject

class UserListFragment : Fragment(), MviView<UserListViewState> {

    companion object {
        const val TAG = "UserListFragment"

        fun createInstance(): UserListFragment {
            return UserListFragment()
        }
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var viewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(UserListViewModel::class.java)
    }

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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dispatch(UserListAction.FetchUserListAction)
    }

    @SuppressLint("SetTextI18n")
    override fun render(state: UserListViewState) {
        if (state.list.isNotEmpty()) {
            state.list.map {
                it.title
            }.forEach {
                mainText.text = "${mainText.text}\n$it"
            }
        }
    }
}
