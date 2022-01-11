package com.ray.ecommerce.controller;

import com.ray.ecommerce.constant.FileConstant;
import com.ray.ecommerce.dao.VideoRepository;
import com.ray.ecommerce.domain.HttpResponse;
import com.ray.ecommerce.domain.User;
import com.ray.ecommerce.entity.PageInfo;
import com.ray.ecommerce.entity.Videos;
import com.ray.ecommerce.exception.*;
import com.ray.ecommerce.service.VideoCategoriesService;
import com.ray.ecommerce.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

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
    @GetMapping("/all")
    public ResponseEntity<List<Videos>> getAllUsers() {
        List<Videos> videos = videoRepository.findAll();
        return new ResponseEntity<>(videos, HttpStatus.OK);
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

    // display profile Image
    @GetMapping(path = "/image/{title}/{title}", produces = IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable("title") String title, @PathVariable("homeimgalt") String homeimgalt)
            throws IOException {
        // /user/ray/springAngularEcommerce2/users/
        return Files.readAllBytes(Paths.get(FileConstant.USER_FOLDER + title + "/" + homeimgalt));
    }

    @GetMapping(path = "/image/video/{title}", produces = IMAGE_JPEG_VALUE)
    public byte[] getTempProfileImage(@PathVariable("title") String title) throws IOException {
        // http://localhost:8080/api/user/image/profile/{username}  => https://robohash.org/{username}
        URL url = new URL(FileConstant.DEFAULT_VIDEO_IMAGE_PATH + title);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (InputStream inputStream = url.openStream()) {
            byte[] chunk = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(chunk)) > 0) {
                byteArrayOutputStream.write(chunk, 0, bytesRead);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    //@PostMapping("/update")
    @PostMapping("/update")
    public ResponseEntity<Videos> updateVideo(
            @RequestParam("currentalias") String currentalias,
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
            @RequestParam("category-id") String categoryId) throws IOException, NotAnImageFileException {
        try {
            Videos updateVideo = videoService.updateVideo(
                    currentalias,
                    author,
                    artist,
                    Integer.parseInt(sourceid),
                    Integer.parseInt(status),
                    Integer.parseInt(archive),
                    title, alias, hometext, vid_path, vid_type, vid_duration, homeimgfile,homeimgalt, categoryId
            );

            return new ResponseEntity<>(updateVideo, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteVideos(@PathVariable("id") Long id) throws UserNotFoundException, IOException {
        videoService.deleteVideo(id);
        return response(HttpStatus.OK, "Videos has been deleted successfully");
    }


    @PostMapping("/updateProfileImage")
    public ResponseEntity<Videos> updateProfileImage(@RequestParam("homeimgfile") MultipartFile homeimgfile)
            throws EmailExistException, IOException, UsernameExistException, NotAnImageFileException, AliasExistException {
        String title = SecurityContextHolder.getContext().getAuthentication().getName();
        Videos videos = videoService.updateProfileImage(title, homeimgfile);
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),
                httpStatus, httpStatus.getReasonPhrase(), message), httpStatus);
    }
}
