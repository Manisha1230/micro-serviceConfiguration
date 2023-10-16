package com.ratingMicroservices.ratingMicro.services;

import com.ratingMicroservices.ratingMicro.ratingEntity.RatingEntities;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    //create
    RatingEntities createRating(RatingEntities ratings);

    //get all ratings
    List<RatingEntities> getRatings();

    //get all by userId
    List<RatingEntities> getRatingByUserId(String userId);

    //get all by hotel
    List<RatingEntities> getRatingByHotelId(String hotelId);
}

