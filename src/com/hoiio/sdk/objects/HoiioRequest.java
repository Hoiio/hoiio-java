package com.hoiio.sdk.objects;

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

import java.util.HashMap;

import com.hoiio.sdk.exception.HoiioException;

public class HoiioRequest extends HashMap<String, String> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs the {@code HoiioRequest} object
	 */
	public HoiioRequest() {
		
	}

	/**
	 * Puts the parameter into the Map. If the parameter is required, check for nullablity.
	 * @param key The key of the parameter
	 * @param value The value of the parameter
	 * @param isRequired The indicator of whether this parameter is required or not. 
	 * @throws HoiioException
	 */
	public void put(String key, Object value, boolean isRequired) throws HoiioException {
		if (isRequired) {
			if (value == null) {
				throw new HoiioException("error_" + key + "_param_missing");
			}
			put(key, String.valueOf(value));
		} else {
			if (value != null) {
				put(key, String.valueOf(value));
			}
		}		
	}
}
