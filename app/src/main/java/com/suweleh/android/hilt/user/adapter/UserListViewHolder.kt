package com.suweleh.android.hilt.user.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.suweleh.android.hilt.schema.UserSchema
import kotlinx.android.synthetic.main.v_user_item.view.*

class UserListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(userSchema: UserSchema) {
        view.txtTitle.text = userSchema.title
        view.txtBody.text = userSchema.body
    }
}
