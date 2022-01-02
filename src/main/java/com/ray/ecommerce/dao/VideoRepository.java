package com.ray.ecommerce.dao;


import com.ray.ecommerce.entity.Videos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface VideoRepository extends JpaRepository<Videos, Long> {
    Page<Videos> findAll(Pageable pageable);

    Videos findByAlias(String alias);
}
