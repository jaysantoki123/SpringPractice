package com.Placement.SpringPractice.DTOs;

import java.time.LocalDateTime;

public record OrderResponse(
        Long orderID,
        Long userID,
        String productName,
        Double price,
        Integer quantity,
        Double total,
        String status,
        LocalDateTime orderCreatedTime
) {
}
