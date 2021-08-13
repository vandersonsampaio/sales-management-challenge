package com.stefanini.challenge.service.interfaces;

import com.stefanini.challenge.model.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAllProducts();
    Product getByName(String name);
}
