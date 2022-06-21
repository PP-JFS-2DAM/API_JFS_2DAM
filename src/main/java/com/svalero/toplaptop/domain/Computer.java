package com.svalero.toplaptop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "computer")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long computer_id;

    @Column
    @NotBlank
    private String brand;
    @Column
    @NotBlank
    private String model;
    @Column
    @Value("0")
    private int ram;
    @Column
    @Value("false")
    private boolean isRepaired;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "computer")
    @JsonBackReference(value = "computer-order")
    private List<Order> orders;

}
