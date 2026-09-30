package com.Placement.SpringPractice.UserService;


import com.Placement.SpringPractice.DTOMapper.DTOMapper;
import com.Placement.SpringPractice.DTOs.UserRequest;
import com.Placement.SpringPractice.DTOs.UserResponse;
import com.Placement.SpringPractice.UserEntity.UserEntity;
import com.Placement.SpringPractice.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    DTOMapper map;

    public UserResponse saveUser(UserRequest request){
        UserEntity user = map.toEntity(request);

        UserEntity savedUser = userRepository.save(user);

        return map.toDTO(savedUser);
    }

    public List<UserResponse> getUser(){
        List<UserEntity> getUser = userRepository.findAll();

        return getUser.stream()
                .map(map::toDTO)
                .toList();
    }

    public Optional<UserResponse> getById(Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        return user.map(e -> map.toDTO(e));
    }

    public Optional<UserResponse> updateUser(Long id, UserRequest request){
        //find user want to update
        Optional<UserEntity> old_data = userRepository.findById(id);
        //map user to updateDTO, save user to database and return updated Data in response DTO
        return old_data.map(e -> {
            UserEntity updateUser = map.updateDTO(request,e);
            UserEntity savedUser = userRepository.save(updateUser);
            return new UserResponse(
                    savedUser.getName(),
                    savedUser.getEmail(),
                    savedUser.getPhone_no(),
                    savedUser.getAge(),
                    savedUser.getGender(),
                    savedUser.getHobbies()
            );
        });
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public Optional<UserResponse> searchEmail(String email){
        Optional<UserEntity> user = userRepository.findByEmail(email);

      return user.map(e -> map.toDTO(e));
    }

    public Optional<UserResponse> searchByName(String name){
        Optional<UserEntity> user = userRepository.findByName(name);

        return user.map(e -> map.toDTO(e));
    }
}