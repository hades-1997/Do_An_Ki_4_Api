package com.ray.videos.dao;

import com.ray.videos.entity.VideosCat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideosCategoriesRepository extends JpaRepository<VideosCat, Long> {
    VideosCat findVideosCatBy(String title);

    public  VideosCat findById(int theId);
}
