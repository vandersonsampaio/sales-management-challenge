package com.stefanini.challenge.service;

import com.stefanini.challenge.model.Client;
import com.stefanini.challenge.model.Order;
import com.stefanini.challenge.model.OrderDetail;
import com.stefanini.challenge.model.Product;
import com.stefanini.challenge.model.repository.IOrderDetailRepository;
import com.stefanini.challenge.model.repository.IOrderRepository;
import com.stefanini.challenge.model.repository.IProductRepository;
import com.stefanini.challenge.service.implementation.OrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.when;

public class OrderServiceTests {

    private OrderService service;
    private IOrderRepository orderRepository;
    private IProductRepository productRepository;

    @Before
    public void setUp(){
        orderRepository = Mockito.mock(IOrderRepository.class);
        productRepository = Mockito.mock(IProductRepository.class);
        IOrderDetailRepository orderDetailRepository = Mockito.mock(IOrderDetailRepository.class);
        service = new OrderService(orderRepository, productRepository, orderDetailRepository);
    }

    @Test
    public void findAllOrdersTest() {
        Order order = Order.builder().idOrder(1).date(new Date()).build();
        List<Order> expected = Collections.singletonList(order);
        when(orderRepository.findAllByOrderByDateDesc()).thenReturn(Collections.singletonList(order));

        List<Order> actual = service.findAllOrders();

        Assert.assertTrue(actual.size() > 0);
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getIdOrder(), actual.get(0).getIdOrder());
        Assert.assertEquals(expected.get(0).getDate(), actual.get(0).getDate());
    }

    @Test
    public void saveOrderTest_Success() {
        Client client = Client.builder().identifier("1").name("Client Name").build();
        Product productOne = Product.builder().id(1).name("Product One").quantity(2).price(.5).build();
        Product productTwo = Product.builder().id(2).name("Product Two").quantity(1).price(1.5).build();
        List<Product> products = Arrays.asList(productOne, productTwo);

        OrderDetail orderDetailOne = OrderDetail.builder().product(productOne).quantity(2).unitPrice(.5).amount(1).build();
        OrderDetail orderDetailTwo = OrderDetail.builder().product(productTwo).quantity(1).unitPrice(1.5).amount(1.5).build();
        List<OrderDetail> items = Arrays.asList(orderDetailOne, orderDetailTwo);

        when(productRepository.findAllById(anyIterable())).thenReturn(products);

        try {
            boolean actual = service.saveOrder(client, items);
            Assert.assertTrue(actual);
        } catch (Exception ex){
            Assert.assertNull(ex);
        }
    }

    @Test
    public void saveOrderTest_ThereIsProductButNoOrderDetail() {
        Client client = Client.builder().identifier("1").name("Client Name").build();
        Product productOne = Product.builder().id(1).name("Product One").quantity(2).price(.5).build();
        Product productTwo = Product.builder().id(2).name("Product Two").quantity(1).price(1.5).build();
        Product productThree = Product.builder().id(3).name("Product Three").quantity(4).price(2.8).build();
        List<Product> products = Arrays.asList(productOne, productTwo, productThree);

        OrderDetail orderDetailOne = OrderDetail.builder().product(productOne).quantity(2).unitPrice(.5).amount(1).build();
        OrderDetail orderDetailTwo = OrderDetail.builder().product(productTwo).quantity(1).unitPrice(1.5).amount(1.5).build();
        List<OrderDetail> items = Arrays.asList(orderDetailOne, orderDetailTwo);

        when(productRepository.findAllById(anyIterable())).thenReturn(products);

        try {
            boolean actual = service.saveOrder(client, items);
            Assert.assertTrue(actual);
        } catch (Exception ex){
            Assert.assertNull(ex);
        }
    }

    @Test
    public void saveOrderTest_ProductWithoutQuantity() {
        Client client = Client.builder().identifier("1").name("Client Name").build();
        Product productOne = Product.builder().id(1).name("Product One").quantity(2).price(.5).build();
        Product productTwo = Product.builder().id(2).name("Product Two").quantity(1).price(1.5).build();
        List<Product> products = Arrays.asList(productOne, productTwo);

        OrderDetail orderDetailOne = OrderDetail.builder().product(productOne).quantity(3).unitPrice(.5).amount(1).build();
        OrderDetail orderDetailTwo = OrderDetail.builder().product(productTwo).quantity(1).unitPrice(1.5).amount(1.5).build();
        List<OrderDetail> items = Arrays.asList(orderDetailOne, orderDetailTwo);

        when(productRepository.findAllById(anyIterable())).thenReturn(products);

        try {
            boolean actual = service.saveOrder(client, items);
            Assert.assertFalse(actual);
        } catch (Exception ex){
            Assert.assertNotNull(ex);
        }
    }

}
