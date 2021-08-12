package com.stefanini.challenge.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_order_detail")
public class OrderDetail {

    @Id
    @Column(name = "id_order_detail", nullable = false)
    private int idOrderDetail;

    @Column(name = "nr_quantity", nullable = false)
    private int quantity;

    @Column(name = "vl_unit_price", nullable = false)
    private double unitPrice;

    @Column(name = "vl_amount", nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;
}
