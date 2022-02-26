package com.ray.ecommerce.dao;

import com.ray.ecommerce.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {
    Rate findRateByUserIdAndPlayListId(int userId, int playlistId);
}
