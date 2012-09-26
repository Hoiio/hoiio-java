package com.hoiio.sdk.objects.fax;

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

import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.objects.HoiioResponse;

public class FaxHistory extends HoiioResponse {
	
	private static enum Params {
		ENTRIES_COUNT, TOTAL_ENTRIES_COUNT, ENTRIES;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private int pageCount;
	private int totalCount;
	private List<Fax> faxList;
	
	/**
	 * Constructs a new {@code FaxHistory} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 * @throws HoiioException
	 */
	public FaxHistory(JSONObject output) throws HoiioException {
		response = output.toString();
		
		pageCount = output.getInt(Params.ENTRIES_COUNT.toString());
		totalCount = output.getInt(Params.TOTAL_ENTRIES_COUNT.toString());
		faxList = new ArrayList<Fax>();
		
		JSONArray entries = (JSONArray) output.get(Params.ENTRIES.toString());
		
		for (int i = 0; i < entries.size(); i++) {
			faxList.add(new Fax(entries.getJSONObject(i)));
		}
		
	}

	/**
	 * Gets the list of all @{code Fax} objects in the period specified in the request
	 * @return List of all @{code Fax} objects in the period specified in the request.
	 */
	public List<Fax> getFaxList() {
		return faxList;
	}

	/**
	 * Gets the number of the fax history entries returned in this response
	 * @return The number of fax history entries returned in this response.
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * Gets the total number of fax history entries returned in this response
	 * @return The total number of fax history entries returned in this response.
	 */
	public int getTotalCount() {
		return totalCount;
	}	
}
