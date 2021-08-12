package com.stefanini.challenge.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @Column(name = "id_order", nullable = false)
    private int idOrder;

    @Column(name = "dt_order", nullable = false)
    private Date date;

    @Column(name = "vl_amount", nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "nr_identifier_client", nullable = false)
    private Client client;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_order")
    private Set<OrderDetail> items;
}
