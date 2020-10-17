package com.suweleh.android.hilt.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.suweleh.android.hilt.R

class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, UserListFragment.createInstance(), UserListFragment.TAG)
            .commit()
    }
}
