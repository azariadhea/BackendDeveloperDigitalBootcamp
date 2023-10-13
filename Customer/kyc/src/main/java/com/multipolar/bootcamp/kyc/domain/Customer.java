package com.multipolar.bootcamp.kyc.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Customer implements Serializable {
    @Id
    private String id;
    @NotEmpty(message = "NIK  tidak boleh kosong")
    @Length(min=16, message = "Jumlah digit harus 16")
    private String nik;
    @NotEmpty(message = "Nama depan tidak boleh kosong")
    private String firstName;
    private String lastName;
    @Email(message = "Format email harus benar")
    @NotEmpty(message = "Email tidak boleh kosong")
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private MembershipStatus membershipStatus;
    private Address address;
}
