package com.ray.ecommerce.dao;

import com.ray.ecommerce.entity.VideoCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VideoCatRepository extends JpaRepository<VideoCategories, Long> {

    Page<VideoCategories> findAll(Pageable pageable);


    VideoCategories findVideoCategoriesByAlias(String alias);

    VideoCategories findVideoCategoriesByTitle(String title);
}
