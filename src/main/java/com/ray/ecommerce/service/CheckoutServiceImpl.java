package com.ray.ecommerce.service;

import com.ray.ecommerce.dao.*;
import com.ray.ecommerce.domain.User;
import com.ray.ecommerce.dto.Purchase;
import com.ray.ecommerce.dto.PurchaseResponse;
import com.ray.ecommerce.entity.PlaylistCat;
import com.ray.ecommerce.entity.Transiction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private VideosPlaylistCatRepository playListCatRepository;
    private TransictionRepository transictionRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository, OrderRepository orderRepository, UserRepository userRepository, VideoCatRepository playListCatRepository, VideosPlaylistCatRepository playListCatRepository1, TransictionRepository transictionRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.playListCatRepository = playListCatRepository1;
        this.transictionRepository = transictionRepository;
    }

    @Transactional
    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from DTO
        Transiction transiction = purchase.getTransiction();
        // generate tracking number using UUID (Universally Unique Identifier) ver 4
        String orderTrackingNumber = UUID.randomUUID().toString();
        transiction.setOrderTrackingNumber(orderTrackingNumber);
//         get customer info
        User user = userRepository.findUserByEmail(purchase.getUser().getEmail());
        user.add(transiction);
//         get playlistCat info
        PlaylistCat playlistCats = playListCatRepository.findPlaylistCatByAlias(purchase.getPlaylistCat().getAlias());
        playlistCats.add(transiction);
        //order.set
        transiction.setUserPay(purchase.getUser());
        //playlistcat.set
        transiction.setPlaylistCat(purchase.getPlaylistCat());
        // save
        transictionRepository.save(transiction);
        return new PurchaseResponse(orderTrackingNumber);
    }
}
