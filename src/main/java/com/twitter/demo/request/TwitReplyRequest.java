package com.twitter.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TwitReplyRequest {
    private String content ;
    private Long twitId;
    private LocalDateTime createdAt;
    public  String image;
}
