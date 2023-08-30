package com.example.socialwave.model.mapper;

import com.example.socialwave.entity.Comment;
import com.example.socialwave.model.dto.CommentDTO;
import com.example.socialwave.model.request.CreateCommentRequest;
import com.example.socialwave.model.request.UpdateCommentRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {
    public  CommentDTO toCommentDTO(Comment comment) {
        CommentDTO tmp = new CommentDTO();
        tmp.setId(comment.getId());
        tmp.setUser(comment.getUser().getId());
        tmp.setContent(comment.getContent());
        tmp.setCreated(comment.getCreated());
        tmp.setUpdated(comment.getUpdated());
        tmp.setPost(comment.getPost().getId());
        return tmp;
    }


    public static Comment toComment(CreateCommentRequest request,int id) {
        Comment comment = new Comment();
        LocalDateTime now = LocalDateTime.now();
        comment.setCreated(now);
        comment.setContent(request.getContent());
        comment.setUpdated(now);
        return comment;
    }

    public static Comment toComment(UpdateCommentRequest request, int id) {
        Comment comment = new Comment();
        LocalDateTime now = LocalDateTime.now();
        comment.setId(id);
        comment.setContent(request.getContent());
        comment.setUpdated(now);
        return comment;
    }

}
