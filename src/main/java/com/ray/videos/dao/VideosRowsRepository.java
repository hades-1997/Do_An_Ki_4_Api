package com.ray.videos.dao;

import com.ray.videos.entity.VideosRows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

public interface VideosRowsRepository extends JpaRepository<VideosRows, Long> {
    //VideosRows findUserByVideo(String title);
}
