import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:e_linphone/e_linphone.dart';

void main() {
  const MethodChannel channel = MethodChannel('e_linphone');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await ELinphone.platformVersion, '42');
  });
}
