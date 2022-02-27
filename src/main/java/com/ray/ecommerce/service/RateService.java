package com.ray.ecommerce.service;

import com.ray.ecommerce.entity.Rate;
import com.ray.ecommerce.exception.AliasExistException;

public interface RateService {
    Rate addRate(int userId,int playlistId,boolean isStatus, int star);
    Rate updateRate(Long currentId, Boolean newIsStatus, int newStar ) throws AliasExistException;
}
