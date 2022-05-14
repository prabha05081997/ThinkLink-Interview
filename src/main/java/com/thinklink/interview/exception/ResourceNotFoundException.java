package com.thinklink.interview.exception;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -2357864674039598337L;
	
	public ResourceNotFoundException(String exception){
		super(exception);
	}
    
}
