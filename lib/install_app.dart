import 'package:flutter/services.dart';

class InstallApp {
  static const MethodChannel _channel = const MethodChannel('install_app');

  static void installApk({String path}) {
    _channel.invokeMethod("install", {"path": path});
  }
}
