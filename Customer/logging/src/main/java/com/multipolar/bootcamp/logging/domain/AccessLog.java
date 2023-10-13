package com.multipolar.bootcamp.logging.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "access_log")//nama collection
public class AccessLog implements Serializable {

    @Id
    private String Id;
    private String method;
    private String status;
    private String message;
}
