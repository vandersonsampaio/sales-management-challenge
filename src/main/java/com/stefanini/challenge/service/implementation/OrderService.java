package com.stefanini.challenge.service.implementation;

import com.stefanini.challenge.model.Order;
import com.stefanini.challenge.model.OrderDetail;
import com.stefanini.challenge.model.Product;
import com.stefanini.challenge.model.repository.IOrderRepository;
import com.stefanini.challenge.model.repository.IProductRepository;
import com.stefanini.challenge.service.interfaces.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final IOrderRepository repository;
    private final IProductRepository productRepository;

    public OrderService(IOrderRepository repository,
                        IProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    public List<Order> findAllOrders(){
        return repository.findAllByOrderByDateDesc();
    }

    @Transactional(rollbackFor = { Exception.class })
    public boolean saveOrder(Order order) throws Exception {
        List<Product> products = updateQuantities(order);
        if(products.stream().anyMatch(p -> p.getQuantity() < 0)) throw new Exception();

        productRepository.saveAll(products);
        repository.save(order);
        return true;
    }

    private List<Product> updateQuantities(Order order) {
        List<Product> products = productRepository.findAllById(
                order.getItems().stream()
                        .mapToInt(o -> o.getProduct().getId())
                        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll));

        for(Product product : products){
            int quantity = order.getItems().stream().filter(p -> p.getProduct().getId() == product.getId()).findFirst().orElse(OrderDetail.builder().quantity(0).build()).getQuantity();

            product.setQuantity(product.getQuantity() - quantity);
        }

        return products;
    }
}
