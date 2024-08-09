package com.example.javabackend.mapper;


import com.example.javabackend.entity.User;
import com.example.javabackend.model.MRegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    MRegisterResponse toRegisterResponse(User user);
}
