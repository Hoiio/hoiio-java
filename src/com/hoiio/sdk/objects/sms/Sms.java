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

import java.util.Date;

import net.sf.json.JSONObject;

import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.objects.HoiioResponse;
import com.hoiio.sdk.objects.enums.Currency;
import com.hoiio.sdk.objects.enums.SmsStatus;
import com.hoiio.sdk.util.DateUtil;

public class Sms extends HoiioResponse {
	
	private static enum Params {
		SMS_STATUS, TXN_REF, TAG, DEST, DATE, SPLIT_COUNT, CURRENCY, RATE, DEBIT;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private SmsStatus smsStatus;
	private String txnRef;
	private String tag;
	private String dest;
	private Date date;
	private int splitCount;
	private Currency currency;
	private double rate;
	private double debit;
	
	/**
	 * Constructs a new {@code Sms} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 */
	public Sms(JSONObject output) throws HoiioException {
		response = output.toString();
		
		smsStatus = SmsStatus.fromString(output.getString(Params.SMS_STATUS.toString()));
		txnRef = output.getString(Params.TXN_REF.toString());
		tag = output.getString(Params.TAG.toString());
		dest = output.getString(Params.DEST.toString());
		date = DateUtil.stringToDateTime(output.getString(Params.DATE.toString()));
		splitCount = output.getInt(Params.SPLIT_COUNT.toString());
		currency = Currency.fromString(output.getString(Params.CURRENCY.toString()));
		rate = output.getDouble(Params.RATE.toString());
		debit = output.getDouble(Params.DEBIT.toString());
	}

	/**
	 * Gets the currency used for this transaction
	 * @return Currency used for this transaction
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
	 * Gets the Date/time of this transaction
	 * @return Date/time (GMT+8) of the SMS in "YYYY-MM-DD HH:MM:SS" format.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Gets the recipient number of this transaction
	 * @return The recipient number of the SMS. Phone numbers start with a "+" and country code (E.164 format), e.g. +6511111111.
	 */
	public String getDest() {
		return dest;
	}

	/**
	 * Gets the charge per sms for this transaction
	 * @return Per multipart SMS charges for this call transaction.
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * Gets the status of the SMS delivery
	 * @return Status of the SMS delivery.
	 */
	public SmsStatus getSmsStatus() {
		return smsStatus;
	}

	/**
	 * Gets the number of nultipart SMS that this message has been split into
	 * @return The number of multipart SMS that this message has been split into.
	 */
	public int getSplitCount() {
		return splitCount;
	}

	/**
	 * Gets your reference ID submmited in the initial sms/send request
	 * @return Your own reference ID submitted in the initial sms/send request. This parameter will not be present if it wasn't included in the initial request.
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Gets the unique reference ID for this transaction
	 * @return The unique reference ID for this transaction.
	 */
	public String getTxnRef() {
		return txnRef;
	}	
}
