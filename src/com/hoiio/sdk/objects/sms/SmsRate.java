package com.hoiio.sdk.objects.sms;

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

public class SmsRate extends HoiioResponse {
	
	private static enum Params {
		RATE, CURRENCY, SPLIT_COUNT, TOTAL_COST, IS_UNICODE;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private Currency currency;
	private double rate;
	private Integer splitCount;
	private Double totalCost;
	private Boolean isUnicode;
	
	/**
	 * Constructs a new {@code SmsRate} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 */
	public SmsRate(JSONObject output) {
		response = output.toString();
		
		currency = Currency.fromString(output.getString(Params.CURRENCY.toString()));
		rate = output.getDouble(Params.RATE.toString());
		
		if (output.containsKey(Params.SPLIT_COUNT.toString())) {
			splitCount = output.getInt(Params.SPLIT_COUNT.toString());
		}
		
		if (output.containsKey(Params.TOTAL_COST.toString())) {
			totalCost = output.getDouble(Params.TOTAL_COST.toString());
		}
		
		if (output.containsKey(Params.IS_UNICODE.toString())) {
			isUnicode = output.getBoolean(Params.IS_UNICODE.toString());
		}		
	}

	/**
	 * Gets the currency used for this rate amount
	 * @return Currency used for this rate amount.
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Returns whether the message content is unicode or not (only returned if the {@code msg} parameter is included in the request)
	 * @return Indicates if the message content is unicode.
	 */
	public Boolean isUnicode() {
		return isUnicode;
	}

	/**
	 * Gets the charge per sms
	 * @return Billable rate per multipart SMS rate.
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * Gets the number of multipart SMS that this message will be split into (only returned if {@code msg} parameter is included in the request)
	 * @return The number of multipart SMS that this message will be split into.
	 */
	public Integer getSplitCount() {
		return splitCount;
	}

	/**
	 * Gets the total amount that will be billed for sending this SMS (only returned if {@code msg} parameter is included in the request)
	 * @return The total amount that will be billed for sending this SMS.
	 */
	public Double getTotalCost() {
		return totalCost;
	}	
	
}
