package com.stefanini.challenge.controller;

import com.stefanini.challenge.model.Client;
import com.stefanini.challenge.model.Product;
import com.stefanini.challenge.service.interfaces.IClientService;
import com.stefanini.challenge.service.interfaces.IOrderService;
import com.stefanini.challenge.service.interfaces.IProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class NewOrderControllerTests {

    private NewOrderController controller;
    private IClientService clientService;
    private IProductService productService;
    private IOrderService orderService;

    @Before
    public void setUp(){
        clientService = Mockito.mock(IClientService.class);
        productService = Mockito.mock(IProductService.class);
        orderService = Mockito.mock(IOrderService.class);

        controller = new NewOrderController(clientService, productService, orderService);
    }

    @Test
    public void initAndCompletesTest() {
        String productQuery = "put";
        String clientQuery = "am";
        Client clientOne = Client.builder().identifier("192").name("Client Name").build();
        Client clientTwo = Client.builder().identifier("845").name("Example Test").build();
        List<Client> clients = Arrays.asList(clientOne, clientTwo);

        Product productOne = Product.builder().name("Computer").build();
        Product productTwo = Product.builder().name("Television").build();
        List<Product> products = Arrays.asList(productOne, productTwo);

        when(clientService.findAllClients()).thenReturn(clients);
        when(productService.findAllProducts()).thenReturn(products);

        controller.init();
        List<String> actualClients = controller.completeClientInformation(clientQuery);
        List<String> actualProducts = controller.completeProductInformation(productQuery);

        Assert.assertEquals(2, actualClients.size());
        Assert.assertTrue(actualClients.get(0).contains(clientQuery));
        Assert.assertTrue(actualClients.get(1).contains(clientQuery));

        Assert.assertEquals(1, actualProducts.size());
        Assert.assertTrue(actualProducts.get(0).contains(productQuery));
    }
}
