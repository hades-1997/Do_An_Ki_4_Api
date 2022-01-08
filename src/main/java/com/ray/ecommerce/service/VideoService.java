package com.ray.ecommerce.service;

import com.ray.ecommerce.entity.Videos;
import com.ray.ecommerce.exception.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface VideoService {



    Videos addNewVideo(String author, String artist, int sourceid, int status, int archive,
                       String title, String alias, String hometext, String vid_path, String vid_type,
                       String vid_duration, MultipartFile homeimgfile,
                       String homeimgalt, String allowed_comm, int allowed_rating, int hitstotal,
                       int total_rating, int click_rating, String CategoryId) throws IOException, NotAnImageFileException;

    Videos updateVideo(String currentalias, String author, String artist, int sourceid, int status, int archive,
                       String newtitle, String newAlias, String newhometext, String newvid_path, String newvid_type,
                       String newvid_duration, MultipartFile homeimgfile,
                       String newhomeimgalt, String CategoryId) throws IOException, NotAnImageFileException, AliasExistException;

    Videos updateProfileImage(String title, MultipartFile homeimgfile) throws EmailExistException, UsernameExistException, IOException, NotAnImageFileException, AliasExistException;

    void deleteVideo(long id) throws UserNotFoundException, IOException;
}
