package com.ray.ecommerce.service;

import com.ray.ecommerce.dao.RateRepository;
import com.ray.ecommerce.dao.StatusPlaylistRepository;
import com.ray.ecommerce.entity.Rate;
import com.ray.ecommerce.entity.StatusPlaylist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RateServiceImpl implements  RateService{
    private RateRepository rateRepository;
    private StatusPlaylistRepository statusPlaylistRepository;

    @Autowired
    public RateServiceImpl(RateRepository rateRepository, StatusPlaylistRepository statusPlaylistRepository) {
        this.rateRepository = rateRepository;
        this.statusPlaylistRepository = statusPlaylistRepository;
    }

    @Override
    public Rate addRate(int userId, int playlistId,Long StatusId, int star) {

        Rate rates = new Rate();
        rates.setUserId(userId);
        rates.setPlayListId(playlistId);

        StatusPlaylist status = statusPlaylistRepository.findById(StatusId).orElse(null);;
        //StatusPlaylist
        rates.setStatusPlaylist(status);
        rates.setStar(star);

        rateRepository.save(rates);

        return rates;
    }
}
