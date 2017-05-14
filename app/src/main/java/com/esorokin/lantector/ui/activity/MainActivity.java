package com.esorokin.lantector.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.esorokin.lantector.R;
import com.esorokin.lantector.databinding.ActivityMainBinding;
import com.esorokin.lantector.presentation.navigation.AppScreen;
import com.esorokin.lantector.presentation.navigation.TabsInfo;
import com.esorokin.lantector.presentation.presenter.MainPresenter;
import com.esorokin.lantector.presentation.view.MainView;
import com.esorokin.lantector.ui.plugins.SwitchFragmentNavigationPlugin;
import com.esorokin.lantector.ui.plugins.ToolbarPlugin;

public class MainActivity extends BaseActivity implements MainView, NavigationView.OnNavigationItemSelectedListener, SwitchFragmentNavigationPlugin.ScreenSwitchListener {
	@InjectPresenter
	MainPresenter presenter;

	private TabsInfo tabsInfo = new TabsInfo();
	private ActivityMainBinding viewBinding;

	@Override
	protected void initPlugins() {
		super.initPlugins();
		compositionPlugin().attach(new ToolbarPlugin(this));
		compositionPlugin().attach(new SwitchFragmentNavigationPlugin(this, R.id.container, tabsInfo, this));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		initTabsInfo();

		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, viewBinding.drawerLayout, viewBinding.header.toolbar, R.string.drawer_open, R.string.drawer_close);

		viewBinding.drawerLayout.addDrawerListener(drawerToggle);
		drawerToggle.syncState();

		viewBinding.navigationView.setNavigationItemSelectedListener(this);
	}

	private void initTabsInfo() {
		tabsInfo.add(DETECT_LANGUAGE_SCREEN_NAME, R.id.detectLanguageTab, new AppScreen.DetectLanguage());
		tabsInfo.add(HISTORY_SCREEN_NAME, R.id.historyTab, new AppScreen.History());
	}

	@Override
	public void onScreenSwitched(String screenName) {
		viewBinding.navigationView.getMenu().findItem(tabsInfo.getTabId(screenName)).setChecked(true);
	}

	@Override
	public void onBackPressed() {
		if (viewBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
			viewBinding.drawerLayout.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		presenter.userSelectTab(tabsInfo.getScreenName(item.getItemId()));
		return true;
	}

	@Override
	public void openDrawer() {
		viewBinding.drawerLayout.openDrawer(GravityCompat.START);
	}

	@Override
	public void closeDrawer() {
		viewBinding.drawerLayout.closeDrawer(GravityCompat.START);
	}
}
