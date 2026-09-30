package com.Placement.SpringPractice.DTOs;

import java.util.List;

public record UserResponse(
    String name,
    String email,
    String phone_no,
    Integer age,
    String gender,
    List<String> hobbies
) {
}