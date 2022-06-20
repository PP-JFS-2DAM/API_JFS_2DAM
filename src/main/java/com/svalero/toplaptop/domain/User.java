package com.svalero.toplaptop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    @Column
    @NotBlank
    private String name;
    @Column
    @NotBlank
    private String surname;
    @Column
    @NotBlank
    private String dni;
    @Column
    private boolean isVIP;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user-computer")
    private List<Computer> computers;
}
