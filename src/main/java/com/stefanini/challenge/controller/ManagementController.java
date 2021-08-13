package com.stefanini.challenge.controller;

import com.stefanini.challenge.model.Client;
import com.stefanini.challenge.model.Order;
import com.stefanini.challenge.model.Product;
import com.stefanini.challenge.service.interfaces.IClientService;
import com.stefanini.challenge.service.interfaces.IOrderService;
import com.stefanini.challenge.service.interfaces.IProductService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ManagedBean
@RequestScoped
public class ManagementController implements Serializable {

    private final IClientService clientService;
    private final IProductService productService;
    private final IOrderService orderService;
    private List<Client> clients;
    private Order selectedOrder;

    public ManagementController(IClientService clientService,
                                IProductService productService,
                                IOrderService orderService){
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @PostConstruct
    public void init() {
        clients = clientService.findAllClients();
    }

    public List<Client> getClients(){
        return clients;
    }

    public List<Product> getProducts(){
        return productService.findAllProducts();
    }

    public Order getSelectedOrder(){
        return selectedOrder;
    }

    public List<Order> getOrders(){
        return orderService.findAllOrders();
    }

    public void setSelectedOrder(Order order){
        selectedOrder = order;
    }
}
