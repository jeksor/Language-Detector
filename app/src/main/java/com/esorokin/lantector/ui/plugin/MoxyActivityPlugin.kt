package com.esorokin.lantector.ui.plugin

import android.app.Activity
import android.os.Bundle
import com.arellomobile.mvp.MvpDelegate
import com.esorokin.lantector.ui.plugin.base.BasePlugin

class MoxyActivityPlugin(private val activity: Activity) : BasePlugin() {
    private val mvpDelegate: MvpDelegate<Activity> by lazy {
        MvpDelegate<Activity>(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvpDelegate.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        mvpDelegate.onAttach()
    }

    override fun onResume() {
        super.onResume()
        mvpDelegate.onAttach()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mvpDelegate.onSaveInstanceState(outState)
        mvpDelegate.onDetach()
    }

    override fun onStop() {
        super.onStop()
        mvpDelegate.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
        mvpDelegate.onDestroyView()

        if (activity.isFinishing) {
            mvpDelegate.onDestroy()
        }
    }
}