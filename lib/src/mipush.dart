import 'dart:async';

import 'package:flutter/services.dart';

class Mipush {
  static const MethodChannel _channel =
      const MethodChannel('micro_flutter_mipush');

  static const _eventChannel =
      const EventChannel('micro_flutter_receiver_mipush');

  static Future<String> init(appId, appKey) async {
    final Map<String, dynamic> arguments = <String, dynamic>{
      'appId': appId,
      'appKey': appKey,
    };
    final String version = await _channel.invokeMethod('init', arguments);
    return version;
  }
//
//  StreamSubscription _subscription;
//
//  void receiveBroadcast() {
////开启监听
//    if (_subscription == null) {
//      _subscription = _eventChannel
//          .receiveBroadcastStream()
//          .listen(_onEvent, onError: _onError);
//    }
//  }
//
//  void dispose() {
//    //取消监听
//    if (_subscription != null) {
//      _subscription.cancel();
//    }
//  }
//
//  void _onEvent(Object event) {
//    print("ChannelPage: $event");
//  }
//
//  void _onError(Object error) {
//    print(error);
//  }
}
