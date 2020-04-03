package org.dplatform.install_app;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;

import java.io.File;


public class FileProvider extends androidx.core.content.FileProvider {
    private static final String FILE_PROVIDER_AUTHORITIES_VALUE = "%s.fileProvider";

    public static Uri getUriForFile(Activity activity, File file) {
        String author = String.format(FILE_PROVIDER_AUTHORITIES_VALUE, activity.getPackageName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return androidx.core.content.FileProvider.getUriForFile(activity, author, file);
        } else {
            return Uri.fromFile(file);
        }
    }
}
