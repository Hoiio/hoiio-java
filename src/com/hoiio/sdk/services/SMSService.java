package com.hoiio.sdk.services;

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
import java.util.List;

import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.objects.HoiioRequest;
import com.hoiio.sdk.objects.sms.BulkSmsTxn;
import com.hoiio.sdk.objects.sms.Sms;
import com.hoiio.sdk.objects.sms.SmsHistory;
import com.hoiio.sdk.objects.sms.SmsRate;
import com.hoiio.sdk.objects.sms.SmsTxn;
import com.hoiio.sdk.util.DateUtil;
import com.hoiio.sdk.util.StringUtil;

/**
 * The SMS API allows developers to send/receive SMS to/from more than 200 countries around the world at a very competitive rate.<br/>
 * <br/>
 * For more info please refer to: <a href="http://developer.hoiio.com/docs/sms.html">http://developer.hoiio.com/docs/sms.html</a>
 */
public class SmsService extends HttpService {
	
	private static enum Params {
		
		DEST, SENDER_NAME, MSG, TAG, NOTIFY_URL, FROM, TO, PAGE, TXN_REF, INCOMING;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private static final String URL_SMS_SEND = "sms/send";
	private static final String URL_SMS_BULK_SEND = "sms/bulk_send";
	private static final String URL_SMS_GET_RATE = "sms/get_rate";
	private static final String URL_SMS_GET_HISTORY = "sms/get_history";
	private static final String URL_SMS_QUERY_STATUS = "sms/query_status";

	/**
	 * Constructs the service to make all Sms requests
	 * @param appId AppID of the developer
	 * @param accessToken AccessToken of the developer
	 */
	public SmsService(String appId, String accessToken) {
		this.appId = appId;
		this.accessToken = accessToken;
	}
	
	/**
	 * Retrieves the history of SMS sent or received by this application.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public SmsHistory fetchHistory() throws HoiioException {
		return fetchHistory(null, null, null);
	}

	/**
	 * Retrieves the history of SMS sent or received by this application.
	 * @param from (optional) Retrieve SMS history made by this app starting from this date/time in "YYYY-MM-DD HH:MM:SS" (GMT+8) format. E.g. "2010-01-01 00:00:00". If omitted, SMS history will be retrieved from the earliest transaction.
	 * @param to (optional) Retrieve SMS history made by this app before this date/time in "YYYY-MM-DD HH:MM:SS" (GMT+8) format. E.g. "2010-01-01 00:00:00". If omitted, SMS history will be retrieved up to the current point of time.
	 * @param page (optional) Each request returns a maximum of 100 entries. This parameter indicates which subset of entries to return. If omitted, the first page will be returned.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public SmsHistory fetchHistory(Date from, Date to, Integer page) throws HoiioException {
		
		HoiioRequest map = new HoiioRequest();
		
		if (from != null) {
			map.put(Params.FROM.toString(), DateUtil.dateToString(from), false);
		}
		
		if (to != null) {
			map.put(Params.TO.toString(), DateUtil.dateToString(to), false);
		}
		
		if (page != null) {
			map.put(Params.PAGE.toString(), page.toString(), false);
		}
				
		return new SmsHistory(doHttpPost(URL_SMS_GET_HISTORY, map));
	}
	
	/**
	 * Retrieves the billable rate that will be charged for each multipart SMS message received
	 * @param incoming Your Hoiio number to receive incoming SMS.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public SmsRate fetchIncomingRate(String incoming) throws HoiioException {
		HoiioRequest map = new HoiioRequest();

		map.put(Params.INCOMING.toString(), incoming, true);
			
		return new SmsRate(doHttpPost(URL_SMS_GET_RATE, map));		
	}
	
	/**
	 * Retrieves the billable rate that will be charged for each multipart SMS message sent
	 * @param dest The recipient number of the SMS. Phone numbers should start with a "+" and country code (E.164 format), e.g. +6511111111. 
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public SmsRate fetchOutgoingRate(String dest) throws HoiioException {
		return fetchOutgoingRate(dest, null);
	}

	/**
	 * Retrieves the billable rate that will be charged for each multipart SMS message sent
	 * @param dest The recipient number of the SMS. Phone numbers should start with a "+" and country code (E.164 format), e.g. +6511111111.
	 * @param msg (optional)  If provided, an estimate of the number of multipart SMS and total cost of sending this message will be included in the response
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public SmsRate fetchOutgoingRate(String dest, String msg) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
				
		map.put(Params.DEST.toString(), dest, true);
		map.put(Params.MSG.toString(), msg, false);
			
		return new SmsRate(doHttpPost(URL_SMS_GET_RATE, map));		
	}

	/**
	 * Fetches the current status of a SMS sent previously
	 * @param txnRef The unique reference ID for the required transaction.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Sms fetchSms(String txnRef) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.TXN_REF.toString(), txnRef);
			
		return new Sms(doHttpPost(URL_SMS_QUERY_STATUS, map));		
	}	
	
	/**
	 * Sends SMS to mobile numbers in over 200 countries
	 * @param dest The recipient number of the SMS. Phone numbers should start with a "+" and country code (E.164 format), e.g. +6511111111.
	 * @param msg Contents of the SMS message.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public SmsTxn send(String dest, String msg) throws HoiioException {
		return send(dest, msg, null, null, null);
	}

	/**
	 * Sends SMS to mobile numbers in over 200 countries
	 * @param dest The recipient number of the SMS. Phone numbers should start with a "+" and country code (E.164 format), e.g. +6511111111.
	 * @param msg Contents of the SMS message.
	 * @param senderName (optional) This is the sender name that the recipient of your SMS will see. 
	 * @param tag (optional) This is a text string containing your own reference ID for this transaction. This value will be included in the response for Notification, FetchSms and FetchHistory for your reference. Max 256 characters.
	 * @param notifyUrl (optional) A fully-qualified HTTP/S callback URL on your web server to be notified when the SMS has been delivered
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public SmsTxn send(String dest, String msg, String senderName, String tag, String notifyUrl) throws HoiioException {
		
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.DEST.toString(), dest, true); 
		map.put(Params.MSG.toString(), msg, true);
		map.put(Params.SENDER_NAME.toString(), senderName, false);
		map.put(Params.TAG.toString(), tag, false);
		map.put(Params.NOTIFY_URL.toString(), notifyUrl, false);
		
		return new SmsTxn(doHttpPost(URL_SMS_SEND, map));
	}
	
	/**
	 * Sends SMS in bulk (up to 1000 messages) to any mobile numbers in more than 200 countries
	 * @param dests The list of recipient numbers of the SMS, separated by comma, up to a maximum of 1000 numbers. Phone numbers should start with a "+" and country code (E.164 format) e.g. +6511111111,+6522222222,+6533333333.
	 * @param msg Contents of the SMS message.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public BulkSmsTxn sendBulk(List<String> dests, String msg) throws HoiioException {
		return sendBulk(StringUtil.convertListToString(dests), msg);
	}
	
	/**
	 * Sends SMS in bulk (up to 1000 messages) to any mobile numbers in more than 200 countries
	 * @param dests The list of recipient numbers of the SMS, separated by comma, up to a maximum of 1000 numbers. Phone numbers should start with a "+" and country code (E.164 format) e.g. +6511111111,+6522222222,+6533333333.
	 * @param msg Contents of the SMS message.
	 * @param senderName (optional) This is the sender name that the recipient of your SMS will see
	 * @param tag (optional) This is a text string containing your own reference ID for this transaction. This value will be included in the response for Notification, FetchSms and FetchHistory for your reference. Max 256 characters.
	 * @param notifyUrl (optional) A fully-qualified HTTP/S callback URL on your web server to be notified when the SMS has been delivered
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public BulkSmsTxn sendBulk(List<String> dests, String msg, String senderName,
			String tag, String notifyUrl) throws HoiioException {
		return sendBulk(StringUtil.convertListToString(dests), msg, senderName, tag, notifyUrl);
	}
	
	/**
	 * Sends SMS in bulk (up to 1000 messages) to any mobile numbers in more than 200 countries
	 * @param dest The list of recipient numbers of the SMS, separated by comma, up to a maximum of 1000 numbers. Phone numbers should start with a "+" and country code (E.164 format) e.g. +6511111111,+6522222222,+6533333333.
	 * @param msg Contents of the SMS message.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public BulkSmsTxn sendBulk(String dest, String msg) throws HoiioException {
		return sendBulk(dest, msg, null, null, null);
	}

	/**
	 * Sends SMS in bulk (up to 1000 messages) to any mobile numbers in more than 200 countries
	 * @param dest The list of recipient numbers of the SMS, separated by comma, up to a maximum of 1000 numbers. Phone numbers should start with a "+" and country code (E.164 format) e.g. +6511111111,+6522222222,+6533333333.
	 * @param msg Contents of the SMS message.
	 * @param senderName (optional) This is the sender name that the recipient of your SMS will see
	 * @param tag (optional) This is a text string containing your own reference ID for this transaction. This value will be included in the response for Notification, FetchSms and FetchHistory for your reference. Max 256 characters.
	 * @param notifyUrl (optional) A fully-qualified HTTP/S callback URL on your web server to be notified when the SMS has been delivered
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public BulkSmsTxn sendBulk(String dest, String msg, String senderName, 
			String tag, String notifyUrl) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.DEST.toString(), dest, true); 
		map.put(Params.MSG.toString(), msg, true);
		map.put(Params.SENDER_NAME.toString(), senderName, false);
		map.put(Params.TAG.toString(), tag, false);
		map.put(Params.NOTIFY_URL.toString(), notifyUrl, false);
		
		return new BulkSmsTxn(doHttpPost(URL_SMS_BULK_SEND, map));
	}
}