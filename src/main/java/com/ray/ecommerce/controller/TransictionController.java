package com.ray.ecommerce.controller;

import com.paypal.api.payments.TransactionBase;
import com.ray.ecommerce.dao.TransictionRepository;
import com.ray.ecommerce.domain.User;
import com.ray.ecommerce.entity.PlaylistCat;
import com.ray.ecommerce.entity.Transiction;
import com.ray.ecommerce.entity.Videos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/transiction")
public class TransictionController {
    private TransictionRepository transictionRepository;

    @Autowired
    public TransictionController(TransictionRepository transictionRepository) {
        this.transictionRepository = transictionRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transiction> getVideoId(@PathVariable("id") Long id) {
       Transiction transiction = transictionRepository.findTransictionById(id);
        return new ResponseEntity<>(transiction, HttpStatus.OK);
    }

    //@PathVariable("userPay")
    @GetMapping("/course")
    public ResponseEntity<Transiction> getCourse(@PathVariable("user_id") Long user_id ,
                                                 @PathVariable("playlist_tid") Long playlist_tid){

        Transiction course = transictionRepository.findByPlaylistCatAndUserPay(playlist_tid,user_id );
        return  new ResponseEntity<>(course, HttpStatus.OK);
    }

}
