package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.ExecutionTimer;
import com.example.demo.exception.DataHandlingException;
import com.example.demo.mdel.Employee;
import com.example.demo.service.Exceptionservice;

@RestController
public class TestController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private Exceptionservice exceptionService;

	@ExecutionTimer
	@GetMapping("/check")
	public String checkExecutionTime() throws InterruptedException {
		Thread.sleep(2000);
		return "Sleep done "+env.getProperty("config.name");
	}
	
	@PostMapping("/json")
	public String getJsonData(@RequestBody Employee employee, HttpServletRequest request) {
		String token = request.getHeader("token").split("\\.")[1];
		String data = new String(java.util.Base64.getDecoder().decode(token));
		System.out.println(data);
		System.out.println("checking"+data);
		JSONObject obj = new JSONObject(data);
		System.out.println(obj);
		JSONObject employeeJson = new JSONObject(employee);
		System.out.println(employee.toString());
		System.out.println("checking test data");
		return employeeJson.getString("location");
	}
	
	@GetMapping("/exception")
	public ResponseEntity<Map<String, Object>> checkException(@RequestHeader(value="Exception") String value) throws DataHandlingException{
		return exceptionService.exception(value);
	}
}
