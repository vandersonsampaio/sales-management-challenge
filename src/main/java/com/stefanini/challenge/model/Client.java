package com.stefanini.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    @Column(name = "nr_identifier", nullable = false)
    private String identifier;

    @Column(name = "ds_name", nullable = false)
    private String name;

    @Column(name = "ds_email", nullable = false)
    @Email
    private String email;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name = "nr_identifier_client")
    private Set<Order> orderSet;

    @Override
    public String toString() {
        return identifier +
                " | " + name +
                " | " + email;
    }
}
