package com.stefanini.challenge.service.interfaces;

import com.stefanini.challenge.model.Client;
import com.stefanini.challenge.model.Order;
import com.stefanini.challenge.model.OrderDetail;

import java.util.List;

public interface IOrderService {

    List<Order> findAllOrders();

    boolean saveOrder(Client client, List<OrderDetail> items) throws Exception;
}
