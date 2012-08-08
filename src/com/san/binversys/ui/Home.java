package com.san.binversys.ui;

import android.app.Activity;
import android.content.Intent;
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
		getMenuInflater().inflate(R.menu.activity_home,menu );
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// sample code. Trying the installing feature
		
		switch (item.getItemId()) {
		case R.id.menu_install:
			Utils.installAppFrom(this,
					"/mnt/sdcard/tmp/sell a bike July 17 2012 v0.2.4.apk");
			break;
		case R.id.menu_installed_apps:
			startActivity(new Intent(this, InstalledAppsActivity.class));
			break;
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

}
