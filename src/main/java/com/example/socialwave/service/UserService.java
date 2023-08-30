package com.example.socialwave.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


import com.example.socialwave.entity.User;
import com.example.socialwave.model.dto.UserDTO;
import com.example.socialwave.model.request.ChangePasswordRequest;
import com.example.socialwave.model.request.CreateUserRequest;
import com.example.socialwave.model.request.SignUpRequest;
import com.example.socialwave.model.request.UpdateUserRequest;
import org.springframework.stereotype.Service;




@Service
public interface UserService {
    List<User> findAll();
    List<UserDTO> getAllUser();

    UserDTO findByPhoneNumber1(String phoneNumber);

    User findUserByPhoneNumber(String phoneNumber);

    UserDTO getUserById(int id,int userId);

    int getCurrentUserId(Principal principal);

    UserDTO updateUser(UpdateUserRequest request, int id);

    UserDTO disableUser(UpdateUserRequest request, int id);

    List<UserDTO> searchByNameOrId(String keyword);

    List<UserDTO> getUsers(String type, String keyword);

    UserDTO createUser(CreateUserRequest request);

    UserDTO changePassword(ChangePasswordRequest request, String phoneNumber);

    Optional<User> findByPhoneNumber(String phoneNumber);

    UserDTO signUp(SignUpRequest request);
}
