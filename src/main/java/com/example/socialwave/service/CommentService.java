package com.example.socialwave.service;

import com.example.socialwave.model.dto.CommentDTO;
import com.example.socialwave.model.request.CreateCommentRequest;
import com.example.socialwave.model.request.UpdateCommentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    List<CommentDTO> getAllComment(int postId);
    CommentDTO createComment(CreateCommentRequest request,int postId, int commentatorId);

    CommentDTO updateComment(UpdateCommentRequest request, int id ,int userId);//id comment

    void deleteComment(int id,int userId);//id comment

}
