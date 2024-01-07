package com.hbx.project.hnetdiscback.handler;

import com.hbx.project.hnetdiscback.entity.vo.AopResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public AopResponse SystemExceptionHandler(Exception e){
        return new AopResponse().fail(e.getMessage());
    }
}
