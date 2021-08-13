package com.stefanini.challenge.service.implementation;

import com.stefanini.challenge.model.Client;
import com.stefanini.challenge.model.Order;
import com.stefanini.challenge.model.OrderDetail;
import com.stefanini.challenge.model.Product;
import com.stefanini.challenge.model.repository.IOrderDetailRepository;
import com.stefanini.challenge.model.repository.IOrderRepository;
import com.stefanini.challenge.model.repository.IProductRepository;
import com.stefanini.challenge.service.interfaces.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final IOrderRepository repository;
    private final IProductRepository productRepository;
    private final IOrderDetailRepository orderDetailRepository;

    public OrderService(IOrderRepository repository,
                        IProductRepository productRepository,
                        IOrderDetailRepository orderDetailRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<Order> findAllOrders(){
        return repository.findAllByOrderByDateDesc();
    }

    @Transactional(rollbackFor = { Exception.class })
    public boolean saveOrder(Client client, List<OrderDetail> items) throws Exception {
        Order order = Order.builder().client(client).date(new Date()).build();
        order.setAmount(items.stream().mapToDouble(OrderDetail::getAmount).sum());

        List<Product> products = updateQuantities(items);
        if(products.stream().anyMatch(p -> p.getQuantity() < 0)) throw new Exception();

        productRepository.saveAll(products);
        repository.save(order);
        items.forEach(i -> i.setOrder(order));
        orderDetailRepository.saveAll(items);
        return true;
    }

    private List<Product> updateQuantities(List<OrderDetail> items) {
        List<Product> products = productRepository.findAllById(
                items.stream()
                        .mapToInt(o -> o.getProduct().getId())
                        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll));

        for(Product product : products){
            int quantity = items.stream().filter(p -> p.getProduct().getId() == product.getId()).findFirst().orElse(OrderDetail.builder().quantity(0).build()).getQuantity();

            product.setQuantity(product.getQuantity() - quantity);
        }

        return products;
    }
}
