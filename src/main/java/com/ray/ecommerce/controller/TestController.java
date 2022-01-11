package com.ray.ecommerce.controller;

import com.ray.ecommerce.dao.VideoRepository;
import com.ray.ecommerce.entity.Videos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private VideoRepository videoRepository;

    @Autowired
    public TestController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }


    @PostMapping("/video")
    public Videos placeOrder(@RequestBody Videos product) {

        Videos temp = videoRepository.save(product);
        return temp;
    }
}
