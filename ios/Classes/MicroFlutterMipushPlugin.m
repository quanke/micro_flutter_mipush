#import "MicroFlutterMipushPlugin.h"
#import <micro_flutter_mipush/micro_flutter_mipush-Swift.h>

@implementation MicroFlutterMipushPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftMicroFlutterMipushPlugin registerWithRegistrar:registrar];
}
@end
