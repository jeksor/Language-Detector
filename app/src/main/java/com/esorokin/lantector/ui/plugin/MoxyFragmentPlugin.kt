package com.esorokin.lantector.ui.plugin

import android.os.Bundle
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpDelegate
import com.esorokin.lantector.ui.plugin.base.BasePlugin

class MoxyFragmentPlugin(private val fragment: Fragment) : BasePlugin() {
    private val mvpDelegate: MvpDelegate<Fragment> by lazy {
        MvpDelegate<Fragment>(fragment)
    }

    private var isStateSaved: Boolean = false

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
        //We leave the screen and respectively all fragments will be destroyed
        if (fragment.activity.isFinishing) {
            mvpDelegate.onDestroy()
            return
        }

        // When we rotate device isRemoving() return true for fragment placed in backstack
        // http://stackoverflow.com/questions/34649126/fragment-back-stack-and-isremoving
        if (isStateSaved) {
            isStateSaved = false
            return
        }

        // See https://github.com/Arello-Mobile/Moxy/issues/24
        var anyParentIsRemoving = false
        var parent: Fragment? = fragment.parentFragment
        while (!anyParentIsRemoving && parent != null) {
            anyParentIsRemoving = parent.isRemoving
            parent = parent.parentFragment
        }

        if (fragment.isRemoving || anyParentIsRemoving) {
            mvpDelegate.onDestroy()
        }
    }
}