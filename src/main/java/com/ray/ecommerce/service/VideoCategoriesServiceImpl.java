package com.ray.ecommerce.service;

import com.ray.ecommerce.constant.FileConstant;
import com.ray.ecommerce.dao.VideoCatRepository;
import com.ray.ecommerce.entity.VideoCategories;
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

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Transactional
public class VideoCategoriesServiceImpl implements VideoCategoriesService{
    private Logger LOGGER = LoggerFactory.getLogger(VideoServiceImpl.class);
    private VideoCatRepository catRepository;


    @Autowired
    public VideoCategoriesServiceImpl(VideoCatRepository catRepository) {
        this.catRepository = catRepository;
    }


    @Override
    public VideoCategories addCategory(String title, String alias, String description, MultipartFile image, int weight, String keywords) throws IOException, NotAnImageFileException {

        VideoCategories categories = new VideoCategories();
        categories.setTitle(title);
        categories.setAlias(alias);
        categories.setDescription(description);
        categories.setImage(
                ServletUriComponentsBuilder.fromCurrentContextPath().path(FileConstant.DEFAULT_USER_IMAGE_PATH + title).toUriString()
        );
        categories.setWeight(weight);
        categories.setAdd_time(new Date());
        catRepository.save(categories);
        saveProfileImage(categories, image);
        return categories;
    }

    @Override
    public VideoCategories updateCategory(String currentAlias, String newTitle, String newAlias, String newDescription, MultipartFile image, String newKeywords) throws AliasExistException, IOException, NotAnImageFileException {
       VideoCategories currentCategories = validateNewAliasCategories(currentAlias,newAlias);
        currentCategories.setTitle(newTitle);
        currentCategories.setAlias(newAlias);
        currentCategories.setDescription(newDescription);
        currentCategories.setKeywords(newKeywords);
        catRepository.save(currentCategories);
        saveProfileImage(currentCategories, image);
        return currentCategories;
    }

    @Override
    public VideoCategories updateProfileImage(String alias, MultipartFile image) throws AliasExistException, IOException, NotAnImageFileException {
        VideoCategories videoCategories = validateNewAliasCategories(alias, null);
        saveProfileImage(videoCategories, image);
        return videoCategories;
    }

    @Override
    public void deleteCategory(long id) throws UserNotFoundException, IOException {
        if (!catRepository.existsById(id)) {
            throw new UserNotFoundException("Category does not exist");
        }
        VideoCategories videoCategories = catRepository.findById(id).get();
        Path userFolder = Paths.get(FileConstant.USER_FOLDER + videoCategories.getAlias()).toAbsolutePath().normalize();
        FileUtils.deleteDirectory(new File(userFolder.toString()));
        catRepository.deleteById(id);
    }

    private void saveProfileImage(VideoCategories videoCategories, MultipartFile image) throws NotAnImageFileException, IOException {
        if (image != null) {
            if (!Arrays.asList(MimeTypeUtils.IMAGE_JPEG_VALUE, MimeTypeUtils.IMAGE_GIF_VALUE, MimeTypeUtils.IMAGE_PNG_VALUE).contains(image.getContentType())) {
                throw new NotAnImageFileException(image.getOriginalFilename() + " is not an image file");
            }

            // /user/ray/springAngularEcommerce2/users/
            Path userFolder = Paths.get(FileConstant.USER_FOLDER + videoCategories.getTitle()).toAbsolutePath().normalize();
            if (!Files.exists(userFolder)) {
                Files.createDirectories(userFolder);
            }

            // delete if exist /user/ray/springAngularEcommerce2/users/{username}/{fileName.jpg}
            Files.deleteIfExists(Paths.get(userFolder + videoCategories.getTitle() + "." + "jpg"));

            // save
            Files.copy(image.getInputStream(), userFolder.resolve(videoCategories.getTitle() + "." + "jpg"), REPLACE_EXISTING);

            // update new URL http://localhost:8080/api/user/image/{username}/{fileName.jpg}
            videoCategories.setImage(ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path(FileConstant.USER_IMAGE_PATH + videoCategories.getTitle() + "/" + videoCategories.getTitle() + "." + "jpg")
                    .toUriString()
            );
            catRepository.save(videoCategories);
            LOGGER.info("Save profile image: " + image.getOriginalFilename());
        }
    }


    private VideoCategories validateNewAliasCategories(String currentAlias, String newAlias) throws AliasExistException {

        VideoCategories newCategoryByAlias = catRepository.findVideoCategoriesByAlias(newAlias);

        if (StringUtils.isNotBlank(currentAlias)) {

            VideoCategories currentA = catRepository.findVideoCategoriesByAlias(newAlias);

            if (currentAlias == null) {
                throw new UsernameNotFoundException("No Alias found by Category " + currentAlias);
            }

            if (newCategoryByAlias != null && !currentA.getId().equals(newCategoryByAlias.getId())) {
                throw new AliasExistException("Alias already exist");
            }
            return currentA;
        }else {
            return  null;
        }
    }
}
