package com.example.clothStore.repositories;

import com.example.clothStore.entities.Projections.RefundProjection;
import com.example.clothStore.entities.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRefundRepository extends JpaRepository<Refund,Long> {
    @Query(value = "select refunds.*, users.id as userId, orders.id as orderId, status.name as statusName from refunds "+
            "inner join users on refunds.user_id = users.id "+
            "inner join orders on refunds.order_id = orders.id "+
            "inner join status on refunds.status_id = status.id "+
            "where refunds.user_id =:userId",nativeQuery = true)
    List<RefundProjection> getRefundByUserId(Long userId);

    @Query(value = "select refunds.*, users.id as userId, orders.id as orderId, status.name as statusName from refunds "+
            "inner join users on refunds.user_id = users.id "+
            "inner join orders on refunds.order_id = orders.id "+
            "inner join status on refunds.status_id = status.id ",nativeQuery = true)
    List<RefundProjection> listRefund();

    @Query(value = "select refunds.*, users.id as userId, orders.id as orderId, status.name as statusName from refunds "+
            "inner join users on refunds.user_id = users.id "+
            "inner join orders on refunds.order_id = orders.id "+
            "inner join status on refunds.status_id = status.id "+
            "where refunds.user_id =:userId and refunds.status_id =:statusId",nativeQuery = true)
    List<RefundProjection> getRefundByUserIdAndStatus(Long userId, Long statusId);

    @Query(value = "select refunds.*, users.id as userId, orders.id as orderId, status.name as statusName from refunds "+
            "inner join users on refunds.user_id = users.id "+
            "inner join orders on refunds.order_id = orders.id "+
            "inner join status on refunds.status_id = status.id "+
            "where refunds.id =:id",nativeQuery = true)
    RefundProjection getRefundById(Long id);
}
