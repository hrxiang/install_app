package org.dplatform.install_app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import java.io.File;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class InstallAppPlugin implements MethodChannel.MethodCallHandler {
    private InstallAppPlugin(Activity activity) {
        this.activity = activity;
    }

    private Activity activity;

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        if ("install".equals(methodCall.method)) {
            String path = methodCall.argument("path");
            try {
                install(activity, path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        MethodChannel channel = new MethodChannel(registrar.messenger(), "install_app");
        channel.setMethodCallHandler(new InstallAppPlugin(registrar.activity()));
    }

    private static void install(Activity activity, String apkPath) {
        File apk = new File(apkPath);
        //apk文件的本地路径
        //会根据用户的数据类型打开android系统相应的Activity。
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(activity, apk);
            //权限
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            uri = Uri.parse("file://" + apk.getAbsolutePath());
        }
        //设置intent的数据类型是应用程序application
        intent.setDataAndType(uri, "application/vnd.android.package-archive");//
        //为这个新apk开启一个新的activity栈
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //开始安装
        activity.startActivity(intent);
    }
}
