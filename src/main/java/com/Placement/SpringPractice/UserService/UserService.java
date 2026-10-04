package com.Placement.SpringPractice.UserService;

import com.Placement.SpringPractice.AddressEntity.AddressEntity;

import com.Placement.SpringPractice.DTOMapper.DTOMapper;
import com.Placement.SpringPractice.DTOs.AddressResponse;
import com.Placement.SpringPractice.DTOs.UserRequest;
import com.Placement.SpringPractice.DTOs.UserResponse;
import com.Placement.SpringPractice.Exception.ResourceNotFoundException;
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
        AddressEntity address = map.toAddressEntity(request.addressRequest());
        //connect both entities
        user.setAddress(address);
        address.setUser(user);


        UserEntity savedUser = userRepository.save(user);
        return map.toDTO(savedUser);
    }

    public List<UserResponse> getUser(){
        List<UserEntity> getUser = userRepository.findAll();

        return getUser.stream()
                .map(map::toDTO)
                .toList();
    }

    public UserResponse getById(Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        return user.map(e -> map.toDTO(e)).orElseThrow(() -> new ResourceNotFoundException("User with id :- " + id + "not found"));

    }

    public Optional<UserResponse> updateUser(Long id, UserRequest request){
        //find user want to update
        Optional<UserEntity> old_data = userRepository.findById(id);
        //map user to updateDTO, save user to database and return updated Data in response DTO
        return old_data.map(e -> {
            //convert userEntity to UpdateDTO
           UserEntity updateUser = map.updateDTO(request,e);

           //convert AddressEntity to UpdateAddressDTO
           AddressEntity updateAddress = map.updateAddress(request.addressRequest(),e.getAddress());

           //set UpdatedAddress in UserEntity
           updateUser.setAddress(updateAddress);

           //set UpdateUser in AddressEntity
           updateAddress.setUser(updateUser);

           //Save user to database
           UserEntity savedUser = userRepository.save(updateUser);

           //convert updateAddressEntity to AddressDTO
           AddressResponse addressResponse = map.toAddressResponseDto(updateAddress);
            return new UserResponse(
                    savedUser.getName(),
                    savedUser.getEmail(),
                    savedUser.getPhone_no(),
                    savedUser.getAge(),
                    savedUser.getGender(),
                    savedUser.getHobbies(),
                    addressResponse
            );
        });
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public UserResponse searchEmail(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        return user.map(e -> map.toDTO(e)).orElseThrow(() -> new ResourceNotFoundException("User with email :- " + email + " not found"));
    }

    public UserResponse searchByName(String name){
        Optional<UserEntity> user = userRepository.findByName(name);
        return user.map(e -> map.toDTO(e)).orElseThrow(() -> new ResourceNotFoundException("User with name :- " + name + "is not found"));
    }
}