package com.ua.Anton.Anton.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String model;

    private String fuel;

    private int quantity;

    @JsonIgnore
    @OneToMany(mappedBy = "car")
    private List<Sale> saleList;

    public Car(Long id, String brand, String model, String fuel, int quantity) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.fuel = fuel;
        this.quantity = quantity;
    }
}
