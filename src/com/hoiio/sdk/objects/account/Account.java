package com.hoiio.sdk.objects.account;

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

public class Account extends HoiioResponse {
	
	private static enum Params {
		UID, NAME, MOBILE_NUMBER, EMAIL, COUNTRY, PREFIX, CURRENCY;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private String uid;
	private String name;
	private String mobileNumber;
	private String email;
	private String country;
	private String prefix;
	private Currency currency;
	
	/**
	 * Constructs a new {@code Account} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 */
	public Account(JSONObject output) {
		response = output.toString();
		
		uid = output.getString(Params.UID.toString());
		name = output.getString(Params.NAME.toString());
		mobileNumber = output.getString(Params.MOBILE_NUMBER.toString());
		country = output.getString(Params.COUNTRY.toString());
		prefix = output.getString(Params.PREFIX.toString());
		currency = Currency.fromString(output.getString(Params.CURRENCY.toString()));
		
		if (output.containsKey(Params.EMAIL.toString())) {
			email = output.getString(Params.EMAIL.toString());
		}
	}

	/**
	 * Gets the country of this account
	 * @return The country of this account in ISO 3166-1 alpha-2 format (e.g. SG).
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Gets the currency of this account
	 * @return Currency used for this account. 
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Gets the email address of this account
	 * @return The email address of this account. This is present only if the email address is verified.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the registered number of this account
	 * @return The registered number of this account. Phone number starts with a "+" and country code (E.164 format), e.g. +6511111111.
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * Gets the full name of this account
	 * @return The full name of this account.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the country code prefix of this account
	 * @return The country code prefix of this account (e.g. +65)
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * Gets the user ID of this account
	 * @return The user ID of this account.
	 */
	public String getUid() {
		return uid;
	}
}
