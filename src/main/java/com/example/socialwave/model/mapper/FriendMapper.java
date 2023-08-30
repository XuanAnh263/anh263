package com.example.socialwave.model.mapper;

import com.example.socialwave.entity.Friend;
import com.example.socialwave.model.dto.FriendDTO;
import com.example.socialwave.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FriendMapper {

    private static UserService userService;



    public static FriendDTO toFriendDTO(Friend friend) {
        FriendDTO tmp = new FriendDTO();
        tmp.setId(friend.getId());
        tmp.setUserA(friend.getUserA().getId());
        tmp.setUserB(friend.getUserB().getId());
        tmp.setCreated(friend.getCreated());
        tmp.setState(friend.getState());
        return tmp;
    }





    public static Friend toFriend() {
        Friend friend = new Friend();
        LocalDateTime now = LocalDateTime.now();
        friend.setState("waiting");
        friend.setCreated(now);
        return friend;
    }

    public static Friend toFriend( int userBId) {
        Friend friend = new Friend();
        return friend;
    }
}
