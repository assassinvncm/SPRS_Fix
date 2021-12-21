package com.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jwt.service.JwtUserDetailsService;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	public static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	public GlobalExceptionHandler() {
		// TODO Auto-generated constructor stub
	}
	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        logger.error(ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
	
	@ExceptionHandler(AuthenticationException.class)
	public final ResponseEntity<Object> handleAuthenException(AuthenticationException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), details);
        logger.error(ex.getLocalizedMessage());
        return ResponseEntity.status(401).body(error);
    }
	
    @ExceptionHandler(AppException.class)
    public final ResponseEntity<Object> handleAppException(AppException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(ex.getCode(),ex.getLocalizedMessage(), details);
        logger.error(ex.getLocalizedMessage());
        return ResponseEntity.status(200).body(error);
    }
    
 
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        logger.error(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(400,"Validation Failed", details);
        return ResponseEntity.status(200).body(error);
    }
}
