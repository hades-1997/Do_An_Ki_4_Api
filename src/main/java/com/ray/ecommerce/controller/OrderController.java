package com.ray.ecommerce.controller;

import com.ray.ecommerce.dao.OrderRepository;
import com.ray.ecommerce.entity.Order;
import com.ray.ecommerce.entity.Videos;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllUsers() {
        List<Order> orders = orderRepository.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/order")
    public Order placeOrder(@RequestBody Order order) {
        Order temp = orderRepository.save(order);
        return temp;
    }
}
