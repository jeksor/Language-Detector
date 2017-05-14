package com.esorokin.lantector.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.esorokin.lantector.ui.plugins.base.CompositionPlugin;
import com.esorokin.lantector.ui.plugins.base.CompositionPluginDelegate;

public class BaseActivity extends MvpAppCompatActivity {
	private final CompositionPlugin compositionPlugin = new CompositionPluginDelegate();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initPlugins();
		compositionPlugin.onCreate();
	}

	/**
	 * Attach your plugins here.
	 */
	protected void initPlugins() {
		//override
	}

	protected CompositionPlugin compositionPlugin() {
		return compositionPlugin;
	}

	@Override
	protected void onStart() {
		super.onStart();
		compositionPlugin.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		compositionPlugin.onResume();
	}

	@Override
	public void onContentChanged() {
		super.onContentChanged();
		compositionPlugin.onViewCreated(getWindow().getDecorView());
	}

	@Override
	protected void onPause() {
		super.onPause();
		compositionPlugin.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
		compositionPlugin.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		compositionPlugin.onDestroy();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		compositionPlugin.onOptionsItemSelected(item);
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		compositionPlugin.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		compositionPlugin.onBackPressed();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		compositionPlugin.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}
}
