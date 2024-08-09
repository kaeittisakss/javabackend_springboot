package com.example.javabackend.exception;

public class ProductExcrption extends BaseException {

    public ProductExcrption(String code) {
        super("product." + code);
    }

    public static ProductExcrption notFound() {
        return new ProductExcrption("not.found");
    }
}
