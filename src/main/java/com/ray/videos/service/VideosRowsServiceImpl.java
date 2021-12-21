package com.ray.videos.service;

import com.ray.videos.constant.FileConstant;
import com.ray.videos.dao.VideosCategoriesRepository;
import com.ray.videos.dao.VideosRowsRepository;
import com.ray.videos.domain.User;
import com.ray.videos.entity.VideosCat;
import com.ray.videos.entity.VideosRows;
import com.ray.videos.exception.NotAnImageFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Transactional
//@Qualifier("myUserDetailsService")
public class VideosRowsServiceImpl implements VideosRowsService{

    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private VideosRowsRepository videosRowsRepository;
    private VideosCategoriesRepository categoriesRepository;

    @Autowired
    public VideosRowsServiceImpl(VideosRowsRepository videosRowsRepository, VideosCategoriesRepository categoriesRepository) {
        this.videosRowsRepository = videosRowsRepository;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public VideosRows findVideos(String title) {
        return videosRowsRepository.findVideos(title);
    }

    @Override
    public VideosRows AddNewVideos(String author, String artist, int sourceid, Date add_time, int status, int archive, String title, String alias, String hometext, String vid_path, String vid_duration,
                               MultipartFile homeimgfile, String homeimgalt, String allowed_comm, int allowed_rating, int hitstotal, int total_rating, int click_rating, String categoryId) throws IOException, NotAnImageFileException {

        VideosRows videosRows  = new VideosRows();
        videosRows.setAuthor(author);
        videosRows.setArtist(artist);
        videosRows.setSourceid(sourceid);
        videosRows.setAdd_time(new Date());
        videosRows.setStatus(0);
        videosRows.setArchive(archive);
        videosRows.setTitle(title);
        videosRows.setAlias(alias);
        videosRows.setHometext(hometext);
        videosRows.setVid_path(vid_path);
        videosRows.setVid_duration(vid_duration);
        videosRows.setHomeimgfile(
                // http://localhost:8080/api/user/image/profile/{username}  => https://robohash.org/{username}
                ServletUriComponentsBuilder.fromCurrentContextPath().path(FileConstant.DEFAULT_VIDEOS_IMAGE_PATH + title).toUriString()
        );;
        videosRows.setHomeimgalt(homeimgalt);
        videosRows.setAllowed_comm(allowed_comm);
        videosRows.setAllowed_rating(allowed_rating);
        videosRows.setHitstotal(hitstotal);
        videosRows.setTotal_rating(total_rating);
        videosRows.setClick_rating(click_rating);

        //get category
        int catId = Integer.parseInt(videosRows.CategoryId);
        VideosCat videosCat = categoriesRepository.findById(catId);

        videosRows.setVideosCat(videosCat);

        videosRowsRepository.save(videosRows);

        saveProfileImage(videosRows, homeimgfile);

        return videosRows;
    }

    private void saveProfileImage(VideosRows videosRows, MultipartFile profileImage) throws NotAnImageFileException, IOException {
        if (profileImage != null) {
            if (!Arrays.asList(MimeTypeUtils.IMAGE_JPEG_VALUE, MimeTypeUtils.IMAGE_GIF_VALUE, MimeTypeUtils.IMAGE_PNG_VALUE).contains(profileImage.getContentType())) {
                throw new NotAnImageFileException(profileImage.getOriginalFilename() + " is not an image file");
            }

            // /user/ray/springAngularEcommerce2/users/
            Path userFolder = Paths.get(FileConstant.USER_FOLDER + videosRows.getTitle()).toAbsolutePath().normalize();
            if (!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
            }

            // delete if exist /user/ray/springAngularEcommerce2/users/{username}/{fileName.jpg}
            Files.deleteIfExists(Paths.get(userFolder + videosRows.getTitle() + "." + "jpg"));

            // save
            Files.copy(profileImage.getInputStream(), userFolder.resolve(videosRows.getTitle() + "." + "jpg"), REPLACE_EXISTING);

            // update new URL http://localhost:8080/api/user/image/{username}/{fileName.jpg}
            videosRows.setHomeimgfile(ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path(FileConstant.USER_IMAGE_PATH + videosRows.getTitle() + "/" + videosRows.getTitle() + "." + "jpg")
                    .toUriString()
            );
            videosRowsRepository.save(videosRows);
            //LOGGER.info("Save profile image: " + profileImage.getOriginalFilename());
        }
    }

}
