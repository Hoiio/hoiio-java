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

public class SmsTxn extends HoiioResponse {
	
	private static enum Params {
		TXN_REF;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private String txnRef;

	/**
	 * Constructs a new {@code SmsTxn} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 */
	public SmsTxn(JSONObject output) {
		response = output.toString();
		
		txnRef = output.getString(Params.TXN_REF.toString());		
	}
	
	/**
	 * Gets the unique reference ID for this transaction
	 * @return A unique reference ID for this SMS transaction. This parameter will not be present if the request was not successful.
	 */
	public String getTxnRef() {
		return txnRef;
	}
}
