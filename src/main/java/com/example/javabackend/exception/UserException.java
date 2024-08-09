package com.example.javabackend.exception;

public class UserException extends BaseException {

    public UserException(String code) {
        super("user." + code );
    }

    public static UserException notFound() {
        return new UserException("user.not.found");
    }

    public static UserException requestNull() {
        return new UserException("register request.null");
    }

    public static UserException emailNull() {
        return new UserException("register.email.null");
    }


    // CERATE
    public static UserException createEmailNull() {
        return new UserException("register.email.null");
    }
    public static UserException createEmailDuplicated() {
        return new UserException("register.email.null");
    }

    public static UserException createPasswordNull() {
        return new UserException("register.password.null");
    }

    public static UserException createNameNull() {
        return new UserException("register.name.null");
    }


    // LOGIN
    public static UserException loginFailEmailNotFound() {
        return new UserException("login.fail");
    }

    public static UserException loginFailPasswordNotFound() {
        return new UserException("login.fail");
    }
}
