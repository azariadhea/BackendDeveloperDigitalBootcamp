package com.multipolar.bootcamp.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    //http://localhost:8080/api/product
    @GetMapping("/product")
    public ResponseEntity<List<Product>> product() {
        List<Product> todoList = List.of(
                new Product(1, "Laptop"),
                new Product(2, "Handphone"),
                new Product(3, "TV"));
        return ResponseEntity.ok(todoList);
    }
}
