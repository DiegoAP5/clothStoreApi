package com.example.clothStore.services;

import com.example.clothStore.controllers.dtos.requests.CreateClothRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.controllers.dtos.responses.ClothResponse;
import com.example.clothStore.controllers.excepcion.ClothExcepcion;
import com.example.clothStore.entities.Cloth;
import com.example.clothStore.entities.Order;
import com.example.clothStore.entities.Projections.ClothProjection;
import com.example.clothStore.repositories.IClothRepository;
import com.example.clothStore.services.interfaces.IClothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClothServiceImpl implements IClothService {
    @Autowired
    private IClothRepository repository;
    @Override
    public Cloth findClothById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ClothExcepcion("Cloth not found "));
    }

    @Override
    public Cloth findClothByName(String name) {
        return repository.getClothByName(name);
    }

    @Override
    public BaseResponse listClothes() {
        List<ClothResponse> response =repository.listClothes().stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("All clothes")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getClothById(Long id) {
        ClothResponse response = from(findClothById(id));
        return BaseResponse.builder()
                .data(response)
                .message("Cloth searched by id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getClothByName(String name) {
        ClothResponse response = from(findClothByName(name));
        return BaseResponse.builder()
                .data(response)
                .message("Cloth searched by name")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateClothRequest request) {
        Cloth cloth = new Cloth();
        cloth = create(request,cloth);
        ClothResponse response = from(repository.save(cloth));
        return BaseResponse.builder()
                .data(response)
                .message("Cloth created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public void delete(Long id) {
        findClothById(id);
        repository.deleteById(id);

    }

    private ClothResponse from(Cloth cloth){
        ClothResponse response = new ClothResponse();
        response.setId(cloth.getId());
        response.setName(cloth.getName());
        response.setUrl(cloth.getUrl());
        response.setPrice(cloth.getPrice());
        return response;
    }

    public ClothResponse from(ClothProjection cloth){
        ClothResponse response = new ClothResponse();
        response.setId(cloth.getId());
        response.setName(cloth.getName());
        response.setUrl(cloth.getUrl());
        response.setPrice(cloth.getPrice());
        return response;
    }

    private Cloth create(CreateClothRequest request, Cloth cloth){
        cloth.setName(request.getName());
        cloth.setUrl(request.getUrl());
        cloth.setPrice(request.getPrice());
        return cloth;
    }

}
