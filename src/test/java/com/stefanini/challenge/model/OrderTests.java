package com.stefanini.challenge.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;

public class OrderTests {

    @Test
    public void orderTest_BuilderGetterAndToString(){
        int id = 1;
        Date date = new Date();
        double amount = 18.52;
        Client client = new Client();

        Order order = Order.builder().idOrder(id).date(date)
                .amount(amount).client(client).items(new HashSet<>()).build();

        Assert.assertEquals(id, order.getIdOrder());
        Assert.assertEquals(date, order.getDate());
        Assert.assertEquals(amount, order.getAmount(), .0001);
        Assert.assertEquals(0, order.getItems().size());
        Assert.assertEquals(client, order.getClient());
        Assert.assertNotEquals("", order.toString());
    }

    @Test
    public void orderTest_NoArgsAndSetter(){
        int id = 1;
        Date date = new Date();
        double amount = 18.52;
        Client client = new Client();

        Order order = new Order();
        order.setAmount(amount);
        order.setIdOrder(id);
        order.setDate(date);
        order.setClient(client);
        order.setItems(new HashSet<>());

        Assert.assertEquals(id, order.getIdOrder());
        Assert.assertEquals(date, order.getDate());
        Assert.assertEquals(amount, order.getAmount(), .0001);
        Assert.assertEquals(0, order.getItems().size());
        Assert.assertEquals(client, order.getClient());
    }

    @Test
    public void orderBuild(){
        int id = 1;
        Order.OrderBuilder builder = Order.builder().idOrder(id);
        Assert.assertTrue(builder.toString().contains(Integer.toString(id)));
    }
}
