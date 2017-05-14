package com.esorokin.lantector.ui.plugins;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class BackPressedToolbarPlugin extends ToolbarPlugin {
	@NonNull
	private final BackClickListener backClickListener;

	public BackPressedToolbarPlugin(AppCompatActivity dependency, @StringRes int titleRes, @NonNull BackClickListener backClickListener) {
		this(dependency, dependency.getString(titleRes), backClickListener);
	}

	public BackPressedToolbarPlugin(AppCompatActivity dependency, @NonNull String title, @NonNull BackClickListener backClickListener) {
		super(dependency, title);
		this.backClickListener = backClickListener;
	}

	public BackPressedToolbarPlugin(AppCompatActivity dependency, @NonNull BackClickListener backClickListener) {
		super(dependency);
		this.backClickListener = backClickListener;
	}

	@Override
	public void onViewCreated(final View view) {
		super.onViewCreated(view);
		ActionBar supportActionBar = getDependency().getSupportActionBar();
		if (supportActionBar != null) {
			supportActionBar.setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public void onOptionsItemSelected(MenuItem item) {
		if (item != null && item.getItemId() == android.R.id.home) {
			onBackPressed();
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		backClickListener.onBackPressed();
	}

	public interface BackClickListener {
		void onBackPressed();
	}
}
