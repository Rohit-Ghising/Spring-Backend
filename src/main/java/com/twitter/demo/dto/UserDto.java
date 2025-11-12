package com.twitter.demo.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private Long id;
    private String fullName;
    private String image;
    private String website;
    private String email;
    private String birthDate;
    private String location;
    private String mobile;
    private String backgroundImage;
    private String bio;

    private boolean req_user;
    private boolean login_with_google;

    private List<UserDto>followers =new ArrayList<>();
    private List<UserDto> following=new ArrayList<>();
    private boolean followed;
    private boolean isVerified;


}
