package com.svalero.toplaptop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotBlank
    private String name;
    @Column
    @NotBlank
    private String surname;
    @Column
    @NotBlank
    private String dni;
    //@Value("false")
    @Column
    private boolean vip;
    //@Value("0")
    //@NotNull
    @Column
    private float latitude;
    //@Value("0")
    //@NotNull
    @Column
    private float longitude;
    //@Value("null")
    @Lob
    @Column
    private String userImage;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user-computer")
    private List<Computer> computers;
}
