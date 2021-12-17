import 'dart:async';

import 'package:e_linphone/e_linphone_exception.dart';
import 'package:flutter/services.dart';

import 'transport.dart';

export 'transport.dart';

class ELinphone {
  static const MethodChannel _methodChannel = const MethodChannel('e_linphone');
  static StreamController _controller = StreamController.broadcast();
  static const EventChannel _eventChannel =
      const EventChannel('e_linphone_event');

  static StreamSubscription<dynamic>? _subscription;

  static Stream<dynamic> get stream {
    if (_subscription == null) {
      _subscription = const EventChannel('e_linphone_event')
          .receiveBroadcastStream()
          .listen((event) {
        _controller.add(event);
      });
    }
    return _controller.stream;
  }

  static Future<String?> get platformVersion async {
    final String? version =
        await _methodChannel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String?> login(
      {required String uri,
      required String username,
      required String password,
      required String domain,
      Transport? transport,
      String? userid,
      String? ha1,
      String? relam,
      String? algorithm}) {
    return _methodChannel.invokeMethod<String>('login', {
      'uri': uri,
      'username': username,
      'password': password,
      'domain': domain,
      'transport': transport?.toStr(),
      'userid': userid,
      'ha1': ha1,
      'realm': relam,
      'algorithm': algorithm
    }).onError((PlatformException error, stackTrace) =>
        throw error.toELinphoneException());
  }

  static void release() {
    _subscription?.cancel();
    _controller.close();
  }
}
