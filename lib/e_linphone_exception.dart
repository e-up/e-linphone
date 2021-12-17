import 'dart:convert';

import 'package:flutter/services.dart';

enum ELinphoneExceptionCode { LOGIN_FAILURE, CALL_FAILURE }

class ELinphoneException {
  final ELinphoneExceptionCode? code;
  final String? message;

  ELinphoneException({required this.code, required this.message});

  Map<String, dynamic> toJson() {
    return {'code': '$code', 'message': message};
  }

  @override
  String toString() {
    return jsonEncode(this.toJson());
  }
}

extension ToELinphoneExceptionCode on String {
  ELinphoneExceptionCode? toELinphoneExceptionCode() {
    switch (this) {
      case "1":
        return ELinphoneExceptionCode.LOGIN_FAILURE;
      case "2":
        return ELinphoneExceptionCode.CALL_FAILURE;
    }
    return null;
  }
}

extension ToELinphoneException on PlatformException {
  ELinphoneException toELinphoneException() {
    return ELinphoneException(
        code: this.code.toELinphoneExceptionCode(), message: this.message);
  }
}
