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
public class Order  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "order_date")
    @NotNull
    private LocalDate orderDate;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "technical_id")
    private Technical technical;
    @ManyToOne
    @JoinColumn(name = "computer")
    private Computer computer;


}
