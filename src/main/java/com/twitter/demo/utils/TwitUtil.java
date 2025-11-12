package com.twitter.demo.utils;

import com.twitter.demo.model.Like;
import com.twitter.demo.model.Twit;
import com.twitter.demo.model.User;

public class TwitUtil {
    public  final static boolean isLikedByReqUser(User reqUser, Twit twit){
        for (Like like:twit.getLikes()){
            if(like.getUser().getId().equals(reqUser.getId()));
            return true;
        }
        return false;

    }
    public  final static boolean idReTwitedByReqUser(User reqUser,Twit twit){
        for (User user:twit.getRetwitUser()){
            if(user.getId().equals(reqUser.getId())){
                return  true;
            }

        }
        return false;
    }
}
