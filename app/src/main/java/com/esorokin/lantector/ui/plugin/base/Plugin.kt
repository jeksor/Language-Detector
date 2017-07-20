package com.esorokin.lantector.ui.plugin.base

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View

interface Plugin {
    fun onCreate(savedInstanceState: Bundle?)

    fun onViewCreated(view: View)

    fun onStart()

    fun onRestoreInstanceState(savedInstanceState: Bundle?)

    fun onResume()

    fun onPause()

    fun onSaveInstanceState(outState: Bundle?)

    fun onStop()

    fun onDestroy()

    fun onOptionsItemSelected(item: MenuItem)

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

    fun onBackPressed()
}