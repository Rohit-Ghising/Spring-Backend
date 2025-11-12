package com.twitter.demo.controller;

import com.twitter.demo.dto.TwitDto;
import com.twitter.demo.service.TwitService;
import com.twitter.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TwitController {
    @Autowired
    private TwitService twitService;
    private UserService userService;
    public ResponseEntity<TwitDto>


}
