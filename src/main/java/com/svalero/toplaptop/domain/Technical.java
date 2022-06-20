package com.svalero.toplaptop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "technical")
public class Technical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long technical_id;
    @Column
    @NotBlank
    private String name;
    @Column
    @NotBlank
    private String surname;
    @Column
    @NotBlank
    private String DNI;
    @Column
    @NotBlank
    private boolean isAvailable;

}
