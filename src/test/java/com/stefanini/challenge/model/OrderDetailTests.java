package com.stefanini.challenge.model;

import org.junit.Assert;
import org.junit.Test;

public class OrderDetailTests {

    @Test
    public void orderDetailTest_BuilderGetterAndToString(){
        int id = 1;
        double unitPrice = 9.26;
        double amount = 18.52;
        int quantity = 2;

        Product product = new Product();
        Order order = new Order();

        OrderDetail orderDetail = OrderDetail.builder().idOrderDetail(id)
                .amount(amount).quantity(quantity).unitPrice(unitPrice)
                .product(product).order(order).build();

        Assert.assertEquals(id, orderDetail.getIdOrderDetail());
        Assert.assertEquals(unitPrice, orderDetail.getUnitPrice(), .0001);
        Assert.assertEquals(amount, orderDetail.getAmount(), .0001);
        Assert.assertEquals(quantity, orderDetail.getQuantity());
        Assert.assertEquals(order, orderDetail.getOrder());
        Assert.assertEquals(product, orderDetail.getProduct());
        Assert.assertNotEquals("", orderDetail.toString());
    }

    @Test
    public void orderDetailTest_NoArgsAndSetter(){
        int id = 1;
        double unitPrice = 9.26;
        double amount = 18.52;
        int quantity = 2;

        Product product = new Product();
        Order order = new Order();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setIdOrderDetail(id);
        orderDetail.setQuantity(quantity);
        orderDetail.setAmount(amount);
        orderDetail.setProduct(product);
        orderDetail.setUnitPrice(unitPrice);

        Assert.assertEquals(id, orderDetail.getIdOrderDetail());
        Assert.assertEquals(unitPrice, orderDetail.getUnitPrice(), .0001);
        Assert.assertEquals(amount, orderDetail.getAmount(), .0001);
        Assert.assertEquals(quantity, orderDetail.getQuantity());
        Assert.assertEquals(order, orderDetail.getOrder());
        Assert.assertEquals(product, orderDetail.getProduct());
    }

    @Test
    public void orderDetailBuild(){
        int id = 1;
        OrderDetail.OrderDetailBuilder builder = OrderDetail.builder().idOrderDetail(id);
        Assert.assertTrue(builder.toString().contains(Integer.toString(id)));
    }
}
