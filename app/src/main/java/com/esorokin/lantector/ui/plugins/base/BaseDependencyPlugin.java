package com.esorokin.lantector.ui.plugins.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;

public class BaseDependencyPlugin<T> extends DependencyPlugin<T> {
	public BaseDependencyPlugin(T dependency) {
		super(dependency);
	}

	@Override
	public void onCreate() {
		//override
	}

	@Override
	public void onViewCreated(final View view) {
		//override
	}

	@Override
	public void onStart() {
		//override
	}

	@Override
	public void onResume() {
		//override
	}

	@Override
	public void onPause() {
		//override
	}

	@Override
	public void onStop() {
		//override
	}

	@Override
	public void onDestroy() {
		//override
	}

	@Override
	public void onOptionsItemSelected(MenuItem item) {
		//override
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		//override
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		//override
	}

	@Override
	public void onBackPressed() {
		//override
	}
}