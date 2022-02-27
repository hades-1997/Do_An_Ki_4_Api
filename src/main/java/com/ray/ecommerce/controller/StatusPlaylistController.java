package com.ray.ecommerce.controller;

import com.ray.ecommerce.dao.StatusPlaylistRepository;
import com.ray.ecommerce.domain.HttpResponse;
import com.ray.ecommerce.entity.StatusPlaylist;
import com.ray.ecommerce.entity.Videos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/statusPlaylist")
public class StatusPlaylistController {

    private StatusPlaylistRepository statusPlaylistRepository;

    public StatusPlaylistController(StatusPlaylistRepository statusPlaylistRepository) {
        this.statusPlaylistRepository = statusPlaylistRepository;
    }

    @GetMapping("/list")
    public  ResponseEntity<List<StatusPlaylist>> getAllStatusPlaylist(){
        List<StatusPlaylist> statusPlaylists = statusPlaylistRepository.findAll();
        return new ResponseEntity<>(statusPlaylists, HttpStatus.OK);
    }

    //public
    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),
                httpStatus, httpStatus.getReasonPhrase(), message), httpStatus);
    }
}
