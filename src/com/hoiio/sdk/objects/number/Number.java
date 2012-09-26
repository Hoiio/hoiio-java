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
import com.hoiio.sdk.objects.enums.AutoExtendStatus;
import com.hoiio.sdk.util.DateUtil;

public class Number {
	
	private static enum Params {
		NUMBER, FORWARD_TO, FORWARD_SMS_TO, EXPIRY, AUTO_EXTEND_STATUS, COUNTRY, STATE, MODE;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private String number;
	private String forwardTo;
	private String forwardToSms;
	private Date expiry;
	private AutoExtendStatus autoExtendStatus;
	private String country;
	private String state;
	private String mode;
	
	/**
	 * Constructs a new {@code Number} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 */
	public Number(JSONObject output) throws HoiioException {
		number = output.toString();
		forwardTo = output.getString(Params.FORWARD_TO.toString());
		forwardToSms = output.getString(Params.FORWARD_SMS_TO.toString());
		expiry = DateUtil.stringToDate(output.getString(Params.EXPIRY.toString()));
		autoExtendStatus = AutoExtendStatus.fromString(output.getString(Params.AUTO_EXTEND_STATUS.toString()));
		country = output.getString(Params.COUNTRY.toString());
		state = output.getString(Params.STATE.toString());
		mode = output.getString(Params.MODE.toString());
	}

	/**
	 * Gets the auto extend status of this number
	 * @return Whether this number is automatically extended. Allowed values: enabled, disabled.
	 */
	public AutoExtendStatus getAutoExtendStatus() {
		return autoExtendStatus;
	}

	/**
	 * Gets the country that this number belong to
	 * @return The country that this Hoiio Number belong to in ISO 3166-1 alpha-2 format.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Gets the expiry date for this number
	 * @return The expiry date for this Hoiio Number in "YYYY-MM-DD" format (GMT+8).
	 */
	public Date getExpiry() {
		return expiry;
	}

	/**
	 * Gets the URL of the forward to
	 * @return A fully-qualified HTTP/S URL on your web server to be notified when there is an incoming call to this Hoiio Numbe
	 */
	public String getForwardTo() {
		return forwardTo;
	}

	/**
	 * Gets the URL of the forward to sms
	 * @return A fully-qualified HTTP/S URL on your web server to be notified when there is an incoming call to this Hoiio Numbe
	 */
	public String getForwardToSms() {
		return forwardToSms;
	}

	/**
	 * Gets the Hoiio Number assigned to this application
	 * @return The Hoiio Number assigned to your application. Phone numbers start with a "+" and country code (E.164 format), e.g. +6511111111.
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Gets the state that this number belong to
	 * @return The state that this Hoiio Number belong to in ISO 3166-2 format. Is an empty string if the number is not state specific.
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Gets the mode of this number
	 * @return The mode of this number: voice, fax
	 */
	public String getMode() {
		return mode;
	}
	
}
