package com.hoiio.sdk.exception;

public class HoiioException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String exception;
	
	public HoiioException() {
		super();
	}
	
	public HoiioException(String exception) {
		super(exception);
		this.exception = exception;
	}
	
	public String getException() {
		return exception;
	}
}
