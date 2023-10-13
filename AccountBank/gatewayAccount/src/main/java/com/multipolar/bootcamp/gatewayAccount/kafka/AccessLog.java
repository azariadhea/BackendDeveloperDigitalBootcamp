package com.multipolar.bootcamp.gatewayAccount.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessLog implements Serializable {
    private String httpMethod;
    private String requestUrl;
    private Integer responseStatusCode;
    private String content;
    private String clientIP;
    private String userAgent;
    private LocalDateTime timeStamp = LocalDateTime.now();
}
