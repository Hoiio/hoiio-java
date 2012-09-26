package com.hoiio.sdk.exception;

/*
Copyright (C) 2012 Hoiio Pte Ltd (http://www.hoiio.com)

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/

/**
 * This class is thrown whenever there is an unexpected exception occurs
 */
public class InternalServerErrorException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs the object
	 */
	public InternalServerErrorException() {
		super();
	}
	
	/**
	 * Constructs the object
	 * @param cause The unexpected exception 
	 */
	public InternalServerErrorException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs the object
	 * @param message The exception message
	 */
	public InternalServerErrorException(String message) {
		super(message);
	}

}
