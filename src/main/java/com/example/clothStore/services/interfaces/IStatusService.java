package com.example.clothStore.services.interfaces;

import com.example.clothStore.controllers.dtos.requests.CreateStatusRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.entities.Status;

public interface IStatusService {

    Status findStatusById(Long id);

    Status findStatusByName(String name);

    BaseResponse listStatus();

    BaseResponse getStatusById(Long id);

    BaseResponse getStatusByName(String name);

    BaseResponse create(CreateStatusRequest request);

    void delete(Long id);
}
