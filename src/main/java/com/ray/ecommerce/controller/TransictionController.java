package com.ray.ecommerce.controller;

import com.ray.ecommerce.dao.TransictionRepository;
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
}
