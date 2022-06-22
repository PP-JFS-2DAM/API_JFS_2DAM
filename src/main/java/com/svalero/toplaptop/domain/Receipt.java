package com.svalero.toplaptop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "receipts")
public class Receipt {

    @Id

    private String id;

    @Field
    @Value("0")
    private double price;
    @Field
    @Value("0")
    private double discount;
    @Field
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;


}
