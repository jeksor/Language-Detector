package com.esorokin.lantector.presentation.navigation

import com.art.alligator.Screen

class TabsInfo {
    private val tabs: MutableMap<String, Tab> = mutableMapOf()

    fun add(screenName: String, tabId: Int, screen: Screen) {
        if (tabs.containsKey(screenName)) {
            throw IllegalArgumentException("Duplicate screen name")
        }

        tabs.put(screenName, Tab(tabId, screen))
    }

    fun getTabId(screenName: String): Int {
        val tab = tabs[screenName] ?: throw IllegalArgumentException("Unknown screen name " + screenName)
        return tab.id
    }

    fun getScreen(screenName: String): Screen {
        val tab = tabs[screenName] ?: throw IllegalArgumentException("Unknown screen name " + screenName)
        return tab.screen
    }

    fun getScreenName(tabId: Int): String {
        for ((key, tab) in tabs) {
            if (tab.id == tabId) {
                return key
            }
        }
        throw IllegalArgumentException("Unknown tab id " + tabId)
    }

    private class Tab internal constructor(internal val id: Int, internal val screen: Screen)
}
