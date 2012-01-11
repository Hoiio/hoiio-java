package com.hoiio.sdk.exception;

public class InternalServerErrorException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public InternalServerErrorException() {
		super();
	}
	
	public InternalServerErrorException(Throwable cause) {
		super(cause);
	}

	public InternalServerErrorException(String message) {
		super(message);
	}

}
