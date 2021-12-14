package com.ray.videos.dao;

import com.ray.videos.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {
    // select * from product where category_id = ?
    Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);

    //@Query(value = "select p from Product p where p.name like concat('%',:name,'%')") //JPQL
    Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
}
