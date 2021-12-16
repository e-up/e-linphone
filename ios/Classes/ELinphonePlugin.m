#import "ELinphonePlugin.h"
#if __has_include(<e_linphone/e_linphone-Swift.h>)
#import <e_linphone/e_linphone-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "e_linphone-Swift.h"
#endif

@implementation ELinphonePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftELinphonePlugin registerWithRegistrar:registrar];
}
@end
