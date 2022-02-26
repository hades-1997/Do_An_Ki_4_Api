package com.ray.ecommerce.controller;

import com.ray.ecommerce.dao.RateRepository;
import com.ray.ecommerce.domain.HttpResponse;
import com.ray.ecommerce.entity.Rate;
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

    @Autowired
    public RateController(RateRepository rateRepository, RateService rateService) {
        this.rateRepository = rateRepository;
        this.rateService = rateService;
    }

    @GetMapping("/find")
    public  ResponseEntity<Rate> getRate(@RequestParam(value = "userId", required = false) int userId,
                                         @RequestParam(value = "playListId", required = false) int playListId){
        Rate finRate =   rateRepository.findRateByUserIdAndPlayListId(userId, playListId );
        return  new ResponseEntity<>(finRate, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Rate> getAddRate(@RequestParam(value = "userId", required = false) String userId,
                                             @RequestParam(value = "playListId", required = false) String playListId,
                                           @RequestParam(value = "StatusId", required = false) String StatusId,
                                             @RequestParam(value = "star", required = false) String star){
       try{
           Rate addNewRate = rateService.addRate(
                   Integer.parseInt(userId),
                   Integer.parseInt(playListId),
                   Long.parseLong(StatusId),
                   Integer.parseInt(star)
           );
           return  new ResponseEntity<>(addNewRate, HttpStatus.OK);
       }catch (Exception e){
           System.out.println(e.getMessage());
           return null;
       }
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),
                httpStatus, httpStatus.getReasonPhrase(), message), httpStatus);
    }
}
