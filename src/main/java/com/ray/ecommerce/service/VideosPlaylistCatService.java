package com.ray.ecommerce.service;

import com.ray.ecommerce.entity.PlaylistCat;
import com.ray.ecommerce.entity.Videos;
import com.ray.ecommerce.exception.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideosPlaylistCatService {
    PlaylistCat addPlaylist(int status, int private_mode, int numbers, String title, String alias, MultipartFile image,
                            String description, int weight, String keywords, int hitstotal, int favorite) throws IOException, NotAnImageFileException;

    PlaylistCat updatePlaylist(String currentAlias,int newstatus, int newprivate_mode, int newnumbers, String newtitle, String alias, MultipartFile image,
                               String newdescription, String newkeywords ) throws IOException, NotAnImageFileException;

    PlaylistCat updateProfileImage(String alias, MultipartFile image) throws EmailExistException, UsernameExistException, IOException, NotAnImageFileException, AliasExistException;

    void deletePlaylist(long id) throws UserNotFoundException, IOException;
}
