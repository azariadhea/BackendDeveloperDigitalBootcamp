package com.multipolar.bootcamp.loggingAccount.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "access_log_account")//nama collection
public class AccessLog implements Serializable {

    @Id
    private String id;
    private String httpMethod;
    private String requestUrl;
    private Integer responseStatusCode;
    private String content;
    private String clientIP;
    private String userAgent;
    private LocalDateTime timeStamp = LocalDateTime.now();
}
