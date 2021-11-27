package com.example.pagamento.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CrudExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ResponseException> handlerNotFoundException( Exception ex, WebRequest request){
		ResponseException responseException = new ResponseException(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ResponseException>(responseException, HttpStatus.NOT_FOUND);
	}
	
	public final ResponseEntity<ResponseException> handlerBadRequestException( Exception ex, WebRequest request){
		ResponseException responseException = new ResponseException(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ResponseException>(responseException, HttpStatus.BAD_REQUEST);
	}

}
