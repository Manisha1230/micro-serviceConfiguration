package com.ratingMicroservices.ratingMicro.ratingController;

import com.ratingMicroservices.ratingMicro.ratingEntity.RatingEntities;
import com.ratingMicroservices.ratingMicro.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsController {
    @Autowired
    private RatingService ratingService;
    @PostMapping("/create")
    public ResponseEntity<RatingEntities> createRating(@RequestBody  RatingEntities rating){
     return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }
    @GetMapping("/rating")
    public ResponseEntity<List<RatingEntities>> getRatings(){
        return ResponseEntity.ok(ratingService.getRatings());
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<RatingEntities>> getRatingByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<RatingEntities>> getRatingByHotelId(@PathVariable String hotelId){
     return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

}
