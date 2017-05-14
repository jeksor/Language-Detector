package com.esorokin.lantector.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.esorokin.lantector.ui.plugins.base.CompositionPlugin;
import com.esorokin.lantector.ui.plugins.base.CompositionPluginDelegate;

public class BaseFragment extends MvpAppCompatFragment {
	private final CompositionPlugin compositionPlugin = new CompositionPluginDelegate();

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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initPlugins();
		compositionPlugin.onCreate();
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		compositionPlugin.onViewCreated(view);
	}

	@Override
	public void onStart() {
		super.onStart();
		compositionPlugin.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
		compositionPlugin.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		compositionPlugin.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
		compositionPlugin.onStop();
	}


	@Override
	public void onDestroy() {
		super.onDestroy();
		compositionPlugin.onDestroy();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		compositionPlugin.onActivityResult(requestCode, resultCode, data);
	}
}
