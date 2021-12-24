import 'dart:async';

import 'package:e_linphone/e_linphone.dart';
import 'package:flutter/material.dart';

class CallPage extends StatefulWidget {
  const CallPage({Key? key}) : super(key: key);

  static Route route() => MaterialPageRoute(builder: (context) => CallPage());

  @override
  State<StatefulWidget> createState() => CallPageState();
}

class CallPageState extends State<CallPage> {
  late StreamSubscription _subscription;

  String _state = '';

  String _message = '';

  @override
  void initState() {
    super.initState();
    _subscription = ELinphone.stream.listen((event) {
      final action = event['action'];
      if (action == 'CallStateChanged')
        _onCallStateChanged(state: event['state'], message: event['message']);
    });
  }

  @override
  void dispose() {
    _subscription.cancel();
    super.dispose();
  }

  void _onCallStateChanged({String? state, String? message}) {
    switch (state) {
      case 'Idle':
        break;
      case 'IncomingReceived':
        break;
      case 'PushIncomingReceived':
        break;
      case 'OutgoingInit':
        break;
      case 'OutgoingProgress':
        break;
      case 'OutgoingRinging':
        break;
      case 'OutgoingEarlyMedia':
        break;
      case 'Connected':
        break;
      case 'StreamsRunning':
        break;
      case 'Pausing':
        break;
      case 'Paused':
        break;
      case 'Resuming':
        break;
      case 'Referred':
        break;
      case 'Error':
        break;
      case 'End':
        Navigator.of(context).pop();
        break;
      case 'PausedByRemote':
        break;
      case 'UpdatedByRemote':
        break;
      case 'IncomingEarlyMedia':
        break;
      case 'Updating':
        break;
      case 'Released':
        break;
      case 'EarlyUpdatedByRemote':
        break;
      case 'EarlyUpdating':
        break;
    }
    _state = state ?? '';
    _message = message ?? '';
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Wrap(
          runSpacing: 12,
          children: [
            Center(
              child: Text('$_state,$_message'),
            ),
            Center(
              child: ElevatedButton(
                  onPressed: () => ELinphone.terminate(),
                  child: Icon(Icons.phone_disabled)),
            )
          ],
        ),
      ),
    );
  }
}
