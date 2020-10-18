package com.suweleh.android.hilt.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.suweleh.android.hilt.R
import com.suweleh.android.hilt.schema.UserSchema

class UserListAdapter : ListAdapter<UserSchema, UserListViewHolder>(
    UserListDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.v_user_item, parent, false)
        return UserListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
