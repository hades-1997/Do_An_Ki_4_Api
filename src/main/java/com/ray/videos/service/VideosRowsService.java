package com.ray.videos.service;

import com.ray.videos.entity.VideosCat;
import com.ray.videos.entity.VideosRows;
import com.ray.videos.exception.NotAnImageFileException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

public interface VideosRowsService {
    //VideosRows findUserByVideo(String title);
    VideosRows findVideos(String title);
    VideosRows AddNewVideos(String author, String artist, int sourceid, Date add_time, int status, int archive, String title, String alias, String hometext, String vid_path, String vid_duration, MultipartFile homeimgfile, String homeimgalt, String allowed_comm, int allowed_rating, int hitstotal,
                        int total_rating, int click_rating, String categoryId) throws IOException, NotAnImageFileException;
}
