package com.ray.ecommerce.controller;

import com.ray.ecommerce.dao.VideosPlaylistCatRepository;
import com.ray.ecommerce.domain.HttpResponse;
import com.ray.ecommerce.entity.PageInfo;
import com.ray.ecommerce.entity.PlaylistCat;
import com.ray.ecommerce.entity.Videos;
import com.ray.ecommerce.exception.*;
import com.ray.ecommerce.service.VideosPlaylistCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/playlistcat")
public class PlaylistCatController {

    private VideosPlaylistCatService playlistCatService;
    private VideosPlaylistCatRepository playlistCatRepository;

    @Autowired
    public PlaylistCatController(VideosPlaylistCatService playlistCatService, VideosPlaylistCatRepository playlistCatRepository) {
        this.playlistCatService = playlistCatService;
        this.playlistCatRepository = playlistCatRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllPlaylistCat(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue= "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<PlaylistCat> playlistCats = playlistCatRepository.findAll(paging);

        PageInfo myPage = new PageInfo(playlistCats.getNumber(), playlistCats.getTotalElements(), playlistCats.getTotalPages(), playlistCats.getSize());

        Map<String, Object> response = new HashMap<>();
        response.put("playlistCats", playlistCats);
        response.put("page", myPage);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> getPlaylistCatFindName(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue= "3") int size,
                                                                 @RequestParam("title") String title) {
        Pageable paging = PageRequest.of(page, size);

        Page<PlaylistCat> playlistCats = playlistCatRepository.findByTitleContaining(title,paging);

        PageInfo myPage = new PageInfo(playlistCats.getNumber(), playlistCats.getTotalElements(), playlistCats.getTotalPages(), playlistCats.getSize());

        Map<String, Object> response = new HashMap<>();
        response.put("playlistCats", playlistCats);
        response.put("page", myPage);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{alias}")
    public ResponseEntity<PlaylistCat> getAlias(@PathVariable("alias") String alias){
        PlaylistCat playlistCat = playlistCatRepository.findPlaylistCatByAlias(alias);
        return new ResponseEntity<>(playlistCat, HttpStatus.OK);
    }

    @PostMapping("/add")
    public  ResponseEntity<PlaylistCat> addNewPlayListCat(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name ="private_mode", required = false) String private_mode,
            @RequestParam(name ="numbers", required = false) String numbers,
            @RequestParam(name ="title", required = false) String title,
            @RequestParam(name ="alias", required = false) String alias,
            @RequestParam(name ="image", required = false) MultipartFile image,
            @RequestParam(name ="description", required = false) String description,
            @RequestParam(name ="weight", required = false) String weight,
            @RequestParam(name ="keywords", required = false) String keywords,
            @RequestParam(name ="hitstotal", required = false) String hitstotal,
            @RequestParam(name ="favorite", required = false) String favorite) throws IOException, NotAnImageFileException {
        PlaylistCat newplaylist = playlistCatService.addPlaylist(
                Integer.parseInt(status),
                Integer.parseInt(private_mode),
                Integer.parseInt(numbers),
                title, alias, image, description,
                Integer.parseInt(weight),
                keywords,
                Integer.parseInt(hitstotal),
                Integer.parseInt(favorite)
        );
        return  new ResponseEntity<>(newplaylist, HttpStatus.OK);
    }
    @PostMapping("/update")
    public  ResponseEntity<PlaylistCat>updatePlayListCat(
            @RequestParam(name = "currentAlias", required = false) String currentAlias,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name ="private_mode", required = false) String private_mode,
            @RequestParam(name ="numbers", required = false) String numbers,
            @RequestParam(name ="title", required = false) String title,
            @RequestParam(name ="alias", required = false) String alias,
            @RequestParam(name ="image", required = false) MultipartFile image,
            @RequestParam(name ="description", required = false) String description,
            @RequestParam(name ="keywords", required = false) String keywords) throws IOException, NotAnImageFileException {
        PlaylistCat updatePlaylist = playlistCatService.updatePlaylist(
                currentAlias,
                Integer.parseInt(status),
                Integer.parseInt(private_mode),
                Integer.parseInt(numbers),
                title, alias, image, description,keywords
        );
        return  new ResponseEntity<>(updatePlaylist, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteVideos(@PathVariable("id") Long id) throws UserNotFoundException, IOException {
        playlistCatService.deletePlaylist(id);
        return response(HttpStatus.OK, "Playlist Category has been deleted successfully");
    }


    @PostMapping("/updateProfileImage")
    public ResponseEntity<PlaylistCat> updateProfileImage(@RequestParam("image") MultipartFile homeimgfile)
            throws EmailExistException, IOException, UsernameExistException, NotAnImageFileException, AliasExistException {
        String title = SecurityContextHolder.getContext().getAuthentication().getName();
        PlaylistCat playlistCat = playlistCatService.updateProfileImage(title, homeimgfile);
        return new ResponseEntity<>(playlistCat, HttpStatus.OK);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),
                httpStatus, httpStatus.getReasonPhrase(), message), httpStatus);
    }
}
