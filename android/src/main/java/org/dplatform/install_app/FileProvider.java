package org.dplatform.install_app;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;

import java.io.File;


public class FileProvider extends androidx.core.content.FileProvider {

    public static Uri getUriForFile(Activity activity, File file) {
        String author = String.format("%s.dplatform.fileProvider", activity.getPackageName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return androidx.core.content.FileProvider.getUriForFile(activity, author, file);
        } else {
            return Uri.fromFile(file);
        }
    }
}
