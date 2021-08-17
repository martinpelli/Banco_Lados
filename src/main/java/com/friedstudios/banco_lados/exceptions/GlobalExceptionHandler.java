package com.friedstudios.banco_lados.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> defaultErrorHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        return new ResponseEntity<ErrorMessage>(new ErrorMessage("Error genérico",e.getMessage(),"1",request.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> notFoundHandler(HttpServletRequest request, Exception e){
        return new ResponseEntity<ErrorMessage>(new ErrorMessage("Error genérico",e.getMessage(),"2",request.getRequestURI()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseBody
    public ResponseEntity<ErrorMessage> badRequestExceptions(HttpServletRequest request, Exception e) {
        return new ResponseEntity<ErrorMessage>(new ErrorMessage("Bad Request",e.getMessage(),"3",request.getRequestURI()), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({NotAuthorizedException.class, UsernameNotFoundException.class, BadCredentialsException.class})
    @ResponseBody
    public ResponseEntity<ErrorMessage> notAuthorizedExceptionHandler(HttpServletRequest request, Exception e){
        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage("Not Authorized",
                        e.getMessage(),
                        "3",
                        request.getRequestURI()),
                HttpStatus.UNAUTHORIZED);


    }
}
