package com.svalero.toplaptop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long receipt_id;
    @Column
    @NotBlank
    private double price;
    @Column
    @NotBlank
    private double discount;
    @Column
    @NotBlank
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
