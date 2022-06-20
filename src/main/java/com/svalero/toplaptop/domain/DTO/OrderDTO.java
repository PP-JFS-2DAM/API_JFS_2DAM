package com.svalero.toplaptop.domain.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor

public class OrderDTO {
    private long computer;
    private long technical;
    private String description;
    private LocalDate date;

}
