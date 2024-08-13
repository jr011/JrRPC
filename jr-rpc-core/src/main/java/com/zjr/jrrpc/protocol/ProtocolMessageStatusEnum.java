package com.zjr.jrrpc.protocol;

import lombok.Getter;

@Getter
public enum ProtocolMessageStatusEnum {
    OK("ok",20),
    BAD_REQUEST("badRequest",40),
    BAD_RESPONSE("badResponse",50);
    private final String text;
    private final int value;
    private ProtocolMessageStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }
    public static ProtocolMessageStatusEnum getEnumByValue(int value) {
        for(ProtocolMessageStatusEnum anEnum : ProtocolMessageStatusEnum.values()) {
            if(anEnum.value == value) {
                return anEnum;
            }
        }
        return null;
    }
}
