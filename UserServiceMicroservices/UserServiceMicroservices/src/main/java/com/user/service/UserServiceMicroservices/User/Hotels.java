package com.user.service.UserServiceMicroservices.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotels {
    private String hotelId;
    private String hotelName;
    private  String location;
    private String about;
    private  String address;
}
