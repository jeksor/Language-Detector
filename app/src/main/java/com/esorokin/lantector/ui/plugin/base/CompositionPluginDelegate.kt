package com.esorokin.lantector.ui.plugin.base

import android.content.Intent
import android.view.MenuItem
import android.view.View

class CompositionPluginDelegate : CompositionPlugin {
    var plugins: MutableList<Plugin> = mutableListOf()

    override fun <T : Plugin> attach(plugin: T): T = plugin.also { plugins.add(it) }

    override fun detach(plugin: Plugin) {
        plugins.remove(plugin)
    }

    override fun onCreate() {
        for (plugin in plugins) {
            plugin.onCreate()
        }
    }

    override fun onViewCreated(view: View) {
        for (plugin in plugins) {
            plugin.onViewCreated(view)
        }
    }

    override fun onStart() {
        for (plugin in plugins) {
            plugin.onStart()
        }
    }

    override fun onResume() {
        for (plugin in plugins) {
            plugin.onResume()
        }
    }

    override fun onPause() {
        for (plugin in plugins) {
            plugin.onPause()
        }
    }

    override fun onStop() {
        for (plugin in plugins) {
            plugin.onStop()
        }
    }

    override fun onDestroy() {
        for (plugin in plugins) {
            plugin.onDestroy()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) {
        for (plugin in plugins) {
            plugin.onOptionsItemSelected(item)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        for (plugin in plugins) {
            plugin.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        for (plugin in plugins) {
            plugin.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onBackPressed() {
        for (plugin in plugins) {
            plugin.onBackPressed()
        }
    }
}