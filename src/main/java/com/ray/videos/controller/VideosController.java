package com.ray.videos.controller;

import com.ray.videos.dao.VideosRowsRepository;
import com.ray.videos.entity.VideosRows;
import com.ray.videos.service.VideosRowsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/videos")
public class VideosController {
    private VideosRowsRepository videosRowsRepository;

    @Autowired
    public VideosController( VideosRowsRepository videosRowsRepository) {
        this.videosRowsRepository = videosRowsRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<List<VideosRows>> getList(){
        List<VideosRows> videosRows = videosRowsRepository.findAll();
        return new ResponseEntity<>(videosRows, HttpStatus.OK);
    }


}
