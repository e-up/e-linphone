enum Transport { TCP, UDP, TLS, DTLS }

extension ToString on Transport {
  String toStr() {
    switch (this) {
      case Transport.TCP:
        return 'tcp';
      case Transport.UDP:
        return 'udp';
      case Transport.TLS:
        return 'tls';
      case Transport.DTLS:
        return 'dtls';
    }
  }
}
