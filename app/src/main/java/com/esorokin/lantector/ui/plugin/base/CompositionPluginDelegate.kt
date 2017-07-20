package com.esorokin.lantector.ui.plugin.base

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View

class CompositionPluginDelegate : CompositionPlugin {
    var plugins: MutableList<Plugin> = mutableListOf()

    override fun <T : Plugin> attach(plugin: T): T = plugin.also { plugins.add(it) }

    override fun detach(plugin: Plugin) {
        plugins.remove(plugin)
    }

    override fun onCreate(savedInstanceState: Bundle?) = plugins.forEach { it.onCreate(savedInstanceState) }

    override fun onViewCreated(view: View) = plugins.forEach { it.onViewCreated(view) }

    override fun onStart() = plugins.forEach { it.onStart() }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) = plugins.forEach { it.onRestoreInstanceState(savedInstanceState) }

    override fun onResume() = plugins.forEach { it.onResume() }

    override fun onPause() = plugins.forEach { it.onPause() }

    override fun onSaveInstanceState(outState: Bundle?) = plugins.forEach { it.onSaveInstanceState(outState) }

    override fun onStop() = plugins.forEach { it.onStop() }

    override fun onDestroy() = plugins.forEach { it.onDestroy() }

    override fun onOptionsItemSelected(item: MenuItem) = plugins.forEach { it.onOptionsItemSelected(item) }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) = plugins.forEach { it.onRequestPermissionsResult(requestCode, permissions, grantResults) }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) = plugins.forEach { it.onActivityResult(requestCode, resultCode, data) }

    override fun onBackPressed() = plugins.forEach { it.onBackPressed() }
}