package com.example.clothStore.services.interfaces;

import com.example.clothStore.controllers.dtos.requests.CreateSendRequest;
import com.example.clothStore.controllers.dtos.requests.UpdateSendRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.entities.Send;

public interface ISendService {

    Send findSendById(Long id);

    BaseResponse getSendById(Long id);

    BaseResponse getSendByUserIdAndStatus(Long id, String statusName);

    BaseResponse getSendByUserId(Long id);

    BaseResponse create(CreateSendRequest request);

    BaseResponse update(Long id, UpdateSendRequest request);

    void delete(Long id);
}
