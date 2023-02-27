package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.constants.ExceptionConstant;
import com.example.demo.exception.DataHandlingException;
import com.example.demo.model.ValidationResponse;

@Service
public class Exceptionservice {

	public ResponseEntity<Map<String, Object>> exception(String exception) throws DataHandlingException{
		
		Map<String, Object> map = new HashMap<>();
		if(exception.length()<3) {
			//map.put(ExceptionConstant.error, getErrorList("1001", "input value is empty"));
			System.out.println("exception message");
			throw new DataHandlingException("1002", "value is empty");
		}
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
	}
	
	private ValidationResponse getErrorList(String code, String message){
		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setCode(code);
		validationResponse.setMessage(message);
		System.out.println("get error list in exception service");
		return validationResponse;
	}
}
