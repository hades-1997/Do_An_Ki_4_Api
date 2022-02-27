package com.ray.ecommerce.controller;

import com.ray.ecommerce.dao.RateRepository;
import com.ray.ecommerce.dao.StatusPlaylistRepository;
import com.ray.ecommerce.domain.HttpResponse;
import com.ray.ecommerce.entity.Rate;
import com.ray.ecommerce.entity.StatusPlaylist;
import com.ray.ecommerce.exception.AliasExistException;
import com.ray.ecommerce.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/rate")

public class RateController {

    private RateRepository rateRepository;
    private RateService rateService;
    private StatusPlaylistRepository statusPlaylistRepository;

    @Autowired
    public RateController(RateRepository rateRepository, RateService rateService, StatusPlaylistRepository statusPlaylistRepository) {
        this.rateRepository = rateRepository;
        this.rateService = rateService;
        this.statusPlaylistRepository = statusPlaylistRepository;
    }

    @GetMapping("/find")
    public  ResponseEntity<Rate> getRate(@RequestParam(value = "userId", required = false) int userId,
                                         @RequestParam(value = "playListId", required = false) int playListId){
        Rate finRate =  rateRepository.findRateByUserIdAndPlayListId(userId, playListId );
        return  new ResponseEntity<>(finRate, HttpStatus.OK);
    }
    @GetMapping("/find/status")
    public ResponseEntity<StatusPlaylist> getStatus(@RequestParam(value = "statusId", required = false) Long statusId){
        StatusPlaylist statusPlaylist = statusPlaylistRepository.findById(statusId).orElse(null);
        return  new ResponseEntity<>(statusPlaylist, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Rate> countStatus( @RequestParam(value = "playListId", required = false) int playListId,
                                             @RequestParam(value = "isStatus", required = false) String isStatus){
        boolean check = Boolean.parseBoolean("0");
        long count = rateRepository.countByPlayListIdAndIsStatus(playListId, true);
        return  new ResponseEntity(count, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Rate> getAddRate(@RequestParam(value = "userId", required = false) String userId,
                                             @RequestParam(value = "playListId", required = false) String playListId,
                                           @RequestParam(value = "isStatus", required = false) String isStatus,
                                             @RequestParam(value = "star", required = false) String star){
       try{
           Rate addNewRate = rateService.addRate(
                   Integer.parseInt(userId),
                   Integer.parseInt(playListId),
                   Boolean.parseBoolean(isStatus),
                   Integer.parseInt(star)
           );
           return  new ResponseEntity<>(addNewRate, HttpStatus.OK);
       }catch (Exception e){
           System.out.println(e.getMessage());
           return null;
       }
    }

    @PostMapping("/update")
    public ResponseEntity<Rate> UpdateRate(@RequestParam(value = "currentId", required = false) String currentId,
                                           @RequestParam(value = "newIsStatus") String newIsStatus,
                                           @RequestParam(value = "newStar", required = false) String newStar) throws AliasExistException {

        Rate updateRate = rateService.updateRate(
                Long.parseLong(currentId),
                Boolean.parseBoolean(newIsStatus),
                Integer.parseInt(newStar)
        );
        return  new ResponseEntity<>(updateRate, HttpStatus.OK);
    }
    //public
    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),
                httpStatus, httpStatus.getReasonPhrase(), message), httpStatus);
    }
}
