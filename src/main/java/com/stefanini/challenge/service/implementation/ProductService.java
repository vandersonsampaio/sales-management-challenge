package com.stefanini.challenge.service.implementation;

import com.stefanini.challenge.model.Product;
import com.stefanini.challenge.model.repository.IProductRepository;
import com.stefanini.challenge.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IProductRepository repository;

    public ProductService(IProductRepository repository){
        this.repository = repository;
    }

    public List<Product> findAllProducts(){
        return repository.findAll();
    }
}
