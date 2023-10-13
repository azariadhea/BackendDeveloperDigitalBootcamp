package com.multipolar.bootcamp.account.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Document
public class AccountHolder implements Serializable {

    @NotEmpty(message = "NIK tidak boleh kosong")
    @Length(min=16, message = "Jumlah digit harus 16")
    private String nik;
    @NotEmpty(message = "Nama tidak boleh kosong")
    private String name;
    @NotEmpty(message = "Alamat tidak boleh kosong")
    private String address;
}
