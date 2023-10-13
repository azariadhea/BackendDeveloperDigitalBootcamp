package com.multipolar.bootcamp.kyc.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Address implements Serializable {
    private String street;
    private String city;
    private String State;
    private String postalCode;
    private String country;
}