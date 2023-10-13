package com.multipolar.bootcamp.bank.repository;

import com.multipolar.bootcamp.bank.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}