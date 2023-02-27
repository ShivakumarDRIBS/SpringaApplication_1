package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.constants.ExceptionConstant;
import com.example.demo.model.ValidationResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = DataHandlingException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ResponseEntity<Object> handleException(DataHandlingException ex) {
		Map<String, Object> map = new HashMap<>();
			map.put(ExceptionConstant.error, getErrorList(ex.getCode(), ex.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
	}
	
	private ValidationResponse getErrorList(String code, String message){
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setCode(code);
		validationResponse.setMessage(message);
		return validationResponse;
	}
}
