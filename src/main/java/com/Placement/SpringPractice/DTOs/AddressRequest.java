package com.Placement.SpringPractice.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressRequest(
        @NotBlank(message = "Street name is required")
        String street,

        @NotBlank(message = "City name is required")
        String city,

        @NotBlank(message = "State name is required")
        String state,

        @NotBlank(message = "Country name is required")
        String country,

        @NotBlank(message = "Pincode is required")
        @Pattern(
                regexp = "^[0-9]{6}$",
                message = "Pincode must be 6 digits"
        )
        String pincode
){
}
