package com.thinklink.cryptocurrencytracker.exception;

public class BadRequestException extends Exception {

	private static final long serialVersionUID = -5730575496592599903L;

	public BadRequestException(String exception){
		super(exception);
	}
}
