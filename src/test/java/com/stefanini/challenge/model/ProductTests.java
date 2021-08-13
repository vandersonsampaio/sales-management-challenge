package com.stefanini.challenge.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class ProductTests {

    @Test
    public void productTest_BuilderGetterAndToString(){
        String nameProduct = "Product Name";
        int id = 1;
        double price = .5;
        int quantity = 2;
        String sku = "SKU";

        Product product = Product.builder().id(id).name(nameProduct)
                .price(price).quantity(quantity).sku(sku)
                .orderDetailSet(new HashSet<>()).build();

        Assert.assertEquals(nameProduct, product.getName());
        Assert.assertEquals(id, product.getId());
        Assert.assertEquals(price, product.getPrice(), .0001);
        Assert.assertEquals(quantity, product.getQuantity());
        Assert.assertEquals(sku, product.getSku());
        Assert.assertEquals(0, product.getOrderDetailSet().size());
        Assert.assertEquals(nameProduct, product.toString());
    }

    @Test
    public void productTest_NoArgs(){
        int quantity = 2;
        Product product = new Product();
        product.setQuantity(quantity);

        Assert.assertNotNull(product);
        Assert.assertEquals(quantity, product.getQuantity());
    }
    
    @Test
    public void productBuild(){
        Product.ProductBuilder builder = Product.builder().id(1);
        Assert.assertTrue(builder.toString().contains("1"));
    }
}
