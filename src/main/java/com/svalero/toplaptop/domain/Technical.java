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
@Document(value = "technicals")
public class Technical {
    @Id

    private String id;
    @Field
    @NotBlank
    private String name;
    @Field
    @NotBlank
    private String surname;
    @Field
    @NotBlank
    private String DNI;
    @Field
    @Value("true")
    private boolean isAvailable;

    @OneToMany(mappedBy = "technical")
    private List<Order> orders;

}
