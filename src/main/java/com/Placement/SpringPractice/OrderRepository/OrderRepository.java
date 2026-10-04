package com.Placement.SpringPractice.OrderRepository;

import com.Placement.SpringPractice.OrderEntity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUserId(Long id);
}
