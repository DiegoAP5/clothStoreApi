package com.example.clothStore.services;

import com.example.clothStore.controllers.dtos.requests.CreateSendRequest;
import com.example.clothStore.controllers.dtos.requests.UpdateSendRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.controllers.dtos.responses.SendResponse;
import com.example.clothStore.controllers.excepcion.ClothExcepcion;
import com.example.clothStore.entities.Order;
import com.example.clothStore.entities.Projections.SendProjection;
import com.example.clothStore.entities.Send;
import com.example.clothStore.entities.Status;
import com.example.clothStore.entities.User;
import com.example.clothStore.repositories.ISendRepository;
import com.example.clothStore.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class SendServiceImpl implements ISendService {

    @Autowired
    private ISendRepository repository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IStatusService statusService;

    @Autowired
    private ISNSService snsService;

    @Override
    public Send findSendById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ClothExcepcion("Send not found "));
    }

    @Override
    public BaseResponse getSendById(Long id) {
        SendResponse response = from(repository.getSendById(id));
        return BaseResponse.builder()
                .data(response)
                .message("Order by id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getSendByUserIdAndStatus(Long id, String statusName) {
        Status status = statusService.findStatusByName(statusName);
        List<SendResponse> response = repository.getSendByUserIdAndStatus(id, status.getId())
                .stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Send by user by status")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getSendByUserId(Long id) {
        return null;
    }

    @Override
    public BaseResponse create(CreateSendRequest request) {
        SendResponse response = new SendResponse();
        Send send = new Send();
        send = create(request,send);
        response = from(repository.save(send));

        return BaseResponse.builder()
                .data(response)
                .message("Send created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateSendRequest request) {
        Send send = findSendById(id);
        send = update(request,send);
        SendResponse response = from(repository.save(send));
        Status status = statusService.findStatusByName(request.getStatus());
        snsService.sendNotification(send,"arn:aws:sns:us-east-1:626350110357:PostmenNotifications");
        return BaseResponse.builder()
                .data(response)
                .message("Send updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public void delete(Long id) {
        findSendById(id);
        repository.deleteById(id);

    }

    private SendResponse from(SendProjection send){
        SendResponse response = new SendResponse();
        response.setId(send.getId());
        response.setStatus(send.getStatusName());
        response.setUserId(send.getUserId());
        response.setGuide(send.getGuide());
        response.setAddress(send.getAddress());
        response.setOrderId(send.getOrderId());
        return response;
    }

    private SendResponse from(Send send){
        SendResponse response = new SendResponse();
        response.setGuide(send.getGuide());
        response.setId(send.getId());
        response.setStatus(send.getStatus().getName());
        response.setUserId(send.getUser().getId());
        response.setOrderId(send.getOrder().getId());
        response.setAddress(send.getAddress());
        return response;
    }

    private Send create(CreateSendRequest request, Send send){
        User user = userService.findUserById(request.getUserId());
        send.setUser(user);

        Order order = orderService.findOrderById(request.getOrderId());
        send.setOrder(order);

        Status status = statusService.findStatusByName(request.getStatus());
        send.setStatus(status);

        send.setGuide(generateTrackingId());
        send.setAddress(request.getAddress());
        return send;
    }

    private Send update(UpdateSendRequest request, Send send){
        Status status = statusService.findStatusByName(request.getStatus());
        send.setStatus(status);
        return send;
    }

    public static String generateTrackingId(){
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        String trackingId = "PM" + randomUUIDString.replaceAll("-", "").substring(0, 12);
        return trackingId.toUpperCase();
    }

}
