package com.multipolar.bootcamp.bank.service;

import com.multipolar.bootcamp.bank.domain.Product;
import com.multipolar.bootcamp.bank.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id){
        return productRepository.findById(id);
    }

    public Product createOrUpdateProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProductById(String id){
        productRepository.deleteById(id);
    }
}
