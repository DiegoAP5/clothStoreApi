package com.example.clothStore.repositories;

import com.example.clothStore.entities.Order;
import com.example.clothStore.entities.Projections.OrderProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {

    @Query(value = "select orders.*, users.id as userId, cloths.id as clothId, status.name as statusName from orders " +
            "inner join users on orders.user_id = users.id" +
            "inner join cloths on orders.cloth_id = cloths.id" +
            "inner join status on orders.status_id = status.id" +
            "where orders.user_id =:userId",nativeQuery = true)
    List<OrderProjection> listAllOrdersByUserId(Long userId);

    @Query(value = "select orders.*, users.id as userId, cloths.id as clothId, status.name as statusName from orders " +
            "inner join users on orders.user_id = users.id" +
            "inner join cloths on orders.cloth_id = cloths.id" +
            "inner join status on orders.status_id = status.id" +
            "where orders.user_id =:userId and orders.status_id =:statusId",nativeQuery = true)
    List<OrderProjection> listAllOrdersByUserIdAndStatus(Long userId, Long statusId);

    @Query(value = "select orders.*, users.id as userId, cloths.id as clothId, status.name as statusName from orders " +
            "inner join users on orders.user_id = users.id" +
            "inner join cloths on orders.cloth_id = cloths.id" +
            "inner join status on orders.status_id = status.id" +
            "where orders.id =:id",nativeQuery = true)
    OrderProjection getOrderById(Long id);
}
