import Flutter
import UIKit

public class SwiftMicroFlutterMipushPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "micro_flutter_mipush", binaryMessenger: registrar.messenger())
    let instance = SwiftMicroFlutterMipushPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }
}
