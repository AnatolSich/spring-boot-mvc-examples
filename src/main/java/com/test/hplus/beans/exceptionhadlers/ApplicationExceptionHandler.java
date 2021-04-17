package com.test.hplus.beans.exceptionhadlers;

import com.test.hplus.exceptions.ApplicationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler({ApplicationException.class, AsyncRequestTimeoutException.class})
    public String handleException() {
        System.out.println("in global exception handler");
        return "error";
    }
}
