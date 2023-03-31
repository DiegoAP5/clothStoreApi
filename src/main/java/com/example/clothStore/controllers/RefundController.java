package com.example.clothStore.controllers;

import com.example.clothStore.controllers.dtos.requests.CreateRefundRequest;
import com.example.clothStore.controllers.dtos.requests.UpdateRefundRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.services.interfaces.IRefundService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("refund")
public class RefundController {
    @Autowired
    private IRefundService service;

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllRefund(){
        BaseResponse baseResponse = service.listRefund();
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<BaseResponse> getRefundByUserId(@PathVariable Long id){
        BaseResponse baseResponse = service.getRefundByUserId(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("user/{id}/status/{statusName}")
    public  ResponseEntity<BaseResponse> getRefunByStatus(@PathVariable Long id, @PathVariable String statusName){
        BaseResponse baseResponse = service.getRefundByUserIdAndStatus(id,statusName);
        return  new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getRefundById(@PathVariable Long id){
        BaseResponse baseResponse = service.getRefundById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateRefundRequest request) {
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @PathVariable UpdateRefundRequest request){
        BaseResponse baseResponse = service.update(id,request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
