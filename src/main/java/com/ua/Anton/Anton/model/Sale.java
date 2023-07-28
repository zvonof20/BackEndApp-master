package com.ua.Anton.Anton.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="car_id", nullable=false)
    private Car car;

    private int originalCarQuantity;


    @ManyToOne
    @JoinColumn(name="manager_id", nullable=false)
    private Manager manager;


    private int originalManagerSaleQuantity;

    private int quantity;

    public Sale(int originalCarQuantity, int originalManagerSaleQuantity, int quantity) {
        this.originalCarQuantity = originalCarQuantity;
        this.originalManagerSaleQuantity = originalManagerSaleQuantity;
        this.quantity = quantity;
    }
}
