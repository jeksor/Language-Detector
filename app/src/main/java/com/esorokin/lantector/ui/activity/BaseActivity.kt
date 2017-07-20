package com.esorokin.lantector.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.esorokin.lantector.ui.plugin.MoxyActivityPlugin
import com.esorokin.lantector.ui.plugin.base.CompositionPluginDelegate

abstract class BaseActivity : AppCompatActivity() {
    protected val compositionPlugin = CompositionPluginDelegate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPlugins()
        compositionPlugin.onCreate(savedInstanceState)
    }

    /**
     * Attach your plugins here.
     */
    protected open fun initPlugins() {
        compositionPlugin.attach(MoxyActivityPlugin(this))
    }

    override fun onStart() {
        super.onStart()
        compositionPlugin.onStart()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        compositionPlugin.onRestoreInstanceState(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        compositionPlugin.onResume()
    }

    override fun onContentChanged() {
        super.onContentChanged()
        compositionPlugin.onViewCreated(window.decorView)
    }

    override fun onPause() {
        super.onPause()
        compositionPlugin.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        compositionPlugin.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        compositionPlugin.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositionPlugin.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        compositionPlugin.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        compositionPlugin.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        compositionPlugin.onBackPressed()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        compositionPlugin.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
