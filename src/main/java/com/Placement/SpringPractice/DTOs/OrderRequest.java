package com.Placement.SpringPractice.DTOs;

public record OrderRequest(
        Long userId,
        String productName,
        Integer quantity,
        Double price
) {
}
