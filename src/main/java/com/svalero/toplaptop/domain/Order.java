package com.svalero.toplaptop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "order")
public class Order  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Field(name = "order_date")
    @NotNull
    private LocalDate orderDate;
    @Field
    private String description;
    @ManyToOne
    @JoinColumn(name = "technical_id")
    private Technical technical;
    @ManyToOne
    @JoinColumn(name = "computer")
    private Computer computer;


}
