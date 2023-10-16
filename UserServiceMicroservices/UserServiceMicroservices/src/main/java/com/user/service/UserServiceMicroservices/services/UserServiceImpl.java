package com.user.service.UserServiceMicroservices.services;

import com.user.service.UserServiceMicroservices.User.Hotels;
import com.user.service.UserServiceMicroservices.User.Rating;
import com.user.service.UserServiceMicroservices.User.UserEntity;
import com.user.service.UserServiceMicroservices.exception.ResourceNotFoundException;
import com.user.service.UserServiceMicroservices.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService{
   @Autowired
    private UserRepository userRepository;
   // create  RestTemplate for calling RestTemplate for communication
  @Autowired
   private RestTemplate restTemplate;

  private final Logger logger =  LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        String randomUserId = UUID.randomUUID().toString();
        userEntity.setUserId(randomUserId);
        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity > getAllUser() {
        //get all users with ratings and hotel details to find which hotels are find for which users

        List<UserEntity> users = userRepository.findAll();


        ArrayList<UserEntity> userDetails = new ArrayList<>();
        for (UserEntity data : users){
            UserEntity userEntity = new UserEntity();
            ArrayList<Rating> ratingsOfUser = restTemplate.getForObject(
                    "http://RATING-SERVICE/ratings/users/"+data.getUserId(),
                    ArrayList.class);

            logger.info("{}",ratingsOfUser);
            if(ratingsOfUser != null){
                ratingsOfUser.stream().map(rating -> {
                    ResponseEntity<Hotels> hotelDetails = restTemplate.getForEntity(
                            "http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),
                            Hotels.class);
                    Hotels hotels = hotelDetails.getBody();
                    logger.info("response status code : {} ",hotelDetails.getStatusCode());
                    rating.setHotels(hotels);
                    //return the rating
                    return  rating;
                }).collect(Collectors.toList());
            }
            userEntity.setUserId(data.getUserId());
            userEntity.setEmail(data.getEmail());
            userEntity.setAddress(data.getAddress());
            userEntity.setAbout(data.getAbout());
            userEntity.setMobNo(data.getMobNo());
            userEntity.setName(data.getName());
            userEntity.setRatings(ratingsOfUser);
            userDetails.add(userEntity);

        }


        return  userDetails;

//        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getSingleUser(String userId) {
        // get user from database with the help of user repository
        UserEntity user = (userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("the user id which you given not found on server  !!"+userId)));
        // fetch rating of the above user from Rating Service
        // http://localhost:8083/ratings/users/afa13df6-36a0-468d-b2ce-71517e52c7bd I will call this url from userService
//       ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);
//        logger.info("{}",ratingsOfUser);// this will only fetch the users and ratings but not fetching hotels.
        Rating[] ratingsOfUser = restTemplate.getForObject(
                "http://RATING-SERVICE/ratings/users/"+user.getUserId(),
                Rating[].class);
        logger.info("{}",ratingsOfUser);
        // conversion of Array to ArrayList
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
       List<Rating>  ratingList = ratings.stream().map(rating -> {
            //api call to the hotel service to get the hotel
          ResponseEntity<Hotels> hotelDetails = restTemplate.getForEntity(
                  "http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),
                  Hotels.class);
          Hotels hotels = hotelDetails.getBody();
          logger.info("response status code : {} ",hotelDetails.getStatusCode());
            // set the hotel to rating
           rating.setHotels(hotels);
            //return the rating
            return  rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return Optional.of(user);
    }
    @Override
    public String deleteUserById(String userId) {
        userRepository.deleteById(userId);
        return null;
    }


    @Override
    public UserEntity updateUserById(UserEntity userEntity) {
        UserEntity existingProduct = userRepository.findById(userEntity.getUserId()).orElse(null);
        existingProduct.setMobNo(userEntity.getMobNo());
        existingProduct.setName(userEntity.getName());
        existingProduct.setAddress(userEntity.getAddress());
        return userRepository.save(existingProduct);
    }

}
