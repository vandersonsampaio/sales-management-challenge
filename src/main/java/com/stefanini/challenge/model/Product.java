package com.stefanini.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @Column(name = "id_product")
    private int id;

    @Column(name = "nr_sku")
    private String sku;

    @Column(name = "ds_name")
    private String name;

    @Column(name = "vl_unit_price")
    private double price;

    @Column(name = "nr_quantity")
    private int quantity;

    @OneToMany
    @JoinColumn(name = "id_product")
    private Set<OrderDetail> orderDetailSet;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
