package com.svalero.toplaptop.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ReceiptDTO {

    private long orde;
    private double price;
    private double discount;
    private LocalDate date;

}
