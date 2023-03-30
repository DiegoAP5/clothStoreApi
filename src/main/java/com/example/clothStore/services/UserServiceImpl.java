package com.example.clothStore.services;

import com.example.clothStore.controllers.dtos.requests.CreateUserRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.controllers.dtos.responses.UserResponse;
import com.example.clothStore.controllers.excepcion.ClothExcepcion;
import com.example.clothStore.entities.User;
import com.example.clothStore.repositories.IUserRepository;
import com.example.clothStore.services.interfaces.ISNSService;
import com.example.clothStore.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Autowired
    private ISNSService snsService;

    @Override
    public User findUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ClothExcepcion("User not found "));
    }

    @Override
    public User getNoUserByName(String name) {
        return repository.getUserByName(name);
    }

    @Override
    public BaseResponse listUsers() {
        List<UserResponse> responses = repository.findAll().stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(responses)
                .message("User list")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getUserById(Long id) {
        UserResponse response = from(findUserById(id));
        return BaseResponse.builder()
                .data(response)
                .message("User by Id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getUserByName(String name) {
        UserResponse response = from(getNoUserByName(name));
        return BaseResponse.builder()
                .data(response)
                .message("User by name")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateUserRequest request) {
        User user = new User();
        user = create(request,user);
        UserResponse response = from(repository.save(user));
        snsService.subscribeEmail("arn:aws:sns:us-east-1:270034414547:Message-Topic", user.getEmail());
        return BaseResponse.builder()
                .data(response)
                .message("user created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public void delete(Long id) {
        findUserById(id);
        repository.deleteById(id);
    }

    private UserResponse from(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setCardNumber(user.getCardNumber());
        response.setRole(user.getRole());
        return response;
    }

    private User create(CreateUserRequest request, User user) {
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setCardNumber(request.getCardNumber());
        user.setRole("user");
        return user;
    }

}
