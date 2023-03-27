package com.example.clothStore.controllers;

import com.example.clothStore.controllers.dtos.requests.CreateOrderRequest;
import com.example.clothStore.controllers.dtos.requests.UpdateOrderRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.services.interfaces.IOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private IOrderService service;

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getOrderById(@PathVariable Long id){
        BaseResponse baseResponse = service.getOrderById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<BaseResponse> getOrderByUserId(@PathVariable Long id){
        BaseResponse baseResponse = service.listOrdersByUserId(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("user/{id}/status/{statusName}")
    public  ResponseEntity<BaseResponse> getOrderByUserIdAndStatus(@PathVariable Long id, @PathVariable String statusName){
        BaseResponse baseResponse = service.listOrdersByUSerIdAndStatus(id,statusName);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateOrderRequest request){
        BaseResponse baseResponse = service.create(request);
        return  new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateOrderRequest request){
        BaseResponse baseResponse = service.update(id, request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
