package com.example.javabackend.model;

import lombok.Data;

@Data
public class MResgisterRequest {
    private String email;

    private String password;

    private String name;
}
