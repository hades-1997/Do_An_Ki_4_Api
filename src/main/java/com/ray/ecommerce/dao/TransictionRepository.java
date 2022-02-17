package com.ray.ecommerce.dao;

import com.ray.ecommerce.entity.Transiction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface TransictionRepository extends JpaRepository<Transiction, Long> {

}
