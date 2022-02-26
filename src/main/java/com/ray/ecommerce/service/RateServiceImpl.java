package com.ray.ecommerce.service;

import com.ray.ecommerce.dao.RateRepository;
import com.ray.ecommerce.entity.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RateServiceImpl implements  RateService{
    private RateRepository rateRepository;

    @Autowired
    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public Rate addRate(int userId, int playlistId, int star) {

        Rate rates = new Rate();
        rates.setUserId(userId);
        rates.setPlayListId(playlistId);
        rates.setStar(star);

        rateRepository.save(rates);

        return rates;
    }
}
