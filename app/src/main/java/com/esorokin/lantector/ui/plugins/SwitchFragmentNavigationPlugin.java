package com.esorokin.lantector.ui.plugins;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.art.alligator.NavigationContext;
import com.art.alligator.NavigationContextBinder;
import com.art.alligator.NavigationFactory;
import com.art.alligator.ScreenSwitcher;
import com.art.alligator.screenswitchers.FragmentScreenSwitcher;
import com.esorokin.lantector.di.DependencyManager;
import com.esorokin.lantector.presentation.navigation.TabsInfo;
import com.esorokin.lantector.ui.plugins.base.BasePlugin;

import javax.inject.Inject;

public class SwitchFragmentNavigationPlugin extends BasePlugin {
	@Inject
	NavigationContextBinder navigationContextBinder;

	@Inject
	NavigationFactory navigationFactory;

	private final AppCompatActivity activity;
	private final int containerId;
	private final TabsInfo tabsInfo;
	private final ScreenSwitchListener screenSwitchListener;

	public SwitchFragmentNavigationPlugin(AppCompatActivity activity, @IdRes int containerId, TabsInfo tabsInfo, ScreenSwitchListener screenSwitchListener) {
		DependencyManager.getAppComponent().inject(this);
		this.activity = activity;
		this.containerId = containerId;
		this.tabsInfo = tabsInfo;
		this.screenSwitchListener = screenSwitchListener;
	}

	public SwitchFragmentNavigationPlugin(AppCompatActivity activity, @IdRes int containerId, TabsInfo tabsInfo) {
		this(activity, containerId, tabsInfo, null);
	}

	@Override
	public void onResume() {
		super.onResume();
		navigationContextBinder.bind(new NavigationContext.Builder(activity)
				.screenSwitcher(createFragmentSwitcher())
				.build());
	}

	private ScreenSwitcher createFragmentSwitcher() {
		return new FragmentScreenSwitcher(activity.getSupportFragmentManager(), containerId) {
			@Override
			protected Fragment createFragment(String screenName) {
				return navigationFactory.createFragment(tabsInfo.getScreen(screenName));
			}

			@Override
			protected void onScreenSwitched(String screenName) {
				super.onScreenSwitched(screenName);
				if (screenSwitchListener != null) {
					screenSwitchListener.onScreenSwitched(screenName);
				}
			}
		};
	}

	public interface ScreenSwitchListener {
		void onScreenSwitched(String screenName);
	}
}
