package com.ray.ecommerce.service;

import com.ray.ecommerce.entity.Course;

public interface CourseService {
    Course addCourse(String orderTrackingNumber, Long userId, Long playlistId, Double totalPrice);

}
