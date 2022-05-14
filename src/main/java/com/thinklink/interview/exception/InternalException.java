package com.thinklink.interview.exception;

public class InternalException extends RuntimeException{

	private static final long serialVersionUID = 7903220993753180932L;

	public InternalException(String exception) {
		super(exception);
	}
}
