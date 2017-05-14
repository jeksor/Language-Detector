package com.esorokin.lantector.ui.plugins.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;

public interface Plugin {
	void onCreate();

	void onViewCreated(View view);

	void onStart();

	void onResume();

	void onPause();

	void onStop();

	void onDestroy();

	void onOptionsItemSelected(MenuItem item);

	void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults);

	void onActivityResult(int requestCode, int resultCode, Intent data);

	void onBackPressed();
}