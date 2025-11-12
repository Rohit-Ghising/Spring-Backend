package com.twitter.demo.mapper;

import com.twitter.demo.dto.TwitDto;
import com.twitter.demo.dto.UserDto;
import com.twitter.demo.model.Twit;
import com.twitter.demo.model.User;
import com.twitter.demo.utils.TwitUtil;

import java.util.ArrayList;
import java.util.List;

public class TwitDtoMapper {
    public static TwitDto toTwitDto(Twit twit, User reqUser){
        UserDto user = UserDtoMapper.toUserDto(twit.getUser());
        boolean isLiked = TwitUtil.isLikedByReqUser(reqUser,twit);
        boolean isRetwited =TwitUtil.idReTwitedByReqUser(reqUser,twit);
        List<Long>retwitUserId = new ArrayList<>();
        for(User user1:twit.getRetwitUser()){
            retwitUserId.add(user1.getId());
        }
        TwitDto twitDto =new TwitDto();
        twitDto.setId(twit.getId());
        twitDto.setContent(twit.getContent());
        twitDto.setCreatedAt(twit.getCreatedAt());
        twitDto.setImage(twit.getImage());
        twitDto.setTotalLikes(twit.getLikes().size());
        twitDto.setTotalReplies(twit.getReplyTwits().size());
        twitDto.setTotalRetweets(twit.getRetwitUser().size());
        twitDto.setUser(user);
        twitDto.setLiked(isLiked);
        twitDto.setRetweet(isRetwited);
        twitDto.setRetwitUsersId(retwitUserId);
        twitDto.setReplyTwits(twitDtos(twit.getReplyTwits(),reqUser));
        twitDto.setVideo(twitDto.getVideo());

        return  twitDto;
    }
    public static List<TwitDto> twitDtos(List<Twit >twits,User reqUser){
        List<TwitDto> twitDtos=new ArrayList<>();
        for (Twit twit:twits){
            TwitDto twitDto=toReplyTwitDto(twit,reqUser);
            twitDtos.add(twitDto);
        }
        return twitDtos;
    }

    private static TwitDto toReplyTwitDto(Twit twit, User reqUser) {
        UserDto user = UserDtoMapper.toUserDto(twit.getUser());
        boolean isLiked = TwitUtil.isLikedByReqUser(reqUser,twit);
        boolean isRetwited =TwitUtil.idReTwitedByReqUser(reqUser,twit);
        List<Long>retwitUserId = new ArrayList<>();
        for(User user1:twit.getRetwitUser()){
            retwitUserId.add(user1.getId());
        }
        TwitDto twitDto =new TwitDto();
        twitDto.setId(twit.getId());
        twitDto.setContent(twit.getContent());
        twitDto.setCreatedAt(twit.getCreatedAt());
        twitDto.setImage(twit.getImage());
        twitDto.setTotalLikes(twit.getLikes().size());
        twitDto.setTotalReplies(twit.getReplyTwits().size());
        twitDto.setTotalRetweets(twit.getRetwitUser().size());
        twitDto.setUser(user);
        twitDto.setLiked(isLiked);
        twitDto.setRetweet(isRetwited);
        twitDto.setRetwitUsersId(retwitUserId);

        twitDto.setVideo(twitDto.getVideo());

        return  twitDto;


    }
}
