package com.ray.videos.dao;

import com.ray.videos.entity.VideosRows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface VideosRowsRepository extends JpaRepository<VideosRows, Long> {
    //VideosRows findUserByVideo(String title);
//    Page<VideosRows> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
//
//    //@Query(value = "select p from Product p where p.name like concat('%',:name,'%')") //JPQL
//    Page<VideosRows> findByNameContaining(@RequestParam("title") String name, Pageable pageable);
    VideosRows findVideos(String title);

}
