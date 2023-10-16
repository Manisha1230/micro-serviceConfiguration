package com.user.service.UserServiceMicroservices.services;

import com.user.service.UserServiceMicroservices.User.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //create
    UserEntity saveUser(UserEntity userEntity);

    //get all User
    List<UserEntity> getAllUser();
    //get single user of given userId
    Optional<UserEntity> getSingleUser(String userId);

    //delete user
    String deleteUserById(String userId );
    //Update user
    UserEntity updateUserById(UserEntity userEntity);
}
