package com.example.javabackend.business;

import com.example.javabackend.exception.BaseException;
import com.example.javabackend.exception.ProductExcrption;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductBusiness {
    public String getProductById(String id) throws BaseException {
        //TODO: get data from database
        if (Objects.equals("1234", id)) {
            throw ProductExcrption.notFound();
        }
        return id;
    }
}