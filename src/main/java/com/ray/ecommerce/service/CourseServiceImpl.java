package com.ray.ecommerce.service;


import com.ray.ecommerce.dao.CourseRepository;
import com.ray.ecommerce.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;


@Service
@Transactional
public class CourseServiceImpl implements  CourseService{

    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course addCourse(String orderTrackingNumber, Long userId, Long playlistId) {
        //        // generate tracking number using UUID (Universally Unique Identifier) ver 4
        Course addCourse = new Course();
        orderTrackingNumber = UUID.randomUUID().toString();
        addCourse.setOrderTrackingNumber(orderTrackingNumber);
        addCourse.setUserId(userId);
        addCourse.setPlaylistId(playlistId);
        courseRepository.save(addCourse);
        return addCourse;
    }

}
