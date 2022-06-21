package com.svalero.toplaptop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_id;
    @Column
    private String description;

    @Column(name = "order_date")
    @NotNull
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "computer_id")
    private Computer computer;


    @ManyToOne
    @JoinColumn(name = "technical_id")
    private Technical technical;


}