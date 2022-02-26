package com.ray.ecommerce.dao;

import com.ray.ecommerce.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
}
