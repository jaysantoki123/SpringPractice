package com.Placement.SpringPractice.OrderService;


import com.Placement.SpringPractice.DTOMapper.DTOMapper;
import com.Placement.SpringPractice.DTOs.OrderRequest;
import com.Placement.SpringPractice.DTOs.OrderResponse;
import com.Placement.SpringPractice.Exception.ResourceNotFoundException;
import com.Placement.SpringPractice.OrderEntity.OrderEntity;
import com.Placement.SpringPractice.OrderRepository.OrderRepository;
import com.Placement.SpringPractice.UserEntity.UserEntity;
import com.Placement.SpringPractice.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class OrderService {

    @Autowired
    DTOMapper map;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    public OrderResponse saveOrder(OrderRequest request){

            UserEntity user = userRepository.findById(request.userId())
                    .orElseThrow(() -> new ResourceNotFoundException("User with id :- " + request.userId() + " not found" ));

            OrderEntity order = map.toOrderEntity(request);
            order.setUser(user);

            order.setTotalAmount(request.price() * request.quantity());

            OrderEntity savedOrder = orderRepository.save(order);

            return map.toOrderResponseDTO(savedOrder);
    }

    public List<OrderResponse> getAllOrder(Long id){
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id :- " + id + "not found "));
        List<OrderEntity> order = orderRepository.findByUserId(id);

        return order.stream()
                .map(map::toOrderResponseDTO)
                .toList();

    }

}
