package com.stefanini.challenge.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class ClientTests {

    @Test
    public void clientTest_BuilderGetterAndToString(){
        String nameClient = "Product Name";
        String identifier = "01234567890";
        String email = "email@standart.com";

        Client client = Client.builder().identifier(identifier).name(nameClient)
                .email(email).orderSet(new HashSet<>()).build();

        Assert.assertEquals(nameClient, client.getName());
        Assert.assertEquals(identifier, client.getIdentifier());
        Assert.assertEquals(email, client.getEmail());
        Assert.assertEquals(0, client.getOrderSet().size());
        Assert.assertTrue(client.toString().contains(identifier));
        Assert.assertTrue(client.toString().contains(nameClient));
        Assert.assertTrue(client.toString().contains(email));
    }

    @Test
    public void clientTest_NoArgs(){
        Client client = new Client();

        Assert.assertNotNull(client);
    }

    @Test
    public void clientBuild(){
        String name = "Name";
        Client.ClientBuilder builder = Client.builder().name(name);
        Assert.assertTrue(builder.toString().contains(name));
    }
}
