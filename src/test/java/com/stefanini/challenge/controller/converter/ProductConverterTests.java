package com.stefanini.challenge.controller.converter;

import com.stefanini.challenge.model.Product;
import com.stefanini.challenge.service.interfaces.IProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class ProductConverterTests {

    @InjectMocks
    private ProductConverter converter;

    @Spy
    private IProductService service;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void productConverterTest_getAsString(){
        String name = "Product Name";
        Product product = Product.builder().name(name).build();

        String actual = converter.getAsString(null, null, product);

        Assert.assertEquals(name, actual);
    }

    @Test
    public void productConverterTest_getAsString_NoValue(){
        String actual = converter.getAsString(null, null, null);

        Assert.assertNull(actual);
    }

    @Test
    public void productConverterTest_getAsObject(){
        int id = 9;
        String value = "Product";
        Product product = Product.builder().id(id).name(value).build();
        Mockito.doReturn(product).when(service).getByName(value);

        Product actual = converter.getAsObject(null, null, value);

        Assert.assertEquals(id, actual.getId());
        Assert.assertEquals(value, actual.getName());
    }

    @Test
    public void productConverterTest_getAsObject_NoValue_Empty(){
        String value = "";

        Product actual = converter.getAsObject(null, null, value);

        Assert.assertNull(actual);
    }

    @Test
    public void productConverterTest_getAsObject_NoValue_Null(){
        Product actual = converter.getAsObject(null, null, null);

        Assert.assertNull(actual);
    }
}
