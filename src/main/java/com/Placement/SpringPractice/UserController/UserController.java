package com.Placement.SpringPractice.UserController;

import com.Placement.SpringPractice.DTOs.UserRequest;
import com.Placement.SpringPractice.DTOs.UserResponse;
import com.Placement.SpringPractice.UserService.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.OptionalValueBinding;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController{

    @Autowired
    UserService service;

    @PostMapping("/create-user")
    public UserResponse createUser(@RequestBody @Valid UserRequest request){
        return service.saveUser(request);
    }

    @GetMapping("/get-users")
    public List<UserResponse> getAllUser(){
        return service.getUser();
    }
    
    @GetMapping("/search-user/{id}")
    public Optional<UserResponse> searchUserById(@PathVariable Long id){
        return service.getById(id);
    }

    @PutMapping("/update-user/{id}")
    public Optional<UserResponse> updateUserById(@PathVariable long id, @RequestBody UserRequest request){
        return service.updateUser(id,request);
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUSerByID(@PathVariable Long id){
        service.deleteUser(id);
        return "User with id " + id + " deleted successfully";
    }

    @GetMapping("/searchByEmail/{email}")
    public Optional<UserResponse> searchByEmail(@PathVariable String email){
        return service.searchEmail(email);
    }

    @GetMapping("/searchByName/{name}")
    public Optional<UserResponse> searchByName(@PathVariable String name){
        return service.searchByName(name);
    }

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }
}