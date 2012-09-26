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

import net.sf.json.JSONObject;

import com.hoiio.sdk.objects.HoiioResponse;
import com.hoiio.sdk.objects.enums.Currency;

public class FaxRate extends HoiioResponse {
	
	private static enum Params {
		CURRENCY, RATE;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private Currency currency;
	private double rate;
	
	/**
	 * Constructs a new {@code FaxRate} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 */
	public FaxRate(JSONObject output) {
		response = output.toString();
		
		currency = Currency.fromString(output.getString(Params.CURRENCY.toString()));
		rate = output.getDouble(Params.RATE.toString());
	}
	
	/**
	 * Gets the currency used for the rate amount
	 * @return Currency used for the rate amount. 
	 */
	public Currency getCurrency() {
		return currency;
	}
	
	/**
	 * Gets the per-minute rate for fax
	 * @return Billable per-minute rate.
	 */
	public double getRate() {
		return rate;
	}
}
