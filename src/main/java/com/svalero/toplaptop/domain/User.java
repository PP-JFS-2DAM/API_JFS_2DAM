package com.svalero.toplaptop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Field
    @NotBlank
    private String name;
    @Field
    @NotBlank
    private String surname;
    @Field
    @NotBlank
    private String dni;
    @Field(name = "vip_user")
    @Value("false")
    private boolean vip;
    @Field
    @NotNull
    @Value("0")
    private float latitude;
    @Field
    @NotNull
    @Value("0")
    private float longitud;
    @Field
    @Lob
    @Value("null")
    private byte[] userImage;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user-computer")
    private List<Computer> computers;
}
