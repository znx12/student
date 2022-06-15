package com.demo.exception;

import com.demo.util.ResponseUtil;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionResolver {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Object resolve(){
        return ResponseUtil.badArgument();
    }
}
