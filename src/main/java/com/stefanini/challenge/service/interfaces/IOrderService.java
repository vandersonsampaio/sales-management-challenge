package com.stefanini.challenge.service.interfaces;

import com.stefanini.challenge.model.Order;

import java.util.List;

public interface IOrderService {

    List<Order> findAllOrders();

    boolean saveOrder(Order order) throws Exception;
}
