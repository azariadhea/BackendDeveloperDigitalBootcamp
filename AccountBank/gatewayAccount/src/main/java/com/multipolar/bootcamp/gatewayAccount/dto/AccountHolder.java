package com.multipolar.bootcamp.gatewayAccount.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AccountHolder implements Serializable {
    private String nik;
    private String name;
    private String address;
}
