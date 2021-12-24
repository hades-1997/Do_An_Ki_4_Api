package com.ray.ecommerce.dao;

import com.ray.ecommerce.entity.VideoCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoCatRepository extends JpaRepository<VideoCategories, Long> {
    //VideoCategories findVideoCategoriesById(int id);
    VideoCategories findVideoCategoriesByTitle(String title);

    VideoCategories findVideoCategoriesById(int id);
}
