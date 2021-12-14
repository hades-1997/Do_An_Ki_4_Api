package com.ray.videos.service;

import com.ray.videos.dao.CustomerRepository;
import com.ray.videos.dto.Purchase;
import com.ray.videos.dto.PurchaseResponse;
import com.ray.videos.entity.Customer;
import com.ray.videos.entity.Order;
import com.ray.videos.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from DTO
        Order order = purchase.getOrder();

        // generate tracking number using UUID (Universally Unique Identifier) ver 4
        String orderTrackingNumber = UUID.randomUUID().toString();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // get orderItems
        List<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));
//        for (OrderItem orderItem : orderItems) {
//            order.add(orderItem);
//        }

        // get addresses
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // get customer info
        Customer customer = purchase.getCustomer();
        customer.add(order);

        // save
        customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingNumber);
    }
}
