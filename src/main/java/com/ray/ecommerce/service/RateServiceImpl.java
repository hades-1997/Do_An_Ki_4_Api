package com.ray.ecommerce.service;

import com.ray.ecommerce.dao.RateRepository;
import com.ray.ecommerce.dao.StatusPlaylistRepository;
import com.ray.ecommerce.entity.Rate;
import com.ray.ecommerce.entity.StatusPlaylist;
import com.ray.ecommerce.entity.Videos;
import com.ray.ecommerce.exception.AliasExistException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
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
    public Rate addRate(int userId, int playlistId,boolean isStatus, int star) {

        Rate rates = new Rate();
        rates.setUserId(userId);
        rates.setPlayListId(playlistId);
        rates.setIsStatus(isStatus);
//        StatusPlaylist statusPlaylist = statusPlaylistRepository.findStatusPlaylistById(StatusId);
  //      rates.setStatusPlaylist(statusPlaylist);

//        Long statusId = Long.parseLong(StatusId);
//
//       StatusPlaylist status = statusPlaylistRepository.findById(statusId).orElse(null);
//        //StatusPlaylist
//        rates.setStatusPlaylist(status);
        rates.setStar(star);

        rateRepository.save(rates);

        return rates;
    }

    @Override
    public Rate updateRate(Long currentId, Boolean newIsStatus, int newStar) throws AliasExistException {

        Rate currentRate = validateRate(currentId);

       //StatusPlaylist newStatus = statusPlaylistRepository.findById(newStatusId).orElse(null);
        //StatusPlaylist
        currentRate.setIsStatus(newIsStatus);
        currentRate.setStar(newStar);

        rateRepository.save(currentRate);
        return currentRate;
    }

    private Rate validateRate(Long currentId) throws AliasExistException {
//        Videos newVideoByAlias = videoRepository.findByAlias(newAlias);
        Rate currentRate = rateRepository.findById(currentId).orElse(null);
        if (currentRate == null) {
            throw new UsernameNotFoundException("No ID found by Rates " + currentRate);
        }
            return currentRate;
    }
}
