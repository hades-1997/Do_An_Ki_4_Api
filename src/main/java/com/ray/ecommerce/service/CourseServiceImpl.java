//package com.ray.ecommerce.service;
//
//import com.ray.ecommerce.dao.TransictionRepository;
//import com.ray.ecommerce.dao.UserRepository;
//import com.ray.ecommerce.dao.VideosPlaylistCatRepository;
//import com.ray.ecommerce.dto.Purchase;
//import com.ray.ecommerce.dto.PurchaseResponse;
//import com.ray.ecommerce.entity.Transiction;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.UUID;
//
//@Service
//@Transactional
//public class TransictionServiceImpl implements  TransictionService{
//
//    private TransictionRepository transictionRepository;
//    private UserRepository userRepository;
//    private VideosPlaylistCatRepository videosPlaylistCatRepository;
//
//    @Autowired
//    public TransictionServiceImpl(TransictionRepository transictionRepository, UserRepository userRepository, VideosPlaylistCatRepository videosPlaylistCatRepository) {
//        this.transictionRepository = transictionRepository;
//        this.userRepository = userRepository;
//        this.videosPlaylistCatRepository = videosPlaylistCatRepository;
//    }
//
//    @Override
//    public PurchaseResponse placeOrder(Purchase purchase) {
//        return null;
//    }
//
//    @Override
//    public Transiction addCourse( String userPay, String playlistCat) {
//
//        Transiction transiction = new Transiction();
//        String orderTrackingNumber = UUID.randomUUID().toString();
//        transiction.setOrderTrackingNumber(orderTrackingNumber);
////        transiction.setUserPay(userRepository.findUserByEmail(userPay));
////        transiction.setPlaylistCat(videosPlaylistCatRepository.findPlaylistCatByAlias(playlistCat));
//
//        transictionRepository.save(transiction);
//
//        return transiction;
//    }
//}
