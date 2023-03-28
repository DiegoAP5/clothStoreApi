package com.example.clothStore.services;

import com.example.clothStore.controllers.dtos.requests.CreateRefundRequest;
import com.example.clothStore.controllers.dtos.requests.UpdateRefundRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.controllers.dtos.responses.RefundResponse;
import com.example.clothStore.controllers.excepcion.ClothExcepcion;
import com.example.clothStore.entities.Order;
import com.example.clothStore.entities.Projections.RefundProjection;
import com.example.clothStore.entities.Refund;
import com.example.clothStore.entities.Status;
import com.example.clothStore.entities.User;
import com.example.clothStore.repositories.IRefundRepository;
import com.example.clothStore.services.interfaces.IOrderService;
import com.example.clothStore.services.interfaces.IRefundService;
import com.example.clothStore.services.interfaces.IStatusService;
import com.example.clothStore.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RefundServiceImpl implements IRefundService {

    @Autowired
    private IRefundRepository repository;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IStatusService statusService;

    @Autowired
    private IUserService userService;

    @Override
    public Refund findRefundById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ClothExcepcion("Refund not found "));
    }

    @Override
    public BaseResponse getRefundById(Long id) {
        RefundResponse response = from(repository.getRefundById(id));
        return BaseResponse.builder()
                .data(response)
                .message("Refund by id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getRefundByUserId(Long id) {
        List<RefundResponse> response = repository.getRefundByUserId(id).stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("All refund by user id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getRefundByUserIdAndStatus(Long id, String statusName) {
        Status status = statusService.findStatusByName(statusName);
        List<RefundResponse> response = repository.getRefundByUserIdAndStatus(id,status.getId()).stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Refund by status")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateRefundRequest request) {
        Refund refund = new Refund();
        refund = create(request,refund);
        RefundResponse response =from(repository.save(refund));
        return BaseResponse.builder()
                .data(response)
                .message("Refund created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateRefundRequest request) {
        Refund refund = findRefundById(id);
        refund = update(request,refund);
        RefundResponse response = from(repository.save(refund));
        return BaseResponse.builder()
                .data(response)
                .message("Refund updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public void delete(Long id) {
    }

    private RefundResponse from(RefundProjection refund){
        RefundResponse response = new RefundResponse();
        response.setId(refund.getId());
        response.setStatus(refund.getStatusName());
        response.setDeclaration(refund.getDeclaration());
        response.setUserId(refund.getUserId());
        response.setOrderId(refund.getOrderId());
        return response;
    }

    private RefundResponse from(Refund refund){
        RefundResponse response = new RefundResponse();
        response.setId(refund.getId());
        response.setStatus(refund.getStatus().getName());
        response.setDeclaration(refund.getDeclaration());
        response.setUserId(refund.getUser().getId());
        response.setOrderId(refund.getOrder().getId());
        return response;
    }

    private Refund create(CreateRefundRequest request, Refund refund){
        User user = userService.findUserById(request.getUserId());
        refund.setUser(user);

        Order order = orderService.findOrderById(request.getOrderId());
        refund.setOrder(order);

        Status status = statusService.findStatusByName(request.getStatus());
        refund.setStatus(status);

        refund.setDeclaration(request.getDeclaration());
        return refund;
    }

    private Refund update(UpdateRefundRequest request, Refund refund){
        Status status = statusService.findStatusByName(request.getStatus());
        refund.setStatus(status);
        return refund;
    }
}