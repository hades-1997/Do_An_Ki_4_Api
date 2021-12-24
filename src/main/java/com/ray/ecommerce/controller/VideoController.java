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
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/videos")

public class VideoController {

    private VideoService videoService;
    private VideoCategoriesService categoriesService;
    private VideoRepository videoRepository;

    @Autowired
    public VideoController(VideoService videoService, VideoCategoriesService categoriesService, VideoRepository videoRepository) {
        this.videoService = videoService;
        this.categoriesService = categoriesService;
        this.videoRepository = videoRepository;
    }

//    @GetMapping("/list")
//    public ResponseEntity<List<Videos>> getList(){
//        List<Videos> videos = videoRepository.findAll();
//        return new ResponseEntity<>(videos, HttpStatus.OK);
//    }

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
    public ResponseEntity<Videos> addNewVideo(@RequestParam("author") String author,
                                              @RequestParam("artist") String artist,
                                              @RequestParam("sourceid") String sourceid,
                                              @RequestParam("status") String status,
                                              @RequestParam("archive") String archive,
                                              @RequestParam("title") String title,
                                              @RequestParam("alias") String alias,
                                              @RequestParam("hometext") String hometext,
                                              @RequestParam("vid_path") String vid_path,
                                              @RequestParam("vid_type") String vid_type,
                                              @RequestParam("vid_duration") String vid_duration,
                                              @RequestParam(value="homeimgfile", required = false) MultipartFile homeimgfile,
                                              @RequestParam("homeimgalt") String homeimgalt,
                                              @RequestParam("allowed_comm") String allowed_comm,
                                              @RequestParam("allowed_rating") String allowed_rating,
                                              @RequestParam("hitstotal") String hitstotal,
                                              @RequestParam("total_rating") String total_rating,
                                              @RequestParam("click_rating") String click_rating,
                                              @RequestParam("CategoryId") String CategoryId) throws IOException, NotAnImageFileException {

        Videos newVideo = videoService.addNewVideo(author,artist,
                Integer.parseInt(sourceid), Integer.parseInt(status), Integer.parseInt(archive),
                title, alias, hometext, vid_path, vid_type, vid_duration, homeimgfile,homeimgalt,allowed_comm,
                Integer.parseInt(allowed_rating),
                Integer.parseInt(hitstotal), Integer.parseInt(total_rating), Integer.parseInt(click_rating), CategoryId);

        return new ResponseEntity<>(newVideo, HttpStatus.OK);
    }
}
