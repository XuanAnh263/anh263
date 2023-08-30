package com.example.socialwave.model.mapper;

import com.example.socialwave.entity.Like;
import com.example.socialwave.model.dto.LikeDTO;
import com.example.socialwave.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LikeMapper {
    private static UserService userService;

    public LikeDTO toLikeDTO(Like like) {
        LikeDTO tmp = new LikeDTO();
        tmp.setId(like.getId());
        tmp.setPeopleLikeId(like.getPeopleLikeId().getId());

        tmp.setUpdated(like.getUpdated());
        tmp.setLikePost(like.getLikePost().getId());
        return tmp;
    }


    public static Like toLike( int id) {
        Like like = new Like();
        LocalDateTime now = LocalDateTime.now();
        like.setUpdated(now);
        return like;
    }


}
