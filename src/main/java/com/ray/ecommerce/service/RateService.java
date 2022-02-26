package com.ray.ecommerce.service;

import com.ray.ecommerce.entity.Rate;

public interface RateService {
    Rate addRate(int userId,int playlistId, int star);
}
