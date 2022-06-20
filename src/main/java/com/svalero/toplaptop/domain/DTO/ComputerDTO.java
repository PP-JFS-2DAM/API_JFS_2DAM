package com.svalero.toplaptop.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComputerDTO {
    private String brand;
    private String model;
    private int ram;
    private boolean isRepaired;
    private long user;
}
