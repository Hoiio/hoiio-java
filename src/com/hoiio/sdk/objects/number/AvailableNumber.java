package com.hoiio.sdk.objects.number;

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

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hoiio.sdk.objects.HoiioResponse;

public class AvailableNumber extends HoiioResponse {
	
	private static enum Params {
		ENTRIES_COUNT, TOTAL_ENTRIES_COUNT, ENTRIES, ENTRY_NUMBER;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private int totalCount;
	private int pageCount;
	private List<String> numberList;
	
	/**
	 * Constructs a new {@code AvailableNumber} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 */
	public AvailableNumber(JSONObject output) {
		response = output.toString();
		
		totalCount = output.getInt(Params.TOTAL_ENTRIES_COUNT.toString());
		pageCount = output.getInt(Params.ENTRIES_COUNT.toString());
		numberList = new ArrayList<String>();
		
		JSONArray entries = (JSONArray) output.get(Params.ENTRIES.toString());
		
		for (int i = 0; i < entries.size(); i++) {
			numberList.add(entries.getString(i));
		}		
	}

	/**
	 * Gets the list of available Hoiio Numbers
	 * @return List of available Hoiio Numbers
	 */
	public List<String> getNumberList() {
		return numberList;
	}

	/**
	 * Gets the number of available Hoiio Numbers returned in this response
	 * @return The number of available Hoiio Numbers returned in this response.
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * Gets the total number of available Hoiio Numbers returned in this response
	 * @return The total number of available Hoiio Numbers in the country specified in the request.
	 */
	public int getTotalCount() {
		return totalCount;
	}
	
}
