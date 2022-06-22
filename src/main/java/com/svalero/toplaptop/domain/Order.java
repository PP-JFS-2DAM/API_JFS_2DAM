package com.svalero.toplaptop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Document(value = "orders")
public class Order  {

    @Id

    private String id;
    @Field(name = "order_date")
    @NotNull
    private LocalDate orderDate;
    @Field
    private String description;
    @ManyToOne
    @JoinColumn(name = "technical_id")
    @JsonBackReference(value = "technical-order")
    private Technical technical;
    @ManyToOne
    @JoinColumn(name = "computer_id")
    @JsonBackReference(value = "computer-order")
    private Computer computer;


}
