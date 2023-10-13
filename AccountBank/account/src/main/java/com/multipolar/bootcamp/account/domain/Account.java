package com.multipolar.bootcamp.account.domain;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Document
public class Account implements Serializable {

    @Id
    private String id;
    @NotEmpty(message = "Nama Akun tidak boleh kosong")
    private String accountNumber;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private AccountHolder accountHolder;
    private Double balance;
    private LocalDateTime openingDate = LocalDateTime.now();
    private LocalDate closingDate;
}
