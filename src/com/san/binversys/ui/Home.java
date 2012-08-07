package com.san.binversys.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.san.binversys.R;
import com.san.binversys.uitil.Utils;

public class Home extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("install");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// sample code. Trying the installing feature
		if (item.getTitle().equals("install"))
			Utils.installAppFrom(this,
					"/mnt/sdcard/tmp/sell a bike July 17 2012 v0.2.4.apk");

		return super.onOptionsItemSelected(item);
	}

}
