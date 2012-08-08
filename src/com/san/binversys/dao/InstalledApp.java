package com.san.binversys.dao;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

public class InstalledApp {
		public Drawable iconDrawable;
		public String name;
		public String packageName;

		public InstalledApp(Drawable iconDrawable, String name,
				String pacakgeName) {
			super();
			this.iconDrawable = iconDrawable;
			this.name = name;
			this.packageName = pacakgeName;
		}

		public InstalledApp(ApplicationInfo applicationInfo,
				PackageManager packageManager) {
			iconDrawable = applicationInfo.loadIcon(packageManager);
			name = packageManager.getApplicationLabel(applicationInfo) + "";
			packageName = applicationInfo.packageName;
		}

	}