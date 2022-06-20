package com.svalero.toplaptop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "computer")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long computer_id;

    @Column
    @NotBlank
    private String brand;
    @Column
    @NotBlank
    private String model;
    @Column
    private int ram;
    @Column
    private boolean isRepaired;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
