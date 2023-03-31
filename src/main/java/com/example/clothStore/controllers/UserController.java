package com.example.clothStore.controllers;

import com.example.clothStore.controllers.dtos.requests.CreateUserRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.services.interfaces.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService service;

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getUserById(@PathVariable Long id){
        BaseResponse baseResponse = service.getUserById(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping
    public ResponseEntity<BaseResponse> listUser(){
        BaseResponse baseResponse = service.listUsers();
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("name/{userName}")
    public ResponseEntity<BaseResponse> getUserByName(@PathVariable String userName){
        BaseResponse baseResponse = service.getUserByName(userName);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("email/{userName}")
    public ResponseEntity<BaseResponse> getUserByEmail(@PathVariable String userName){
        BaseResponse baseResponse = service.getUserByEmail(userName);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateUserRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
