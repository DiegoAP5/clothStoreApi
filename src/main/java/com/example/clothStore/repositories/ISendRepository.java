package com.example.clothStore.repositories;

import com.example.clothStore.entities.Projections.SendProjection;
import com.example.clothStore.entities.Send;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISendRepository extends JpaRepository<Send,Long> {

    @Query(value = "select sends.*, users.id as userId, orders.id as orderId, status.id as statusId from sends"+
            "inner join users on sends.user_id = users.id"+
            "inner join orders on sends.order_id = orders.id"+
            "inner join status on sends.status_id = status.id"+
            "where sends.id =:id",nativeQuery = true)
    SendProjection getSendById(Long id);

    @Query(value = "select sends.*, users.id as userId, orders.id as orderId, status.id as statusId from sends"+
            "inner join users on sends.user_id = users.id"+
            "inner join orders on sends.order_id = orders.id"+
            "inner join status on sends.status_id = status.id"+
            "where sends.user_id =:userId and sends.status_id =:statusId",nativeQuery = true)
    List<SendProjection> getSendByUserIdAndStatus(Long userId, Long statusId);

    @Query(value = "select sends.*, users.id as userId, orders.id as orderId, status.id as statusId from sends"+
            "inner join users on sends.user_id = users.id"+
            "inner join orders on sends.order_id = orders.id"+
            "inner join status on sends.status_id = status.id"+
            "where sends.user_id =:userId",nativeQuery = true)
    SendProjection getSendByUserId(Long userId);
}
