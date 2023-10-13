package com.multipolar.bootcamp.loggingBank.domain;

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
@Document(collection = "access_log_product")//nama collection
public class AccessLog implements Serializable {

    @Id
    private String Id;
    private String requestMethod;
    private String requestUrl;
    private Integer responseStatusCode;
    private LocalDateTime timeStamp = LocalDateTime.now();
    private String content;
}