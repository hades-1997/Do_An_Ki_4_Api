package com.ray.ecommerce.dao;

import com.ray.ecommerce.entity.Rate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RateCurdRepository extends CrudRepository<Rate, Long> {
    //SELECT COUNT(*) FROM playlist_rate WHERE cat_status = 10 AND playlist_id=3;
//    @Query("SELECT COUNT(r.playListId) FROM Rate r WHERE r.playListId=:playListId & r.cat_status=:cat_status ")
//    long aMethodNameOrSomething(int playListId,Long cat_status);
}
