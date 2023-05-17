package com.club.memberinfo.exception;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;



import feign.FeignException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalException(MemberInfoCustomException ex,WebRequest request)
	{
		System.out.println("Updated FeignException: "+ex.getMessage()+" / "+ex.getLocalizedMessage()+" / "+ex.statusCode);
		ErrorMessage message = new ErrorMessage(ex.statusCode,
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorMessage>(message,ex.httpStatus);
	}
	
}
