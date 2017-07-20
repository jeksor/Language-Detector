package com.esorokin.lantector.ui.plugin.base

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View

open class BasePlugin : Plugin {
    override fun onCreate(savedInstanceState: Bundle?) {
        //override
    }

    override fun onViewCreated(view: View) {
        //override
    }

    override fun onStart() {
        //override
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        //override
    }

    override fun onResume() {
        //override
    }

    override fun onPause() {
        //override
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        //override
    }

    override fun onStop() {
        //override
    }

    override fun onDestroy() {
        //override
    }

    override fun onOptionsItemSelected(item: MenuItem) {
        //override
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        //override
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //override
    }

    override fun onBackPressed() {
        //override
    }
}