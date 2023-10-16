package com.user.service.UserServiceMicroservices.repository;

import com.user.service.UserServiceMicroservices.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,String> {
    // if we want to write any custom query or method

}
