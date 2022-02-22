package com.ray.ecommerce.dao;

import com.ray.ecommerce.domain.User;
import com.ray.ecommerce.entity.Course;
import com.ray.ecommerce.entity.PlaylistCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface CourseRepository extends JpaRepository<Course, Long> {
//    Course findCourseById(Long id);
    Course findAllByUserIdAndPlaylistId(Long userId, Long playlistId);
}
