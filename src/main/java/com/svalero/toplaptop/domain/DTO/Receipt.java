package com.svalero.toplaptop.domain.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Receipt {

    private long order;
    private double price;
    private double discount;
    private LocalDate date;

}
