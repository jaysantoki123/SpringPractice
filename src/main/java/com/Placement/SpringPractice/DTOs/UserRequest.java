package com.Placement.SpringPractice.DTOs;

import jakarta.validation.constraints.*;
import java.util.List;

public record UserRequest(

        @NotBlank(message = "Name is required")
        @Size(min = 2, message = "Name must be at least 2 characters long")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Gender is required")
        String gender,

        @NotNull(message = "Age is required")
        @Min(value = 18, message = "Age must be at least 18 years")
        @Max(value = 40, message = "Age must be at most 40 years")
        Integer age,

        @NotEmpty(message = "Hobbies are required")
        List<@NotBlank(message = "Hobby cannot be empty")
        String> hobbies,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password,

        @NotBlank(message = "Phone number is required")
        @Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits long")
        @Pattern(regexp = "^[0-9]*$", message = "Phone number must contain only numbers")
        String phone_no,

        AddressRequest addressRequest

){}
