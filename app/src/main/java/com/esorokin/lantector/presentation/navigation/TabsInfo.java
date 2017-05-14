package com.esorokin.lantector.presentation.navigation;

import java.util.HashMap;
import java.util.Map;

import com.art.alligator.Screen;

public class TabsInfo {
	private Map<String, Tab> tabs = new HashMap<>();

	public void add(String screenName, int tabId, Screen screen) {
		if (tabs.containsKey(screenName)) {
			throw new IllegalArgumentException("Duplicate screen name");
		}

		tabs.put(screenName, new Tab(tabId, screen));
	}

	public int getTabId(String screenName) {
		Tab tab = tabs.get(screenName);
		if (tab == null) {
			throw new IllegalArgumentException("Unknown screen name " + screenName);
		}
		return tab.getId();
	}

	public Screen getScreen(String screenName) {
		Tab tab = tabs.get(screenName);
		if (tab == null) {
			throw new IllegalArgumentException("Unknown screen name " + screenName);
		}
		return tab.getScreen();
	}

	public String getScreenName(int tabId) {
		for (Map.Entry<String, Tab> entry : tabs.entrySet()) {
			Tab tab = entry.getValue();
			if (tab.getId() == tabId) {
				return entry.getKey();
			}
		}
		throw new IllegalArgumentException("Unknown tab id " + tabId);
	}

	private static class Tab {
		private int mId;
		private Screen mScreen;

		Tab(int id, Screen screen) {
			mId = id;
			mScreen = screen;
		}

		int getId() {
			return mId;
		}

		Screen getScreen() {
			return mScreen;
		}
	}
}
