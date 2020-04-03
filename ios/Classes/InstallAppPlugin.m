#import "InstallAppPlugin.h"
#import <install_app/install_app-Swift.h>

@implementation InstallAppPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftInstallAppPlugin registerWithRegistrar:registrar];
}
@end
