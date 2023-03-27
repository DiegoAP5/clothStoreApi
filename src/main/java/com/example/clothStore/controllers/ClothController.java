package com.example.clothStore.controllers;

import com.example.clothStore.controllers.dtos.requests.CreateClothRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.services.interfaces.IClothService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cloth")
public class ClothController {

    @Autowired
    private IClothService service;

    @GetMapping
    public ResponseEntity<BaseResponse> listClothes(){
        BaseResponse baseResponse = service.listClothes();
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("name/{name}")
    public ResponseEntity<BaseResponse> getClothByName(@PathVariable String name){
        BaseResponse baseResponse = service.getClothByName(name);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getClothById(@PathVariable Long id){
        BaseResponse baseResponse = service.getClothById(id);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateClothRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){service.delete(id);}
}
