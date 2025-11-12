package com.twitter.demo.service;

import com.twitter.demo.exception.TwitException;
import com.twitter.demo.exception.UserException;
import com.twitter.demo.model.Twit;
import com.twitter.demo.model.User;
import com.twitter.demo.request.TwitReplyRequest;

import java.util.List;

public interface TwitService {
    public Twit createTwit(Twit req, User user) throws UserException;
    public List<Twit> findAllTwit();
    public  Twit retwit(Long twitId,User user)throws UserException, TwitException;
    public Twit findById(Long twitId)throws TwitException;
    public void deleteTwitById(Long twitId,Long userId) throws TwitException,UserException;
    public  Twit removeFromRetwit(Long twitId,User user)throws TwitException,UserException;
    public Twit createReply(TwitReplyRequest req, User user) throws UserException;
    public List<Twit> getUserTwit(User user) ;
    public  List<Twit> findByLikesContainUser(User user);
}
