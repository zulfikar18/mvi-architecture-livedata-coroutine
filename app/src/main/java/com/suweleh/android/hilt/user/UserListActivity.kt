package com.suweleh.android.hilt.user

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.suweleh.android.hilt.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setLayoutFullScreen()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, UserListFragment.createInstance(), UserListFragment.TAG)
            .commit()
    }

    private fun setLayoutFullScreen() {
        window?.decorView?.apply {
            systemUiVisibility = if (atLeastIsJB()) {
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
        }
    }

    private fun atLeastIsJB() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
}
