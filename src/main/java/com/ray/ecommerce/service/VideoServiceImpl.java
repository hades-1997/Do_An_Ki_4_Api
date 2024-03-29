package com.ray.ecommerce.service;

import com.ray.ecommerce.constant.FileConstant;
import com.ray.ecommerce.dao.VideoCatRepository;
import com.ray.ecommerce.dao.VideoRepository;
import com.ray.ecommerce.dao.VideosPlaylistCatRepository;
import com.ray.ecommerce.entity.VideoCategories;
import com.ray.ecommerce.entity.Videos;
import com.ray.ecommerce.exception.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Transactional
public class VideoServiceImpl implements VideoService{

    private Logger LOGGER = LoggerFactory.getLogger(VideoServiceImpl.class);

    private VideoRepository videoRepository;

    private VideoCatRepository category;
    private VideosPlaylistCatRepository videosPlaylistCat;

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository,VideoCatRepository category) {
        this.videoRepository = videoRepository;
        this.category = category;
    }


    @Override
    public Videos addNewVideo(String author, String artist, int sourceid, int status, int archive, String title, String alias, String hometext,
                              String vid_path, String vid_type, String vid_duration, MultipartFile homeimgfile, String homeimgalt, String allowed_comm,
                              int allowed_rating, int hitstotal, int total_rating, int click_rating, String CategoryId) throws IOException, NotAnImageFileException {


    Videos videos = new Videos();
    videos.setAuthor(author);
    videos.setArtist(artist);
    videos.setSourceid(sourceid);
    videos.setAdd_time(new Date());
    videos.setStatus(status);
    videos.setArchive(archive);
    videos.setTitle(title);
    videos.setAlias(alias);
    videos.setHometext(hometext);
    videos.setVid_path(vid_path);
    videos.setVid_type(vid_type);
    videos.setVid_duration(vid_duration);
    videos.setHomeimgfile(
            ServletUriComponentsBuilder.fromCurrentContextPath().path(FileConstant.DEFAULT_VIDEO_IMAGE_PATH + title).toUriString()
    );;
    videos.setHomeimgalt(homeimgalt);
    videos.setAllowed_comm(allowed_comm);
    videos.setAllowed_rating(allowed_rating);
    videos.setHitstotal(hitstotal);
    videos.setTotal_rating(total_rating);
    videos.setClick_rating(click_rating);

    Long catId = Long.parseLong(CategoryId);
    VideoCategories categories = category.findById(catId).orElse(null);
    videos.setCategories(categories);

    videoRepository.save(videos);

    saveProfileImage(videos, homeimgfile);
    return videos;


    }

    @Override
    public Videos updateVideo(String currentalias, String author, String artist, int sourceid, int status, int archive, String newtitle, String newAlias,
                              String newhometext, String newvid_path, String newvid_type, String newvid_duration, MultipartFile homeimgfile,
                              String newhomeimgalt,String CategoryId) throws IOException, NotAnImageFileException, AliasExistException {

        Videos currentVideo = validateNewAliasVideo(currentalias,newAlias);
        currentVideo.setAuthor(author);
        currentVideo.setArtist(artist);
        currentVideo.setSourceid(sourceid);
        currentVideo.setAdd_time(new Date());
        currentVideo.setStatus(status);
        currentVideo.setArchive(archive);
        currentVideo.setTitle(newtitle);
        currentVideo.setAlias(newAlias);
        currentVideo.setHometext(newhometext);
        currentVideo.setVid_path(newvid_path);
        currentVideo.setVid_type(newvid_type);
        currentVideo.setVid_duration(newvid_duration);
        currentVideo.setHomeimgfile(
                ServletUriComponentsBuilder.fromCurrentContextPath().path(FileConstant.DEFAULT_USER_IMAGE_PATH + newtitle).toUriString()
        );;
        currentVideo.setHomeimgalt(newhomeimgalt);

        Long catId = Long.parseLong(CategoryId);
        VideoCategories categories = category.findById(catId).orElse(null);
        currentVideo.setCategories(categories);


        videoRepository.save(currentVideo);
        saveProfileImage(currentVideo, homeimgfile);
        System.out.println("id video: " + currentVideo.getId());
        return currentVideo;
       // System.out.println(currentVideo.getId());
    }


    @Override
    public Videos updateProfileImage(String alias, MultipartFile homeimgfile) throws AliasExistException, IOException, NotAnImageFileException {
        Videos videos = validateNewAliasVideo(alias, null);
        saveProfileImage(videos, homeimgfile);
        return videos;
    }

    @Override
    public void deleteVideo(long id) throws UserNotFoundException, IOException {
        if (!videoRepository.existsById(id)) {
            throw new UserNotFoundException("Videos does not exist");
        }
        Videos videos = videoRepository.findById(id).get();
        Path videoFolder = Paths.get(FileConstant.USER_FOLDER + videos.getTitle()).toAbsolutePath().normalize();
        FileUtils.deleteDirectory(new File(videoFolder.toString()));
        videoRepository.deleteById(id);
    }


    private void saveProfileImage(Videos videos, MultipartFile homeimgfile) throws IOException, NotAnImageFileException {
        if (homeimgfile != null) {
            if (!Arrays.asList(MimeTypeUtils.IMAGE_JPEG_VALUE, MimeTypeUtils.IMAGE_GIF_VALUE, MimeTypeUtils.IMAGE_PNG_VALUE).contains(homeimgfile.getContentType())) {
                throw new NotAnImageFileException(homeimgfile.getOriginalFilename() + " is not an image file");
            }

            // /user/ray/springAngularEcommerce2/users/
            Path userFolder = Paths.get(FileConstant.USER_FOLDER + videos.getTitle()).toAbsolutePath().normalize();
            if (!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
            }

            // delete if exist /user/ray/springAngularEcommerce2/users/{username}/{fileName.jpg}
            Files.deleteIfExists(Paths.get(userFolder + videos.getTitle() + "." + "jpg"));

            // save
            Files.copy(homeimgfile.getInputStream(), userFolder.resolve(videos.getTitle() + "." + "jpg"), REPLACE_EXISTING);

            // update new URL http://localhost:8080/api/user/image/{username}/{fileName.jpg}
            videos.setHomeimgfile(ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path(FileConstant.USER_IMAGE_PATH + videos.getTitle() + "/" + videos.getTitle() + "." + "jpg")
                    .toUriString()
            );
            videoRepository.save(videos);
            LOGGER.info("Save profile image: " + homeimgfile.getOriginalFilename());
        }
    }

    private Videos validateNewAliasVideo(String currentAlias, String newAlias) throws AliasExistException {

        Videos newVideoByAlias = videoRepository.findByAlias(newAlias);

        if (StringUtils.isNotBlank(currentAlias)) {

            Videos currentVideo = videoRepository.findByAlias(currentAlias);

            if (currentVideo == null) {
                throw new UsernameNotFoundException("No alias found by Videos " + currentVideo);
            }

            if (newVideoByAlias != null && !currentVideo.getId().equals(newVideoByAlias.getId())) {
                throw new AliasExistException("Alias already exist");
            }
            return currentVideo;
        }else {
            return  null;
        }
    }
}
