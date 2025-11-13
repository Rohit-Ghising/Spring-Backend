package com.twitter.demo.controller;

import com.twitter.demo.dto.LikeDto;
import com.twitter.demo.exception.TwitException;
import com.twitter.demo.exception.UserException;
import com.twitter.demo.mapper.LikeDtoMapper;
import com.twitter.demo.model.Like;
import com.twitter.demo.model.User;
import com.twitter.demo.service.LikeService;
import com.twitter.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class LikeConroller {
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;



    @PostMapping("/{twitId}/likes")
    public ResponseEntity<LikeDto> likeTwit(@PathVariable Long twitId,
                                            @RequestHeader("Authorization") String jwt)
            throws TwitException, UserException {
        User user =userService.findUserProfileByJwt(jwt);
        Like like = likeService.likeTwit(twitId,user);
        LikeDto likeDto= LikeDtoMapper.toLikeDto(like,user);
        return new ResponseEntity<LikeDto>(likeDto, HttpStatus.CREATED);




    }
    @GetMapping("/twit/{twitId}")

    public ResponseEntity<List<LikeDto>> getAllLikes(@PathVariable Long twitId,
                                                    @RequestHeader("Authorization") String jwt)
            throws TwitException, UserException {
        User user =userService.findUserProfileByJwt(jwt);
      List  <Like> like = likeService.getAllLikes(twitId);
       List <LikeDto> likeDtos= LikeDtoMapper.toLikeDtos(like,user);
        return new ResponseEntity<>(likeDtos, HttpStatus.CREATED);




    }

}
