package com.Placement.SpringPractice.OrderController;

import com.Placement.SpringPractice.DTOs.OrderRequest;
import com.Placement.SpringPractice.DTOs.OrderResponse;
import com.Placement.SpringPractice.OrderEntity.OrderEntity;
import com.Placement.SpringPractice.OrderService.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<?> saveOrder(@RequestBody @Valid OrderRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.saveOrder(request));
    }

    @GetMapping("/get-orders/{id}")
    public ResponseEntity<List<OrderResponse>> getOrders(@PathVariable Long id){
        List<OrderResponse> orderList = orderService.getAllOrder(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderList);
    }
}
