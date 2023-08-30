package com.example.socialwave.service.impl;

import com.example.socialwave.entity.Like;
import com.example.socialwave.entity.Post;
import com.example.socialwave.entity.User;
import com.example.socialwave.exception.DuplicateRecordException;
import com.example.socialwave.exception.InternalServerException;
import com.example.socialwave.exception.NotFoundException;
import com.example.socialwave.model.dto.LikeDTO;
import com.example.socialwave.model.mapper.LikeMapper;
import com.example.socialwave.repository.LikeRepository;
import com.example.socialwave.repository.PostRepository;
import com.example.socialwave.service.LikeService;
import com.example.socialwave.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;
    private final PostRepository postRepository;

    public LikeServiceImpl(LikeRepository likeRepository, LikeMapper likeMapper, UserService userService, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.likeMapper = likeMapper;
        this.postRepository = postRepository;

    }

    @Override
    public List<LikeDTO> getAllLike(int postId) {
        List<Like> likes = likeRepository.getAllLike(postId);
        return likes.stream().map(likeMapper::toLikeDTO).collect(Collectors.toList());
    }

    @Override
    public LikeDTO createLike( int postId, int userId) {
        Like like = new Like();
        Optional<Like> like1 = likeRepository.findByPostIdAndUserId(postId,userId);
        if(like1.isPresent()){
            throw new DuplicateRecordException("duplicate like");
        }

        Optional<Post> post1 = postRepository.findById(postId);
        int countLike = post1.get().getNumberOfLikes();

        countLike =countLike+1;

        User user =new User();
        user.setId(userId);
        Post post = new Post();
        post.setId(postId);

        like = LikeMapper.toLike(postId);
        like.setLikePost(post);
        like.setPeopleLikeId(user);
        likeRepository.save(like);
        postRepository.updateLike(countLike,postId);
        return likeMapper.toLikeDTO(like);
    }

    @Override
    public void deleteLike(int id) {
        Optional<Like> like = likeRepository.findByPostId(id);
        if (like.isEmpty()) {
            throw new NotFoundException("No like found");
        }

        int postId = like.get().getLikePost().getId();
        Optional<Post> post1 = postRepository.findById(postId);
        int countLike = post1.get().getNumberOfLikes();
        countLike =countLike-1;

        try {
            likeRepository.deleteByPostId(id);
            postRepository.updateLike(countLike,postId);
        } catch (Exception ex) {
            throw new InternalServerException("Can't delete Like");
        }
    }
}
