import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:micro_flutter_mipush/micro_flutter_mipush.dart';

void main() {
  const MethodChannel channel = MethodChannel('micro_flutter_mipush');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await MicroFlutterMipush.platformVersion, '42');
  });
}
