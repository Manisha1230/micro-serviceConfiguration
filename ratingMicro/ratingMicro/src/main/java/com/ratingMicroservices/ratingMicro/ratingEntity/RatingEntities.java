package com.ratingMicroservices.ratingMicro.ratingEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RatingEntities {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ratingId;
    private String userId;
    private String hotelId;
    private int ratings;
    private String feedback;
}
