import 'dart:async';
import 'dart:developer';

import 'package:e_linphone/e_linphone.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'page/call_page.dart';
import 'page/sign_in_page.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  final SharedPreferences sharedPreferences =
      await SharedPreferences.getInstance();
  runApp(Provider(
    create: (context) => sharedPreferences,
    child: App(),
  ));
}

class App extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => AppState();
}

class AppState extends State<App> {
  static GlobalKey<NavigatorState> _navigatorKey = GlobalKey();

  late StreamSubscription _subscription;

  @override
  void initState() {
    super.initState();
    _subscription = ELinphone.stream.listen((event) {
      log('$event');
    });
  }

  @override
  void dispose() {
    _subscription.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      navigatorKey: _navigatorKey,
      onGenerateRoute: (settings) => SignInPage.route(),
    );
  }
}
