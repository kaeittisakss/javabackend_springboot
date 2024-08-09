package com.example.javabackend.business;

import com.example.javabackend.entity.User;
import com.example.javabackend.exception.BaseException;
import com.example.javabackend.exception.FileException;
import com.example.javabackend.exception.UserException;
import com.example.javabackend.mapper.UserMapper;
import com.example.javabackend.model.MLoginRequest;
import com.example.javabackend.model.MRegisterResponse;
import com.example.javabackend.model.MResgisterRequest;
import com.example.javabackend.repository.UserRepository;
import com.example.javabackend.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserBusiness {


    private final UserService userService;

    private final UserMapper userMapper;

    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public String login(MLoginRequest request) throws BaseException {
        // validate request

        // verify database
        Optional<User> opt = userService.findByEmail(request.getEmail());
        if ( opt.isEmpty()) {
            // throw login fail, email not found
            throw UserException.loginFailEmailNotFound();
        }
        User user = opt.get();
        if (!userService.matchPassword(request.getPassword(), user.getPassword())) {
            // throw login fail, password incorrect
            throw UserException.loginFailPasswordNotFound();
        }
            // TODO: generate JWT
            String token = "JWWT to DO";

            return token;

    }


    public MRegisterResponse register(MResgisterRequest request) throws BaseException {
        User user = userService.create(request.getEmail(), request.getPassword(),request.getName());

        return userMapper.toRegisterResponse(user);


//        if (request == null) {
//            throw UserException.requestNull();
//        }
//
//        // validate email
//        if (Objects.isNull(request.getEmail())) {
//            throw UserException.emailNull();
//        }
//        return "";

    }

    public String uploadProfilePicture(MultipartFile file) throws BaseException {
        // validate file
        if ( file == null ) {
            throw  FileException.fileNull();
        }

        // validate size
        if ( file.getSize() > 1028576 * 2 ) {
            // throw error
            throw  FileException.fileMaxSize();
        }

        String contentType = file.getContentType();
        if (contentType == null) {
            throw  FileException.unsupported();
        }


        List<String> supportedTypes = Arrays.asList("image/jpeg", "image/png");
        if (!supportedTypes.contains(contentType)) {
            throw FileException.unsupported();
        }

        // TODO: upload file File Storage (AWS S3, etc...)
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
