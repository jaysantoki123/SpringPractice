package com.Placement.SpringPractice.DTOs;

public record AddressResponse(
        Long id,
        String street,
        String city,
        String state,
        String country,
        String pincode
){
}