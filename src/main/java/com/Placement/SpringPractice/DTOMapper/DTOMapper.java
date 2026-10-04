package com.Placement.SpringPractice.DTOMapper;//package com.Placement.SpringPractice.DTOMapper;
//
//import com.Placement.SpringPractice.DTOs.UserRequest;
//import com.Placement.SpringPractice.DTOs.UserResponse;
//import com.Placement.SpringPractice.UserEntity.ProductEntity;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DTOMapper {
//
//    public ProductEntity toEntity(UserRequest request){
//        ProductEntity entity =  new ProductEntity();
//        entity.setName(request.name());
//        entity.setBrand(request.brand());
//        entity.setCategory(request.category());
//        entity.setPrice(request.price());
//        entity.setQuantity(request.quantity());
//
//        return entity;
//    }
//
//    public UserResponse toResponseDTO(ProductEntity entity){
//        return new UserResponse(
//                entity.getId(),
//                entity.getName(),
//                entity.getPrice(),
//                entity.getQuantity(),
//                entity.getBrand(),
//                entity.getCategory(),
//                entity.getCreatedAt(),
//                entity.getUpdateAt()
//        );
//    }
//
//    public ProductEntity updateEntity(UserRequest request, ProductEntity product){
//            product.setName(request.name());
//            product.setPrice(request.price());
//            product.setBrand(request.brand());
//            product.setQuantity(request.quantity());
//            product.setPrice(request.price());
//
//            return product;
//    }
//}


import com.Placement.SpringPractice.AddressEntity.AddressEntity;
import com.Placement.SpringPractice.DTOs.*;
import com.Placement.SpringPractice.OrderEntity.OrderEntity;
import com.Placement.SpringPractice.UserEntity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DTOMapper {
    public UserEntity toEntity(UserRequest requestDTO) {
        UserEntity user = new UserEntity();
        user.setName(requestDTO.name());
        user.setEmail(requestDTO.email());
        user.setGender(requestDTO.gender());
        user.setAge(requestDTO.age());
        user.setPhone_no(requestDTO.phone_no());
        user.setPassword(requestDTO.password());
        user.setHobbies(requestDTO.hobbies());

        return user;
    }

    public UserResponse toDTO(UserEntity entity) {

        AddressResponse addressResponse = null;
        if (entity.getAddress() != null) {
            addressResponse = toAddressResponseDto(entity.getAddress());
        }

        return new UserResponse(
                entity.getName(),
                entity.getEmail(),
                entity.getPhone_no(),
                entity.getAge(),
                entity.getGender(),
                entity.getHobbies(),
                addressResponse
        );
    }

    public UserEntity updateDTO(UserRequest request, UserEntity entity) {
        entity.setName(request.name());
        entity.setAge(request.age());
        entity.setEmail(request.email());
        entity.setGender(request.gender());
        entity.setHobbies(request.hobbies());
        entity.setPhone_no(request.phone_no());
        entity.setPassword(request.password());

        return entity;

    }

    public AddressEntity toAddressEntity(AddressRequest addressRequest) {
        AddressEntity address = new AddressEntity();
        address.setStreet(addressRequest.street());
        address.setCity(addressRequest.city());
        address.setState(addressRequest.state());
        address.setCountry(addressRequest.country());
        address.setPincode(addressRequest.pincode());

        return address;
    }

    public AddressResponse toAddressResponseDto(AddressEntity entity) {
        return new AddressResponse(
                entity.getId(),
                entity.getStreet(),
                entity.getCity(),
                entity.getState(),
                entity.getCountry(),
                entity.getPincode()
        );
    }

    public AddressEntity updateAddress(AddressRequest request, AddressEntity entity) {
        entity.setStreet(request.street());
        entity.setCity(request.city());
        entity.setState(request.state());
        entity.setCountry(request.country());
        entity.setPincode(request.pincode());

        return entity;
    }

    public OrderEntity toOrderEntity(OrderRequest request) {
        OrderEntity order = new OrderEntity();
        order.setProductName(request.productName());
        order.setQuantity(request.quantity());
        order.setPrice(request.price());

        return order;
    }

    public OrderResponse toOrderResponseDTO(OrderEntity entity) {
        return new OrderResponse(
                entity.getId(),
                entity.getUser().getId(),
                entity.getProductName(),
                entity.getPrice(),
                entity.getQuantity(),
                entity.getTotalAmount(),
                entity.getOrderStatus(),
                entity.getOrderDate()
        );
    }
}