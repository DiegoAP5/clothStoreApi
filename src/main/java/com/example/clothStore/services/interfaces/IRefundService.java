package com.example.clothStore.services.interfaces;

import com.example.clothStore.controllers.dtos.requests.CreateRefundRequest;
import com.example.clothStore.controllers.dtos.requests.UpdateRefundRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.entities.Refund;

public interface IRefundService {

    Refund findRefundById(Long id);

    BaseResponse getRefundById(Long id);

    BaseResponse getRefundByUserId(Long id);

    BaseResponse getRefundByUserIdAndStatus(Long id, String statusName);

    BaseResponse create(CreateRefundRequest request);

    BaseResponse update(Long id,UpdateRefundRequest request);

    void delete(Long id);

    Refund updateStatus(Long id, String statusName);
}
