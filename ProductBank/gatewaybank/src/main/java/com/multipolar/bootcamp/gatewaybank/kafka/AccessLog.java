package com.multipolar.bootcamp.gatewaybank.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessLog implements Serializable {
    private String requestMethod;
    private String requestUrl;
    private Integer responseStatusCode;
    private LocalDateTime timeStamp = LocalDateTime.now();
    private String content;
}
