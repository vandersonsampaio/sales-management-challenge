package com.stefanini.challenge.controller.converter;

import com.stefanini.challenge.model.Client;
import com.stefanini.challenge.service.interfaces.IClientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class ClientConverterTests {

    @InjectMocks
    private ClientConverter converter;

    @Spy
    private IClientService service;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void clientConverterTest_getAsString(){
        String identifier = "01234567890";
        String name = "Client Name";
        Client client = Client.builder().identifier(identifier).name(name).build();

        String actual = converter.getAsString(null, null, client);

        Assert.assertEquals(client.toString(), actual);
    }

    @Test
    public void clientConverterTest_getAsString_NoValue(){
        String actual = converter.getAsString(null, null, null);

        Assert.assertNull(actual);
    }

    @Test
    public void clientConverterTest_getAsObject(){
        String identifier = "01234567890";
        String value = "Name Client";
        Client client = Client.builder().identifier(identifier).name(value).build();
        Mockito.doReturn(client).when(service).getById(identifier);

        Client actual = converter.getAsObject(null, null, client.toString());

        Assert.assertEquals(identifier, actual.getIdentifier());
        Assert.assertEquals(value, actual.getName());
    }

    @Test
    public void clientConverterTest_getAsObject_NoValue_Empty(){
        String value = "";

        Client actual = converter.getAsObject(null, null, value);

        Assert.assertNull(actual);
    }

    @Test
    public void clientConverterTest_getAsObject_NoValue_Null(){
        Client actual = converter.getAsObject(null, null, null);

        Assert.assertNull(actual);
    }
}
