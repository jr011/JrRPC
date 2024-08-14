package com.zjr.jrrpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RpcResponse implements Serializable {
    private Object data;//响应数据
    private Class<?> dataType;//响应数据类型
    private String message;//响应信息
    private Exception exception;//异常信息
}
