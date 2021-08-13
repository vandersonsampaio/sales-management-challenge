package com.stefanini.challenge.controller;

import com.stefanini.challenge.model.Client;
import com.stefanini.challenge.model.Order;
import com.stefanini.challenge.model.Product;
import com.stefanini.challenge.service.interfaces.IClientService;
import com.stefanini.challenge.service.interfaces.IOrderService;
import com.stefanini.challenge.service.interfaces.IProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

public class ManagementControllerTests {

    private ManagementController controller;
    private IClientService clientService;
    private IProductService productService;
    private IOrderService orderService;

    @Before
    public void setUp(){
        clientService = Mockito.mock(IClientService.class);
        productService = Mockito.mock(IProductService.class);
        orderService = Mockito.mock(IOrderService.class);

        controller = new ManagementController(clientService, productService, orderService);
    }

    @Test
    public void initAndGetterTest() {
        Client client = Client.builder().name("Client Name").build();
        List<Client> expected = Collections.singletonList(client);

        when(clientService.findAllClients()).thenReturn(Collections.singletonList(client));

        controller.init();
        List<Client> actual = controller.getClients();

        Assert.assertTrue(actual.size() > 0);
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getName(), actual.get(0).getName());
    }

    @Test
    public void setterAndGetterOrderTest(){
        int id = 2;
        Date date = new Date();
        Order order = Order.builder().idOrder(id).date(date).build();

        controller.setSelectedOrder(order);
        Order actual = controller.getSelectedOrder();

        Assert.assertEquals(id, actual.getIdOrder());
        Assert.assertEquals(date, actual.getDate());
    }

    @Test
    public void getProductsTest(){
        int id = 7;
        String name = "Product Name";
        Product product = Product.builder().id(id).name(name).build();

        when(productService.findAllProducts()).thenReturn(Collections.singletonList(product));

        List<Product> actual = controller.getProducts();

        Assert.assertTrue(actual.size() > 0);
        Assert.assertEquals(id, actual.get(0).getId());
        Assert.assertEquals(name, actual.get(0).getName());
    }

    @Test
    public void getOrdersTest(){
        int id = 7;
        Date date = new Date();
        Order order = Order.builder().idOrder(id).date(date).build();

        when(orderService.findAllOrders()).thenReturn(Collections.singletonList(order));

        List<Order> actual = controller.getOrders();

        Assert.assertTrue(actual.size() > 0);
        Assert.assertEquals(id, actual.get(0).getIdOrder());
        Assert.assertEquals(date, actual.get(0).getDate());
    }
}
