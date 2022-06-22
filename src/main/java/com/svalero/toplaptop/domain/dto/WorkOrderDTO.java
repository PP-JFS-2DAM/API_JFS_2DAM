package com.svalero.toplaptop.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor

public class WorkOrderDTO {

    private LocalDate date;
    private String description;
    private long technical;
    private long computer;

}
