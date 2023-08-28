package com.example.goodread.service;

import com.example.goodread.entity.*;
import com.example.goodread.exception.NotFoundException;
import com.example.goodread.model.request.*;
import com.example.goodread.model.response.*;
import com.example.goodread.repository.*;
import com.example.goodread.repository.custom.BookCustomRepository;
import com.example.goodread.repository.custom.CommentCustomRepository;
import com.example.goodread.repository.custom.ReviewCustomRepository;
import com.example.goodread.statics.FollowingStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FollowingService {
    BookRepository bookRepository;
    UserRepository userRepository;
    CategoryService categoryService;
    ReadingBookRepository readingBookRepository;
    BookOfChallengeRepository bookOfChallengeRepository;
    ObjectMapper objectMapper;
    BookCustomRepository bookCustomRepository;
    ReviewBookRepository reviewBookRepository;
    ReviewCustomRepository reviewCustomRepository;
    CommentCustomRepository commentCustomRepository;
    CommentRepository commentRepository;
    FollowingRepository followingRepository;

    public void followUser(Long userRequestId, Long userAcceptId) {
        User userRequest = userRepository.findById(userRequestId).get();
        User userAccept = userRepository.findById(userAcceptId).get();
        Following following = Following.builder()
                .userRequest(userRequest)
                .userAccept(userAccept)
                .followingStatus(FollowingStatus.FOLLOWING)
                .acceptedDateTime(LocalDateTime.now())
                .build();
        followingRepository.save(following);
    }

    public void unFollow(Long userRequestId, Long userAcceptId) {
        User userRequest = userRepository.findById(userRequestId).get();
        User userAccept = userRepository.findById(userAcceptId).get();
        Optional<Following> followingOptional = followingRepository.findByUserRequestAndUserAccept(userRequest, userAccept);
        if (followingOptional.isEmpty()) {
            throw new NotFoundException("Not following");
        }
        followingRepository.delete(followingOptional.get());
    }
}

