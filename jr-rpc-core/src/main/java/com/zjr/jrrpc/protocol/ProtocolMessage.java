package com.zjr.jrrpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProtocolMessage<T> {
    private Header header;
    private T body;
    @Data
    public static class Header{
        private byte magic; //魔数
        private byte version; //版本
        private byte serializer; //序列化器
        private byte type; //消息类型（请求/响应）
        private byte status; //状态
        private long requestId; //请求id
        private int bodyLength; //消息体长度
    }
}
