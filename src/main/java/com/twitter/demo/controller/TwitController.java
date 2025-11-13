package com.twitter.demo.controller;

import com.twitter.demo.dto.TwitDto;
import com.twitter.demo.exception.TwitException;
import com.twitter.demo.exception.UserException;
import com.twitter.demo.mapper.TwitDtoMapper;
import com.twitter.demo.model.Twit;
import com.twitter.demo.model.User;
import com.twitter.demo.request.TwitReplyRequest;
import com.twitter.demo.response.ApiResponse;
import com.twitter.demo.service.TwitService;
import com.twitter.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TwitController {
    @Autowired
    private TwitService twitService;
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<TwitDto> createTwit(@RequestBody Twit req,
                                              @RequestHeader("Authorization")String jwt)
            throws UserException, TwitException{
        User user =userService.findUserProfileByJwt(jwt);
        Twit twit= twitService.createTwit(req,user);
        TwitDto twitDto= TwitDtoMapper.toTwitDto(twit,user);
        return new ResponseEntity<>(twitDto, HttpStatus.CREATED);

    }

    @PostMapping("/reply")
    public ResponseEntity<TwitDto> replyTwit(@RequestBody TwitReplyRequest req,
                                              @RequestHeader("Authorization")String jwt)
            throws UserException, TwitException{
        User user =userService.findUserProfileByJwt(jwt);
        Twit twit= twitService.createReply(req,user);
        TwitDto twitDto= TwitDtoMapper.toTwitDto(twit,user);
        return new ResponseEntity<>(twitDto, HttpStatus.CREATED);

    }

    @PutMapping("/{twitId}/retweet")
    public ResponseEntity<TwitDto> reTwit(@PathVariable Long twitId, TwitReplyRequest req,
                                             @RequestHeader("Authorization")String jwt)
            throws UserException, TwitException{
        User user =userService.findUserProfileByJwt(jwt);
        Twit twit= twitService.retwit(twitId,user);
        TwitDto twitDto= TwitDtoMapper.toTwitDto(twit,user);
        return new ResponseEntity<>(twitDto, HttpStatus.OK);

    }


    @GetMapping("/{twitId}/")
    public ResponseEntity<TwitDto> findTwitById(@PathVariable Long twitId, TwitReplyRequest req,
                                          @RequestHeader("Authorization")String jwt)
            throws UserException, TwitException{
        User user =userService.findUserProfileByJwt(jwt);
        Twit twit= twitService.findById(twitId);
        TwitDto twitDto= TwitDtoMapper.toTwitDto(twit,user);
        return new ResponseEntity<>(twitDto, HttpStatus.OK);

    }
    @DeleteMapping("/{twitId}/")
    public ResponseEntity<ApiResponse> deleteTwit(@PathVariable Long twitId, TwitReplyRequest req,
                                                @RequestHeader("Authorization")String jwt)
            throws UserException, TwitException{
        User user =userService.findUserProfileByJwt(jwt);
         twitService.deleteTwitById(twitId,user.getId());
      ApiResponse res = new ApiResponse();
      res.setMessage("Twit Deleted sucesfully");
      res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }



    @GetMapping("/")
    public ResponseEntity<List<TwitDto>> getAllTwits(
                                                @RequestHeader("Authorization")String jwt)
            throws UserException, TwitException{
        User user =userService.findUserProfileByJwt(jwt);
      List<Twit>  twits= twitService.findAllTwit();
       List < TwitDto> twitDtos= TwitDtoMapper.twitDtos(twits,user);
        return new ResponseEntity<>(twitDtos, HttpStatus.OK);

    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TwitDto>> getUsersAllTwits( @PathVariable Long userId,
            @RequestHeader("Authorization")String jwt)
            throws UserException, TwitException{
        User user =userService.findUserProfileByJwt(jwt);
        List<Twit>  twits= twitService.getUserTwit(user);
        List < TwitDto> twitDtos= TwitDtoMapper.twitDtos(twits,user);
        return new ResponseEntity<>(twitDtos, HttpStatus.OK);

    }

    @GetMapping("/{userId}/likes")
    public ResponseEntity<List<TwitDto>> findTwitByLikesContainsUsers(
            @RequestHeader("Authorization")String jwt)
            throws UserException, TwitException{
        User user =userService.findUserProfileByJwt(jwt);
        List<Twit>  twits= twitService.findByLikesContainUser(user);
        List < TwitDto> twitDtos= TwitDtoMapper.twitDtos(twits,user);
        return new ResponseEntity<>(twitDtos, HttpStatus.OK);

    }




}
