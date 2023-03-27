package com.example.clothStore.services.interfaces;

import com.example.clothStore.controllers.dtos.requests.CreateClothRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.entities.Cloth;
import com.example.clothStore.entities.Projections.ClothProjection;

import java.util.List;

public interface IClothService {

    Cloth findClothById(Long id);

    List<ClothProjection> findClothByName(String name);

    BaseResponse listClothes();

    BaseResponse getClothById(Long id);

    BaseResponse getClothByName(String name);

    BaseResponse create(CreateClothRequest request);

    void delete(Long id);
}
