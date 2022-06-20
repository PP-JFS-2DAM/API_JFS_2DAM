package com.svalero.toplaptop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "technical")
public class Technical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long technical_id;
    @Column
    @NotBlank
    private String name;
    @Column
    @NotBlank
    private String surname;
    @Column
    @NotBlank
    private String DNI;
    @Column
    private boolean isAvailable;

    @OneToMany(mappedBy = "technical")
    @JsonBackReference(value = "technical-order")
    private List<Order> orders;

}
