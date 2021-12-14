package com.ray.videos.service;

import com.ray.videos.dao.VideosRowsRepository;
import com.ray.videos.entity.VideosRows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
//@Qualifier("myUserDetailsService")
public class VideosRowsServiceImpl implements VideosRowsService{

//    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private VideosRowsRepository videosRowsRepository;

    @Autowired
    public VideosRowsServiceImpl(VideosRowsRepository videosRowsRepository) {
        this.videosRowsRepository = videosRowsRepository;
    }


//    @Override
//    public VideosRows findUserByVideo(String title) {
//        return videosRowsRepository.findUserByVideo(title);
//    }
}
