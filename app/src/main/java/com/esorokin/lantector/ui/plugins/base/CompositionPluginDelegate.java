package com.esorokin.lantector.ui.plugins.base;

import java.util.ArrayList;
import java.util.Collection;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;

public final class CompositionPluginDelegate implements CompositionPlugin {
	public Collection<Plugin> plugins = new ArrayList<>();

	@Override
	public void attach(final Plugin plugin) {
		if (plugin != null) {
			plugins.add(plugin);
		}
	}

	@Override
	public void detach(final Plugin plugin) {
		if (plugin != null) {
			plugins.remove(plugin);
		}
	}

	@Override
	public void onCreate() {
		for (Plugin plugin : plugins) {
			plugin.onCreate();
		}
	}

	@Override
	public void onViewCreated(final View view) {
		for (Plugin plugin : plugins) {
			plugin.onViewCreated(view);
		}
	}

	@Override
	public void onStart() {
		for (Plugin plugin : plugins) {
			plugin.onStart();
		}
	}

	@Override
	public void onResume() {
		for (Plugin plugin : plugins) {
			plugin.onResume();
		}
	}

	@Override
	public void onPause() {
		for (Plugin plugin : plugins) {
			plugin.onPause();
		}
	}

	@Override
	public void onStop() {
		for (Plugin plugin : plugins) {
			plugin.onStop();
		}
	}

	@Override
	public void onDestroy() {
		for (Plugin plugin : plugins) {
			plugin.onDestroy();
		}
	}

	@Override
	public void onOptionsItemSelected(MenuItem item) {
		for (Plugin plugin : plugins) {
			plugin.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		for (Plugin plugin : plugins) {
			plugin.onRequestPermissionsResult(requestCode, permissions, grantResults);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		for (Plugin plugin : plugins) {
			plugin.onActivityResult(requestCode, resultCode, data);
		}
	}

	@Override
	public void onBackPressed() {
		for (Plugin plugin : plugins) {
			plugin.onBackPressed();
		}
	}
}