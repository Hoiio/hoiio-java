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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class StringUtil {
	
	/**
	 * Converts the {@code Map} to URL encoded {@code String}
	 * @param map {@code Map<String, String>}
	 * @return The encoded URL {@code String}
	 */
	public static String convertMapToUrlEncodedString(Map<String, String> map) {
		if (map == null) {
			return "";
		}
		List<NameValuePair> qparams = new LinkedList<NameValuePair>();
		for (Entry<String, String> item : map.entrySet()) {
			if (item.getValue() != null)
				qparams.add(new BasicNameValuePair(item.getKey(), item.getValue()));
		}
		return URLEncodedUtils.format(qparams, "UTF-8");
	}
	
	/**
	 * Converts the {@code List} to comma-separated {@code String}
	 * @param dests {@code List<String>}
	 * @return The comma-separated {@code String}
	 */
	public static String convertListToString(List<String> dests) {
		StringBuilder destString = new StringBuilder();
		for (String dest : dests) {
			destString.append(dest + ",");
		}
		
		// remove the last ","
		destString.deleteCharAt(destString.length() - 1);
		return destString.toString();
	}
	
	/**
	 * Checks for the {@code String} is empty or not. It checks both nullability and emptiness
	 * @param str {@code String} to check
	 * @return whether this string is empty or not
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.isEmpty());
	}
}
