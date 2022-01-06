package com.ray.ecommerce.service;

import com.ray.ecommerce.constant.FileConstant;
import com.ray.ecommerce.dao.VideosPlaylistCatRepository;
import com.ray.ecommerce.entity.PlaylistCat;
import com.ray.ecommerce.entity.Videos;
import com.ray.ecommerce.exception.EmailExistException;
import com.ray.ecommerce.exception.NotAnImageFileException;
import com.ray.ecommerce.exception.UserNotFoundException;
import com.ray.ecommerce.exception.UsernameExistException;
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

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Transactional
public class VideosPlaylistCatServiceImpl implements VideosPlaylistCatService{

    private Logger LOGGER = LoggerFactory.getLogger(VideoServiceImpl.class);
    private VideosPlaylistCatRepository videosPlaylistCatRepository;

    @Autowired
    public VideosPlaylistCatServiceImpl(VideosPlaylistCatRepository videosPlaylistCatRepository) {
        this.videosPlaylistCatRepository = videosPlaylistCatRepository;
    }


    @Override
    public PlaylistCat addPlaylist(int status, int private_mode, int numbers, String title, String alias, MultipartFile image, String description, int weight, String keywords, int hitstotal, int favorite) throws IOException, NotAnImageFileException {

        PlaylistCat playlistCat = new PlaylistCat();
        playlistCat.setStatus(status);
        playlistCat.setPrivate_mode(private_mode);
        playlistCat.setNumbers(numbers);
        playlistCat.setTitle(title);
        playlistCat.setAlias(alias);
        playlistCat.setImage(
                ServletUriComponentsBuilder.fromCurrentContextPath().path(FileConstant.DEFAULT_VIDEO_IMAGE_PATH + title).toUriString()
        );;
        playlistCat.setDescription(description);
        playlistCat.setWeight(weight);
        playlistCat.setKeywords(keywords);
        playlistCat.setHitstotal(hitstotal);
        playlistCat.setFavorite(favorite);
        playlistCat.setAdd_time(new Date());
        videosPlaylistCatRepository.save(playlistCat);
        saveProfileImage(playlistCat, image);
        return playlistCat;
    }

    @Override
    public PlaylistCat updatePlaylist(String currentAlias, int newstatus, int newprivate_mode, int newnumbers,
                                      String newtitle, String alias, MultipartFile image, String newdescription,
                                      int newweight, String newkeywords, int hitstotal, int favorite) throws IOException, NotAnImageFileException {

        PlaylistCat updatePlayListCat = videosPlaylistCatRepository.findByAlias(currentAlias);
        updatePlayListCat.setStatus(newstatus);
        updatePlayListCat.setPrivate_mode(newprivate_mode);
        updatePlayListCat.setNumbers(newnumbers);
        updatePlayListCat.setTitle(newtitle);
        updatePlayListCat.setAlias(alias);
        updatePlayListCat.setImage(
                ServletUriComponentsBuilder.fromCurrentContextPath().path(FileConstant.DEFAULT_VIDEO_IMAGE_PATH + newtitle).toUriString()
        );;
        updatePlayListCat.setDescription(newdescription);
        updatePlayListCat.setWeight(newweight);
        updatePlayListCat.setKeywords(newkeywords);
        updatePlayListCat.setHitstotal(hitstotal);
        updatePlayListCat.setFavorite(favorite);

        updatePlayListCat.setAdd_time(new Date());
        videosPlaylistCatRepository.save(updatePlayListCat);
        saveProfileImage(updatePlayListCat, image);
        return updatePlayListCat;
    }

    @Override
    public PlaylistCat updateProfileImage(String alias, MultipartFile image) throws EmailExistException, UsernameExistException, IOException, NotAnImageFileException {
        PlaylistCat playlistCat = validateNewAlias(alias);
        saveProfileImage(playlistCat, image);
        return playlistCat;
    }

    @Override
    public void deletePlaylist(long id) throws UserNotFoundException, IOException {
        if (!videosPlaylistCatRepository.existsById(id)) {
            throw new UserNotFoundException("User does not exist");
        }
        PlaylistCat playlistCat = videosPlaylistCatRepository.findById(id).get();
        Path PlaylistFolder = Paths.get(FileConstant.USER_FOLDER + playlistCat.getTitle()).toAbsolutePath().normalize();
        FileUtils.deleteDirectory(new File(PlaylistFolder.toString()));
        videosPlaylistCatRepository.deleteById(id);
    }


    private void saveProfileImage(PlaylistCat playlistCat, MultipartFile image) throws IOException, NotAnImageFileException {
        if (image != null) {
            if (!Arrays.asList(MimeTypeUtils.IMAGE_JPEG_VALUE, MimeTypeUtils.IMAGE_GIF_VALUE, MimeTypeUtils.IMAGE_PNG_VALUE).contains(image.getContentType())) {
                throw new NotAnImageFileException(image.getOriginalFilename() + " is not an image file");
            }

            // /user/ray/springAngularEcommerce2/users/
            Path userFolder = Paths.get(FileConstant.USER_FOLDER + playlistCat.getTitle()).toAbsolutePath().normalize();
            if (!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
            }

            // delete if exist /user/ray/springAngularEcommerce2/users/{username}/{fileName.jpg}
            Files.deleteIfExists(Paths.get(userFolder + playlistCat.getTitle() + "." + "jpg"));

            // save
            Files.copy(image.getInputStream(), userFolder.resolve(playlistCat.getTitle() + "." + "jpg"), REPLACE_EXISTING);

            // update new URL http://localhost:8080/api/user/image/{username}/{fileName.jpg}
            playlistCat.setImage(ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path(FileConstant.USER_IMAGE_PATH + playlistCat.getTitle() + "/" + playlistCat.getTitle() + "." + "jpg")
                    .toUriString()
            );
            videosPlaylistCatRepository.save(playlistCat);
            LOGGER.info("Save profile image: " + image.getOriginalFilename());
        }
    }

    private PlaylistCat validateNewAlias(String currentalias) throws UsernameExistException {

        PlaylistCat newPlaylistByalias = videosPlaylistCatRepository.findByAlias(currentalias);

        if (StringUtils.isNotBlank(currentalias)) {

            PlaylistCat currentAlias = videosPlaylistCatRepository.findByAlias(currentalias);

            if (currentAlias == null) {
                throw new UsernameNotFoundException("No user found by user " + currentAlias);
            }

            if (newPlaylistByalias != null && !currentAlias.getId().equals(newPlaylistByalias.getId())) {
                throw new UsernameExistException("Title already exist");
            }
            return currentAlias;
        }else {
            return  null;
        }
    }
}
