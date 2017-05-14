package com.esorokin.lantector.ui.plugins;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.esorokin.lantector.ui.plugins.base.BaseDependencyPlugin;

public class ToolbarPlugin extends BaseDependencyPlugin<AppCompatActivity> {
	@NonNull
	private String title;

	public ToolbarPlugin(AppCompatActivity delegate, @StringRes int titleRes) {
		this(delegate, delegate.getString(titleRes));
	}

	public ToolbarPlugin(AppCompatActivity delegate, @NonNull String title) {
		super(delegate);
		this.title = title;
	}

	public ToolbarPlugin(AppCompatActivity delegate) {
		super(delegate);
		this.title = "";
	}

	@Override
	public void onViewCreated(final View view) {
		super.onViewCreated(view);
		Toolbar toolbar = getToolbar(view);
		if (toolbar != null) {
			getDependency().setSupportActionBar(toolbar);
			if (!TextUtils.isEmpty(title)) {
				getDependency().setTitle(title);
			}
		}
	}

	public void setTitle(@NonNull String title) {
		this.title = title;
		getDependency().setTitle(title);
	}

	private Toolbar getToolbar(View view) {
		if (view instanceof Toolbar) {
			return (Toolbar) view;
		}
		if (view instanceof ViewGroup) {
			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
				Toolbar toolbar = getToolbar(((ViewGroup) view).getChildAt(i));
				if (toolbar != null) {
					return toolbar;
				}
			}
		}
		return null;
	}
}
