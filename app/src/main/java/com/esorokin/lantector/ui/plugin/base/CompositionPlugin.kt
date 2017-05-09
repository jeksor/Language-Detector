package com.esorokin.lantector.ui.plugin.base

interface CompositionPlugin : Plugin {
    fun <T : Plugin> attach(plugin: T): T

    fun detach(plugin: Plugin)
}
