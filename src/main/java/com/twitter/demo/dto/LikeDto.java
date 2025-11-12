package com.twitter.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class LikeDto {
    private Long id;
    private UserDto user;

    private TwitDto twit;

}
