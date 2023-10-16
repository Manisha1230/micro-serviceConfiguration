package com.ratingMicroservices.ratingMicro.services;

import com.ratingMicroservices.ratingMicro.ratingEntity.RatingEntities;
import com.ratingMicroservices.ratingMicro.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService{
  @Autowired
   private RatingRepository ratingRepository;
    @Override
    public RatingEntities createRating(RatingEntities ratings) {
        String randomId = UUID.randomUUID().toString();
        ratings.setRatingId(randomId);
        return ratingRepository.save(ratings);
    }

    @Override
    public List<RatingEntities> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<RatingEntities> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<RatingEntities> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
