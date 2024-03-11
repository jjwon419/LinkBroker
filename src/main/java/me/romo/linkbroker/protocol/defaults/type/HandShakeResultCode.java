package me.romo.linkbroker.protocol.defaults.type;

public interface HandShakeResultCode {
    byte SUCCESS = 0x00;
    byte PROTOCOL_VERSION_DIFFERENT = 0x01;
    byte INVALID_PASSWORD = 0x02;
    byte ALREADY_EXIST_SERVER_NAME = 0x03;
}
