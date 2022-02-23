package com.ray.ecommerce.controller;

import com.ray.ecommerce.dao.CourseRepository;
import com.ray.ecommerce.entity.Course;
import com.ray.ecommerce.service.CourseService;
import com.ray.ecommerce.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/course")
public class CourseController {
    private CourseRepository courseRepository;
    private CourseService courseService;
    @Autowired
    public CourseController(CourseRepository courseRepository, CourseService courseService) {
        this.courseRepository = courseRepository;
        this.courseService = courseService;
    }

    @GetMapping("/{userId}/{playlistId}")
    public ResponseEntity<Course> getCourse(@PathVariable("userId") Long userId,
                                             @PathVariable("playlistId") Long playlistId     ){

        Course course = courseRepository.findAllByUserIdAndPlaylistId(userId,playlistId);
        return  new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<Course> addCourse(@RequestParam(value = "orderTrackingNumber", required = false) String orderTrackingNumber,
                                            @RequestParam(value = "userId", required = false ) String userId,
                                            @RequestParam(value = "playlistId", required = false) String playlistId){
        try{

            Course addCourse = courseService.addCourse(
                    orderTrackingNumber,
                    Long.parseLong(userId),
                    Long.parseLong(playlistId));
            return new ResponseEntity<>(addCourse, HttpStatus.OK);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }



}
