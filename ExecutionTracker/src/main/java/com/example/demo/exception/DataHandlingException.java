package com.example.demo.exception;

public class DataHandlingException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3054841453264905624L;
	
	private final String code;
	private final String message;
	
	public DataHandlingException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		System.out.println(code);
		return code;
	}
	public String getMessage() {
		System.out.println(message);
		return message;
	}
	@Override
	public String toString() {
		return "DataHandlingException [code=" + code + ", message=" + message + "]";
	}
	
	
	
	
}
