package com.san.binversys.uitil;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Utils {

	/**
	 * opens the apk specified in filelocation. 
	 * @param context
	 * @param fileLocation
	 */
	public static void installAppFrom(Context context, String fileLocation) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(fileLocation)),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}

}
