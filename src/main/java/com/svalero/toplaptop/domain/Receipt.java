package com.svalero.toplaptop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @Value("0")
    private double price;
    @Column
    @Value("0")
    private double discount;
    @Column
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "order_id")
    private WorkOrder workorder;


}
