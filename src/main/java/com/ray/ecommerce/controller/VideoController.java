package com.ray.ecommerce.controller;

import com.ray.ecommerce.dao.VideoRepository;
import com.ray.ecommerce.entity.PageInfo;
import com.ray.ecommerce.entity.Videos;
import com.ray.ecommerce.exception.NotAnImageFileException;
import com.ray.ecommerce.service.VideoCategoriesService;
import com.ray.ecommerce.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/videos")

public class VideoController {

    private VideoService videoService;
    private VideoCategoriesService categoriesService;
    private VideoRepository videoRepository;

    @Autowired
    public VideoController(VideoService videoService, VideoCategoriesService categoriesService, VideoRepository videoRepository, VideoRepository videoRepository1) {
        this.videoService = videoService;
        this.categoriesService = categoriesService;
        this.videoRepository = videoRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllVideos(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue= "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Videos> videos = videoRepository.findAll(paging);

        PageInfo myPage = new PageInfo(videos.getNumber(), videos.getTotalElements(), videos.getTotalPages(), videos.getSize());

        Map<String, Object> response = new HashMap<>();
        response.put("videos", videos);
        response.put("page", myPage);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Videos> addNewVideo(
            @RequestParam("author") String author,
            @RequestParam(name ="artist", required = false) String artist,
            @RequestParam(name ="sourceid", required = false) String sourceid,
            @RequestParam(name ="status", required = false) String status,
            @RequestParam(name ="archive", required = false) String archive,
            @RequestParam(name ="title", required = false) String title,
            @RequestParam(name ="alias", required = false) String alias,
            @RequestParam(name ="hometext", required = false) String hometext,
            @RequestParam(name ="vid_path", required = false) String vid_path,
            @RequestParam(name = "vid_type", required = false) String vid_type,
            @RequestParam(name = "vid_duration", required = false) String vid_duration,
            @RequestParam(value="homeimgfile", required = false) MultipartFile homeimgfile,
            @RequestParam(name = "homeimgalt", required = false) String homeimgalt,
            @RequestParam(name = "allowed_comm", required = false) String allowed_comm,
            @RequestParam(name = "allowed_rating", required = false) String allowed_rating,
            @RequestParam(name = "hitstotal", required = false ) String hitstotal,
            @RequestParam(name = "total_rating", required = false ) String total_rating,
            @RequestParam(name = "click_rating", required = false) String click_rating,
            @RequestParam("category-id") String categoryId) throws IOException, NotAnImageFileException {
        try {
            Videos newVideo = videoService.addNewVideo(
                    author,
                    artist,
                    Integer.parseInt(sourceid),
                    Integer.parseInt(status),
                    Integer.parseInt(archive),
                    title, alias, hometext, vid_path, vid_type, vid_duration, homeimgfile,homeimgalt,allowed_comm,
                    Integer.parseInt(allowed_rating),
                    Integer.parseInt(hitstotal),
                    Integer.parseInt(total_rating),
                    Integer.parseInt(click_rating), categoryId);

            return new ResponseEntity<>(newVideo, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
