package com.suweleh.android.hilt.user.adapter

import androidx.recyclerview.widget.DiffUtil
import com.suweleh.android.hilt.schema.UserSchema

class UserListDiffCallback : DiffUtil.ItemCallback<UserSchema>() {
    override fun areItemsTheSame(oldItem: UserSchema, newItem: UserSchema): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserSchema, newItem: UserSchema): Boolean {
        return oldItem == newItem
    }
}
