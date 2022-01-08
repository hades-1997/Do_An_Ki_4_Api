package com.ray.ecommerce.service;


import com.ray.ecommerce.entity.VideoCategories;
import com.ray.ecommerce.exception.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

public interface VideoCategoriesService {

    VideoCategories addCategory(String title, String alias, String description, MultipartFile image, int weight, String keywords) throws IOException, NotAnImageFileException;

    VideoCategories updateCategory(String currentAlias, String newTitle, String newAlias, String newDescription, MultipartFile image, String newKeywords) throws AliasExistException, IOException, NotAnImageFileException;

    VideoCategories updateProfileImage(String alias, MultipartFile image) throws EmailExistException, UsernameExistException, IOException, NotAnImageFileException, AliasExistException;

    void deleteCategory(long id) throws UserNotFoundException, IOException;
}
