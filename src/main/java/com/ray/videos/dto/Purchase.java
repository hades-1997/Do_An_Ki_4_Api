package com.ray.videos.dto;

import com.ray.videos.entity.Address;
import com.ray.videos.entity.Customer;
import com.ray.videos.entity.Order;
import com.ray.videos.entity.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private List<OrderItem> orderItems;
}
