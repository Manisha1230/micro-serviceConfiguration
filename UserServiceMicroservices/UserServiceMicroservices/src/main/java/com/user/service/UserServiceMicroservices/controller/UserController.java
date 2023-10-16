package com.user.service.UserServiceMicroservices.controller;

import com.user.service.UserServiceMicroservices.User.UserEntity;
import com.user.service.UserServiceMicroservices.exception.ResourceNotFoundException;
import com.user.service.UserServiceMicroservices.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usermicro")
public class UserController {
    @Autowired
    private UserService userService;

    private final Logger logger =  LoggerFactory.getLogger(UserController.class);
    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity){
       UserEntity user = userService.saveUser(userEntity);
       return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<Optional<UserEntity>> getSingleUser(@PathVariable String userId){
        logger.info("Get Single User Handler: UserController");
        Optional<UserEntity> existingUser = userService.getSingleUser(userId);
       return  ResponseEntity.ok(existingUser);
    }
    //creating fall back method for circuitBreaker
//    public ResponseEntity<UserEntity> ratingHotelFallback(String userId, Exception ex){
//       logger.info("Fallback is executed because service is down : ",ex.getMessage());
//        UserEntity userEntity = UserEntity.builder()
//                .email("dummy@gmail.com")
//                .name("Dummy")
//                .about("This user is created dummy because some service is down")
//                .userId("145678")
//                .build();
//        return new ResponseEntity<>(userEntity, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUser(){
       List<UserEntity> allUser = userService.getAllUser();
       return ResponseEntity.ok(allUser);
    }
    @DeleteMapping("/user/{id}")
    public String deleteUserById(@PathVariable ("id")String userId){
        String temp = userService.deleteUserById(userId);
        if(temp==null) {
            return "User not found";
        }
        userService.deleteUserById(temp);
        return "User deleted successfully";
    }
    @PutMapping("/user")
    public String UpdateUser(@RequestBody UserEntity userEntity){
        if(userEntity.getUserId() == null){
            throw new ResourceNotFoundException("User Id is null you cannot update data  ");
        }
        userService.updateUserById(userEntity);
        return "user update sucessfully";
   }
}
