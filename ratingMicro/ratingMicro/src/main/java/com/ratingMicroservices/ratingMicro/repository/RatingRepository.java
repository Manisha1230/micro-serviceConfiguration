package com.ratingMicroservices.ratingMicro.repository;

import com.ratingMicroservices.ratingMicro.ratingEntity.RatingEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntities, String> {
    List<RatingEntities> findByUserId(String userId);
    List<RatingEntities> findByHotelId(String hotelId);
}
