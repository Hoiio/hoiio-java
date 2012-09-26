package com.hoiio.sdk.util;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hoiio.sdk.exception.HoiioException;

public class DateUtil {
	
	/**
	 * Converts the {@code date} to {@code String} 
	 * @param date The {@code Date} object
	 * @return The date in this format "yyyy-MM-dd HH:mm:ss"
	 */
	public static String dateToString(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	/**
	 * Converts the @{code String} in date/time format to {@code Date}
	 * @param dateString The date in this format "yyyy-MM-dd HH:mm:ss"
	 * @return The {@code Date} object
	 * @throws HoiioException
	 */
	public static Date stringToDateTime(String dateString) throws HoiioException {
		Date date = null;
	
		if (dateString != null) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
			} catch (ParseException e) {
				throw new HoiioException(e);
			}		
		}
		return date;
	}
	
	/**
	 * Converts the @{code String} in date format to {@code Date}
	 * @param dateString The date in this format "yyyy-MM-dd"
	 * @return The {@code Date} object
	 * @throws HoiioException
	 */
	public static Date stringToDate(String dateString) throws HoiioException {
		Date date = null;

		if (dateString != null) {
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
			} catch (ParseException e) {
				throw new HoiioException(e);
			}	
		}
		return date;
	}
}
