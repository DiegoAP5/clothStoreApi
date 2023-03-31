package com.example.clothStore.services;

import com.example.clothStore.controllers.dtos.requests.CreateRefundRequest;
import com.example.clothStore.controllers.dtos.requests.UpdateRefundRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.controllers.dtos.responses.RefundResponse;
import com.example.clothStore.controllers.excepcion.ClothExcepcion;
import com.example.clothStore.entities.*;
import com.example.clothStore.entities.Projections.RefundProjection;
import com.example.clothStore.repositories.IRefundRepository;
import com.example.clothStore.services.interfaces.*;
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

    @Autowired
    private ISNSService snsService;

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
    public BaseResponse listRefund() {
        List<RefundResponse> response = repository.listRefund().stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("All refund")
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
        String message = "Hemos recibido su reembolso y se encuentra en " + refund.getStatus().getName();
        String subject = "Reembolso creado para el usuario " + refund.getUser().getName();
        snsService.sendNotification(message, subject,refund.getUser().getEmail());
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

    @Override
    public Refund updateStatus(Long id, String statusName) {
        Refund refund = setStatus(id,statusName);
        String message = "Hemos procesado su reembolso y sen encuentra en " + refund.getStatus().getName() + " el reembolso a la tarjeta " + refund.getUser().getCardNumber() + " se encuentra en " + refund.getStatus().getName();
        String subject = "Estado de su reembolso " + refund.getUser().getName();
        snsService.sendNotification(message, subject,refund.getUser().getEmail());
        return repository.save(refund);
    }

    private Refund setStatus(Long id, String statusName){
        Refund refund = findRefundById(id);
        Status status1 = statusService.findStatusByName(statusName);
        refund.setStatus(status1);
        return refund;
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
