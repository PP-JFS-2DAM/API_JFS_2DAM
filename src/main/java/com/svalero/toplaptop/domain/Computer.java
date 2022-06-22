package com.svalero.toplaptop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "computers")
public class Computer  {

    @Id
    private String id;
    @Field
    @NotBlank
    private String brand;
    @Field
    @NotBlank
    private String model;
    @Field
    @NotBlank
    private String ram;
    @Field
    @Lob
    @Value("null")
    private byte[] computerImage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-computer")
    private User user;

    @OneToMany(mappedBy = "computer")
    private List<Order> orders;



}
