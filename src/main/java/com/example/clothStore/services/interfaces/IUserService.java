package com.example.clothStore.services.interfaces;

import com.example.clothStore.controllers.dtos.requests.CreateUserRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.entities.User;

public interface IUserService {

    User findUserById(Long id);

    User getNoUserByName(String name);

    BaseResponse listUsers();

    BaseResponse getUserById(Long id);

    BaseResponse getUserByName(String name);

    BaseResponse getUserByEmail(String email);

    BaseResponse create(CreateUserRequest request);

    void delete(Long id);
}
