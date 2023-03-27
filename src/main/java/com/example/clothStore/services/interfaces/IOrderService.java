package com.example.clothStore.services.interfaces;

import com.example.clothStore.controllers.dtos.requests.CreateOrderRequest;
import com.example.clothStore.controllers.dtos.requests.UpdateOrderRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.entities.Order;

public interface IOrderService {

    Order findOrderById(Long id);

    BaseResponse listOrdersByUserId(Long id);

    BaseResponse listOrdersByUSerIdAndStatus(Long id, String statusName);

    BaseResponse getOrderById(Long id);

    BaseResponse create(CreateOrderRequest request);

    BaseResponse update(Long id, UpdateOrderRequest request);

    void delete(Long id);
}
