package com.svalero.toplaptop.domain.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Computer {
    private String brand;
    private String model;
    private int ram;
    private boolean isRepaired;
    private long user;
}
