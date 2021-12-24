import 'dart:developer';

import 'package:e_linphone/e_linphone.dart';
import 'package:e_linphone_example/page/call_page.dart';
import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  static Route route() => MaterialPageRoute(builder: (context) => HomePage());

  @override
  State<StatefulWidget> createState() => HomePageState();
}

class HomePageState extends State<HomePage> {
  String _uri = '';

  @override
  Widget build(BuildContext context) {
    final uri = TextFormField(
      initialValue: _uri,
      onChanged: (value) => _uri = value,
    );

    final call = ElevatedButton(
      child: Text('Call'),
      onPressed: () => ELinphone.call(uri: _uri)
          .onError((error, stackTrace) => log('error $error')).then((value) => Navigator.of(context).push(CallPage.route())),
    );

    return Scaffold(
      body: SafeArea(
        child: SingleChildScrollView(
          child: Wrap(
            runSpacing: 12,
            children: [uri, call],
          ),
        ),
      ),
    );
  }
}
