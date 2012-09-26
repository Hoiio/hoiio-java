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
import com.hoiio.sdk.objects.enums.Currency;

public class NumberRate extends HoiioResponse {
	
	private static enum Params {
		CURRENCY, ENTRIES_COUNT, ENTRIES;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private Currency currency;
	private int count;
	private List<Rate> rateList;
	
	/**
	 * Constructs a new {@code NumberRate} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 */
	public NumberRate(JSONObject output) {
		response = output.toString();
		
		currency = Currency.fromString(output.getString(Params.CURRENCY.toString()));
		count = output.getInt(Params.ENTRIES_COUNT.toString());
		rateList = new ArrayList<Rate>();
		
		JSONArray entries = (JSONArray) output.get(Params.ENTRIES.toString());
		
		for (int i = 0; i < entries.size(); i++) {
			rateList.add(new Rate(entries.getJSONObject(i)));
		}
		
	}

	/**
	 * Gets the number of entries returned
	 * @return The number of entries returned in this response.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Gets the currency used in this response
	 * @return Currency used in this response.
	 */
	public Currency getCurrency() {
		return currency;
	}
	
	/**
	 * Gets the list of all the rates available for this country
	 * @return List of all the rates available for this country.
	 */
	public List<Rate> getRateList() {
		return rateList;
	}	
}
