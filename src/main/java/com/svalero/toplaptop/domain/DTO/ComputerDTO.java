package com.svalero.toplaptop.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComputerDTO {

    private String brand;
    private String model;
    private String ram;
    private byte[] computerImage;
    private long user;
}
