package com.ray.ecommerce.dto;

import com.ray.ecommerce.domain.User;
import com.ray.ecommerce.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private User user;
    private PlaylistCat playlistCat;
    private List<OrderItem> orderItems;
}
