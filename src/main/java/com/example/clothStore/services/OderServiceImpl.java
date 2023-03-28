package com.example.clothStore.services;

import com.example.clothStore.controllers.dtos.requests.CreateOrderRequest;
import com.example.clothStore.controllers.dtos.requests.UpdateOrderRequest;
import com.example.clothStore.controllers.dtos.responses.BaseResponse;
import com.example.clothStore.controllers.dtos.responses.OrderResponse;
import com.example.clothStore.controllers.excepcion.ClothExcepcion;
import com.example.clothStore.entities.*;
import com.example.clothStore.entities.Projections.OrderProjection;
import com.example.clothStore.repositories.IOrderRepository;
import com.example.clothStore.services.interfaces.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OderServiceImpl implements IOrderService {
    @Autowired
    private IOrderRepository repository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IStatusService statusService;

    @Autowired
    private IClothService clothService;

    @Override
    public Order findOrderById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ClothExcepcion("Order not found "));
    }

    @Override
    public BaseResponse listOrdersByUserId(Long id) {
        List<OrderResponse> response = repository.listAllOrdersByUserId(id).stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Orders by user id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse listOrdersByUSerIdAndStatus(Long id, String statusName) {
        Status status = statusService.findStatusByName(statusName);
        List<OrderResponse> response = repository.listAllOrdersByUserIdAndStatus(id,status.getId()).stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Orders by user and status")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getOrderById(Long id) {
        OrderResponse response = from(repository.getOrderById(id));
        return BaseResponse.builder()
                .data(response)
                .message("Order by id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateOrderRequest request) {
        OrderResponse response = new OrderResponse();

            Order order = new Order();
            order = create(request,order);
            response = from(repository.save(order));
        return BaseResponse.builder()
                .data(response)
                .message("Order by id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateOrderRequest request) {
        Order order = findOrderById(id);
        order = update(request, order);
        OrderResponse response = from(repository.save(order));
        return BaseResponse.builder()
                .data(response)
                .message("Order status update")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public void delete(Long id) {
        findOrderById(id);
        repository.deleteById(id);

    }

    @Override
    public Order updateStatus(Long id, String statusName) {
        Order order = setStatus(id,statusName);
        return repository.save(order);
    }

    private Order setStatus(Long id, String statusName){
        Order order = findOrderById(id);
        Status status1 = statusService.findStatusByName(statusName);
        order.setStatus(status1);
        return order;
    }

    private OrderResponse addOneClothToExistingOrder(Long id, int quantity){
        Order order = findOrderById(id);
        order.setQuantity(order.getQuantity() + quantity);
        OrderResponse response = from(repository.save(order));
        return response;
    }

    private OrderResponse from(Order order){
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setUserId(order.getUser().getId());
        response.setStatusName(order.getStatus().getName());
        response.setClothId(order.getCloth().getId());
        response.setQuantity(order.getQuantity());
        response.setPrice(order.getPrice());
        response.setTotal(order.getTotal());
        return response;
    }

    private OrderResponse from(OrderProjection order){
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setUserId(order.getUserId());
        response.setStatusName(order.getStatusName());
        response.setClothId(order.getClothId());
        response.setPrice(order.getPrice());
        response.setTotal(order.getTotal());
        response.setQuantity(order.getQuantity());
        return response;
    }

    private Order create(CreateOrderRequest request, Order order){
        User user = userService.findUserById(request.getUserId());
        order.setUser(user);

        Cloth cloth = clothService.findClothById(request.getClothId());
        order.setCloth(cloth);

        Status status = statusService.findStatusByName(request.getStatusName());
        order.setStatus(status);

        order.setPrice(request.getPrice());
        order.setQuantity(request.getQuantity());

        BigDecimal quantityDecimal = new BigDecimal(request.getQuantity());
        order.setTotal(order.getPrice().multiply(quantityDecimal));
        return order;
    }

    private Order update(UpdateOrderRequest request, Order order){
        Status status = statusService.findStatusByName(request.getStatus());
        order.setStatus(status);

        return  order;
    }
}
