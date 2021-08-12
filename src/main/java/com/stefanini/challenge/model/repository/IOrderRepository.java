package com.stefanini.challenge.model.repository;

import com.stefanini.challenge.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByOrderByDateDesc();
}
