package com.esorokin.lantector.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View

import com.arellomobile.mvp.MvpAppCompatFragment
import com.esorokin.lantector.ui.plugin.base.CompositionPluginDelegate

open class BaseFragment : MvpAppCompatFragment() {
    protected val compositionPlugin = CompositionPluginDelegate()

    /**
     * Attach your plugins here.
     */
    protected open fun initPlugins() {
        //override
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPlugins()
        compositionPlugin.onCreate()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compositionPlugin.onViewCreated(view!!)
    }

    override fun onStart() {
        super.onStart()
        compositionPlugin.onStart()
    }

    override fun onResume() {
        super.onResume()
        compositionPlugin.onResume()
    }

    override fun onPause() {
        super.onPause()
        compositionPlugin.onPause()
    }

    override fun onStop() {
        super.onStop()
        compositionPlugin.onStop()
    }


    override fun onDestroy() {
        super.onDestroy()
        compositionPlugin.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        compositionPlugin.onActivityResult(requestCode, resultCode, data)
    }
}
