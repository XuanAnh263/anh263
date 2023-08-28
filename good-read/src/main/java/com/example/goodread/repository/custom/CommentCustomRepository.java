package com.example.goodread.repository.custom;

import com.example.goodread.entity.Comment;
import com.example.goodread.repository.BaseRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CommentCustomRepository extends BaseRepository {


    public Optional<Comment> findCommentByUserAndReview(Long reviewId, Long userId) {
        String sql = "SELECT * FROM `comments` cm WHERE cm.review_id = :reviewId AND cm.user_id = :userId";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("reviewId", reviewId);
        parameters.put("userId", userId);
        List<Comment> comments = getNamedParameterJdbcTemplate().query(sql, parameters, BeanPropertyRowMapper.newInstance(Comment.class));
        if (comments.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(comments.get(0));
        }
    }


}
