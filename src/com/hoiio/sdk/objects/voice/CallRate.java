package com.hoiio.sdk.objects.voice;

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

public class CallRate extends HoiioResponse {

	private static final String RATE = "rate";
	private static final String CURRENCY = "currency";
	private static final String TALK_TIME = "talktime";
	
	private Currency currency;
	private double rate;
	private int talkTime;
	
	/**
	 * Constructs a new {@code CallRate} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 */
	public CallRate(JSONObject output) {
		response = output.toString();
		
		currency = Currency.fromString(output.getString(CURRENCY));
		rate = output.getDouble(RATE);
		talkTime = output.getInt(TALK_TIME);		
	}
	
	/**
	 * Gets the currency used for this rate amount
	 * @return Currency used for this rate amount. 
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Gets the billable per-minute rate
	 * @return Billable per-minute rate. Hoiio bill calls using per-minute block.
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * Gets the estimated maximum talk time
	 * @return Estimated maximum talk time in minutes.
	 */
	public int getTalkTime() {
		return talkTime;
	}
}
