package com.ray.ecommerce.service;

import com.ray.ecommerce.entity.PlaylistCat;
import com.ray.ecommerce.entity.Videos;
import com.ray.ecommerce.exception.EmailExistException;
import com.ray.ecommerce.exception.NotAnImageFileException;
import com.ray.ecommerce.exception.UserNotFoundException;
import com.ray.ecommerce.exception.UsernameExistException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideosPlaylistCatService {
    PlaylistCat addPlaylist(int status, int private_mode, int numbers, String title, String alias, MultipartFile image,
                            String description, int weight, String keywords, int hitstotal, int favorite) throws IOException, NotAnImageFileException;

    PlaylistCat updatePlaylist(String currentAlias,int newstatus, int newprivate_mode, int newnumbers, String newtitle, String alias, MultipartFile image,
                               String newdescription, int newweight, String newkeywords, int hitstotal, int favorite) throws IOException, NotAnImageFileException;

    PlaylistCat updateProfileImage(String title, MultipartFile image) throws EmailExistException, UsernameExistException, IOException, NotAnImageFileException;

    void deletePlaylist(long id) throws UserNotFoundException, IOException;
}
