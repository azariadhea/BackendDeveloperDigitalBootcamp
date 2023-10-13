package com.multipolar.bootcamp.bank.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Document
public class Product implements Serializable {

    @Id
    private String id;
    @NotEmpty(message = "Product Name tidak boleh kosong")
    private String productName;
    @NotEmpty(message = "Product Type tidak boleh kosong")
    private ProductType productType;
    @NotEmpty(message = "Interest Rate tidak boleh kosong")
    private Double interestRate;
    @NotEmpty(message = "Minimum Balance tidak boleh kosong")
    private Double minimumBalance;
    @NotEmpty(message = "Maximum Loan Amount tidak boleh kosong")
    private Double maximumLoanAmount;
    @NotEmpty(message = "Terms and Conditions tidak boleh kosong")
    private String termsAndConditions;
    private LocalDateTime dateOfCreation = LocalDateTime.now();
}
