package com.ray.ecommerce.controller;

import com.ray.ecommerce.dao.CourseRepository;
import com.ray.ecommerce.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/course")
public class CourseController {
    private CourseRepository courseRepository;
    @Autowired
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Course> getVideoId(@PathVariable("id") Long id) {
//        Course course = courseRepository.findCourseById(id);
//        return new ResponseEntity<>(course, HttpStatus.OK);
//    }

//    @PathVariable("userPay")

    @GetMapping("/{userId}/{playlistId}")
    public ResponseEntity<Course> getCourse(@PathVariable("userId") Long userId,
                                             @PathVariable("playlistId") Long playlistId     ){

        Course course = courseRepository.findAllByUserIdAndPlaylistId(userId,playlistId);
        return  new ResponseEntity<>(course, HttpStatus.OK);
    }

}
