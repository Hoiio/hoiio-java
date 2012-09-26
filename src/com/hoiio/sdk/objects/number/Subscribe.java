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

import java.util.Date;

import net.sf.json.JSONObject;

import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.objects.HoiioResponse;
import com.hoiio.sdk.objects.enums.Currency;
import com.hoiio.sdk.util.DateUtil;

public class Subscribe extends HoiioResponse {
	
	private static enum Params {
		CURRENCY, DEBIT, EXPIRY;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private Currency currency;
	private double debit;
	private Date expiry;
	
	/**
	 * Constructs a new {@code Subscribe} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 */
	public Subscribe(JSONObject output) throws HoiioException {
		response = output.toString();
		
		currency = Currency.fromString(output.getString(Params.CURRENCY.toString()));
		debit = output.getDouble(Params.DEBIT.toString());
		expiry = DateUtil.stringToDateTime(output.getString(Params.EXPIRY.toString()));
	}

	/**
	 * Gets the currency used for this transaction
	 * @return Currency used for this transaction. 
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Gets the total amount billed for this transaction
	 * @return Total amount billed for this transaction.
	 */
	public double getDebit() {
		return debit;
	}

	/**
	 * Gets the expiry date for this number
	 * @return The expiry date for this Hoiio Number in "YYYY-MM-DD" format (GMT+8).
	 */
	public Date getExpiry() {
		return expiry;
	}
	
}
