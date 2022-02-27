package com.ray.ecommerce.dao;

import com.ray.ecommerce.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {
    Rate findRateByUserIdAndPlayListId(int userId, int playlistId);
    //Rate findRateByPlayListIdAndCatStatus(int playListId,Long statusId);
    //Rate findRateByPlayListIdAndIsStatusTrue(int playListId,Boolean isStatus);
    long countByPlayListIdAndIsStatus(int playListId, boolean isStatus);
    // long countRateByCatStatus(Long CatStatus);
    //void countRatesByCatStatusAndPlayListId(Long CatStatus, int playlistId);
}
