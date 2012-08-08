package com.san.binversys.uitil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.san.binversys.R;
import com.san.binversys.dao.InstalledApp;

public class Utils {

	private static final String TAG = "Utils";

	/**
	 * opens the apk specified in filelocation.
	 * 
	 * @param context
	 * @param fileLocation
	 */
	public static void installAppFrom(Context context, String fileLocation) {
		try {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(new File(fileLocation)),
					"application/vnd.android.package-archive");
			context.startActivity(intent);
		} catch (Exception exception) {
			Log.e(TAG, exception.getMessage() + "");
			Toast.makeText(context, R.string.no_apk, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * returns installed applicationa
	 * 
	 * @return
	 */
	public static ArrayList<InstalledApp> getInstalledApps(Context context) {
		List<ApplicationInfo> installedApplications = context
				.getPackageManager().getInstalledApplications(
						PackageManager.GET_META_DATA);
		ArrayList<InstalledApp> installedApps = new ArrayList<InstalledApp>();
		PackageManager packageManager = context.getPackageManager();
		for (ApplicationInfo info : installedApplications) {
			installedApps.add(new InstalledApp(info, packageManager));
		}
		return installedApps;
	}

}
