import 'dart:async';

import 'package:e_linphone/e_linphone.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'home_page.dart';

class SignInPage extends StatefulWidget {
  static Route route() => MaterialPageRoute(builder: (context) => SignInPage());

  @override
  State<StatefulWidget> createState() => SignInPageState();
}

class SignInPageState extends State<SignInPage> {
  final GlobalKey<FormState> _formKey = GlobalKey();

  late SharedPreferences _sharedPreferences;

  late StreamSubscription _subscription;

  static String _cacheUriKey = '_cache_uri_';

  static String _cacheUsernameKey = '_cache_username_';

  static String _cachePasswordKey = '_cache_password_';

  static String _cacheDomainKey = '_cache_domain_';

  String _uri = '';
  String _username = '';
  String _password = '';
  String _domain = '';

  @override
  void initState() {
    super.initState();
    _sharedPreferences = context.read();
    _uri = _sharedPreferences.getString(_cacheUriKey) ?? '';
    _username = _sharedPreferences.getString(_cacheUsernameKey) ?? '';
    _password = _sharedPreferences.getString(_cachePasswordKey) ?? '';
    _domain = _sharedPreferences.getString(_cacheDomainKey) ?? '';
    _subscription = ELinphone.stream.listen((event) {});
  }

  @override
  void dispose() {
    _subscription.cancel();
    super.dispose();
  }

  void _connect() {
    _formKey.currentState?.save();
    _sharedPreferences.setString(_cacheUriKey, _uri);
    _sharedPreferences.setString(_cacheUsernameKey, _username);
    _sharedPreferences.setString(_cachePasswordKey, _password);
    _sharedPreferences.setString(_cacheDomainKey, _domain);
    ELinphone.login(
            uri: _uri,
            username: _username,
            password: _password,
            domain: _domain)
        .then((value) {
      Navigator.of(context).push(HomePage.route());
    }).onError((error, stackTrace) {
      ScaffoldMessenger.of(context)
          .showSnackBar(SnackBar(content: Text('$error')));
    });
  }

  @override
  Widget build(BuildContext context) {
    final uri = TextFormField(
      initialValue: _uri,
      decoration:
          InputDecoration(labelText: 'SIP Uri', hintText: 'sip:host:port'),
      onSaved: (value) => _uri = value ?? '',
    );

    final username = TextFormField(
      initialValue: _username,
      decoration:
          InputDecoration(labelText: 'Username', hintText: '13888888888'),
      onSaved: (value) => _username = value ?? '',
    );

    final password = TextFormField(
      initialValue: _password,
      decoration: InputDecoration(
        labelText: 'Password',
        hintText: 'Please input your password',
      ),
      onSaved: (value) => _password = value ?? '',
      obscureText: true,
    );

    final domain = TextFormField(
      initialValue: _domain,
      decoration:
          InputDecoration(labelText: 'Domain', hintText: 'sip.iamee.me'),
      onSaved: (value) => _domain = value ?? '',
    );

    final button = ElevatedButton(
      onPressed: _connect,
      child: Text('Connect'),
    );

    final body = Form(
        key: _formKey,
        child: Wrap(
          runSpacing: 12,
          children: [uri, username, password, domain, button],
        ));

    return Scaffold(
      body: SafeArea(
        child: SingleChildScrollView(
          child: Padding(
            padding: EdgeInsets.all(12),
            child: body,
          ),
        ),
      ),
    );
  }
}
