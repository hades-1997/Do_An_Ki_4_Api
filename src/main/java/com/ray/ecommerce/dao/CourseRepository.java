package com.ray.ecommerce.dao;

import com.ray.ecommerce.domain.User;
import com.ray.ecommerce.entity.Course;
import com.ray.ecommerce.entity.PlaylistCat;
import com.ray.ecommerce.entity.Videos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface CourseRepository extends JpaRepository<Course, Long> {
//    Course findCourseById(Long id);

    Page<Course> findAll(Pageable pageable);
    Course findAllByUserIdAndPlaylistId(Long userId, Long playlistId);
}
