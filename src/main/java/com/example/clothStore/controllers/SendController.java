package com.example.clothStore.controllers;

import com.example.clothStore.controllers.dtos.requests.CreateSendRequest;
import com.example.clothStore.controllers.dtos.requests.UpdateSendRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.services.interfaces.ISendService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("send")
public class SendController {
    @Autowired
    private ISendService service;

    @GetMapping("user/{id}")
    public ResponseEntity<BaseResponse> getSendByUserId(@PathVariable Long id){
        BaseResponse baseResponse = service.getSendByUserId(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("user/{id}/status/{statusName}")
    public ResponseEntity<BaseResponse> getSendByStatus(@PathVariable Long id, @PathVariable String statusName){
        BaseResponse baseResponse = service.getSendByUserIdAndStatus(id,statusName);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getSendById(@PathVariable Long id){
        BaseResponse baseResponse = service.getSendById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateSendRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateSendRequest request){
        BaseResponse baseResponse = service.update(id,request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
