package com.stefanini.challenge.service;

import com.stefanini.challenge.model.Product;
import com.stefanini.challenge.model.repository.IProductRepository;
import com.stefanini.challenge.service.implementation.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

public class ProductServiceTests {

    private ProductService service;
    private IProductRepository repository;

    @Before
    public void setUp(){
        repository = Mockito.mock(IProductRepository.class);
        service = new ProductService(repository);
    }

    @Test
    public void findAllProductsTest() {
        Product product = Product.builder().name("Product Name").build();
        List<Product> expected = Collections.singletonList(product);
        when(repository.findAll()).thenReturn(Collections.singletonList(product));

        List<Product> actual = service.findAllProducts();

        Assert.assertTrue(actual.size() > 0);
        Assert.assertEquals(expected.size(), actual.size());
        Assert.assertEquals(expected.get(0).getName(), actual.get(0).getName());
    }

    @Test
    public void getByNameTest_Exists() {
        String nameProduct = "Product Name";
        Product product = Product.builder().name(nameProduct).build();
        when(repository.findAllByName(nameProduct)).thenReturn(Collections.singletonList(product));

        Product actual = service.getByName(nameProduct);

        Assert.assertNotNull(actual);
        Assert.assertEquals(nameProduct, actual.getName());
    }

    @Test
    public void getByNameTest_Null() {
        String nameProduct = "Product Name";
        when(repository.findAllByName(nameProduct)).thenReturn(new ArrayList<>());

        Product actual = service.getByName(nameProduct);

        Assert.assertNull(actual);
    }

}
