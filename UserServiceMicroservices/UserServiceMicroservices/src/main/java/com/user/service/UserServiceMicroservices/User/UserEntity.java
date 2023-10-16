package com.user.service.UserServiceMicroservices.User;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
    private String userId;
    private String name;
    private String email;
    private String about;
    private String address;
    private String mobNo;
    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
