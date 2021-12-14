package com.ray.videos.controller;

import com.ray.videos.dao.ProductRepository;
import com.ray.videos.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private ProductRepository productRepository;

    @Autowired
    public TestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/product")
    public Product placeOrder(@RequestBody Product product) {
        Product temp = productRepository.save(product);
        return temp;
    }
}
