package com.thinklink.interview.exception;

public class UnauthorizedException extends Exception{
	private static final long serialVersionUID = 2670653419425740630L;

	public UnauthorizedException (String exception){
		super(exception);
	}
}
