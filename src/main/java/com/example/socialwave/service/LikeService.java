package com.example.socialwave.service;

import com.example.socialwave.model.dto.LikeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface LikeService {

    List<LikeDTO> getAllLike(int postId);
    LikeDTO createLike( int postId, int userId );
    void deleteLike(int id);//id post
}
