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
@Document(value = "technical")
public class Technical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
    @JsonBackReference(value = "technical-order")
    private List<Order> orders;

}
