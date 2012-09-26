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
import com.hoiio.sdk.objects.voice.Call;
import com.hoiio.sdk.objects.voice.CallHangUp;
import com.hoiio.sdk.objects.voice.CallHistory;
import com.hoiio.sdk.objects.voice.CallRate;
import com.hoiio.sdk.objects.voice.CallTxn;
import com.hoiio.sdk.objects.voice.Conference;
import com.hoiio.sdk.util.DateUtil;
import com.hoiio.sdk.util.StringUtil;


/**  
 * The Voice API provides developers access to telephony  
 * services in more than 200 countries around the world. 
 * You will be able to initiate phone calls to any mobile phones, 
 * land lines or create call conferences just by making an API request to our servers.<br/>
 * <br/>
 * For more info please refer to: <a href="http://developer.hoiio.com/docs/voice.html">http://developer.hoiio.com/docs/voice.html</a>
 */
public class VoiceService extends HttpService {
	
	private static enum Params {
		DEST1, DEST2, CALLER_ID, TAG, NOTIFY_URL, FROM, TO, PAGE, TXN_REF, DEST, ROOM;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private static final String URL_VOICE_CALL = "voice/call";
	private static final String URL_VOICE_GET_RATE = "voice/get_rate";
	private static final String URL_VOICE_GET_HISTORY = "voice/get_history";
	private static final String URL_VOICE_QUERY_STATUS = "voice/query_status";
	private static final String URL_VOICE_CONFERENCE = "voice/conference";
	private static final String URL_VOICE_HANGUP = "voice/hangup";
		
	/**
	 * Constructs the service to make all Voice requests
	 * @param appId AppID of the developer
	 * @param accessToken AccessToken of the developer
	 */
	public VoiceService(String appId, String accessToken) {
		this.appId = appId;
		this.accessToken = accessToken;
	}
	
	/**
	 * Dials out to a list of destination numbers and place them together in a conference call.
	 * @param dests List of destination numbers in E.164 format (start with a "+" and country code) to be called and placed in the conference room. A maximum of 8 numbers are allowed.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Conference createConference(List<String> dests) throws HoiioException {
		return createConference(StringUtil.convertListToString(dests));
	}
	
	/**
	 * Dials out to a list of destination numbers and place them together in a conference call.
	 * @param dests List of destination numbers in E.164 format (start with a "+" and country code) to be called and placed in the conference room. A maximum of 8 numbers are allowed.
	 * @param room (optional) A text string representing the conference room ID. Valid characters are a-z, A-Z, 0-9, period (.) and underscore (_) characters. Max 32 characters.
	 * @param callerId (optional) This is the Caller ID that each destination number will see on their incoming call. 
	 * @param tag (optional) This is a text string containing your own reference ID for this transaction. This value will be included in the response for Notification, FetchVoice and FetchHistory for your reference. Max 256 characters.
	 * @param notifyUrl (optional) A fully-qualified HTTP/S callback URL on your web server to be notified when a call ends
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Conference createConference(List<String> dests, String room, String callerId, String tag, 
			String notifyUrl) throws HoiioException {
		
		return createConference(StringUtil.convertListToString(dests),
				room, callerId, tag, notifyUrl);
	}
	
	/**
	 * Dials out to a list of destination numbers and place them together in a conference call.
	 * @param dest A comma-seperated list of destination numbers in E.164 format (start with a "+" and country code) to be called and placed in the conference room. A maximum of 8 numbers are allowed. E.g. +6511111111,+6522222222,+6533333333
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Conference createConference(String dest) throws HoiioException {
		return createConference(dest, null, null, null, null);
	}

	/**
	 * Dials out to a list of destination numbers and place them together in a conference call.
	 * @param dest A comma-seperated list of destination numbers in E.164 format (start with a "+" and country code) to be called and placed in the conference room. A maximum of 8 numbers are allowed. E.g. +6511111111,+6522222222,+6533333333
	 * @param room (optional) A text string representing the conference room ID. Valid characters are a-z, A-Z, 0-9, period (.) and underscore (_) characters. Max 32 characters.
	 * @param callerId (optional) This is the Caller ID that each destination number will see on their incoming call. 
	 * @param tag (optional) This is a text string containing your own reference ID for this transaction. This value will be included in the response for Notification, FetchVoice and FetchHistory for your reference. Max 256 characters.
	 * @param notifyUrl (optional) A fully-qualified HTTP/S callback URL on your web server to be notified when a call ends
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Conference createConference(String dest, String room, String callerId, String tag, 
			String notifyUrl) throws HoiioException {
		
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.DEST.toString(), dest, true);
		map.put(Params.ROOM.toString(), room, false);
		map.put(Params.CALLER_ID.toString(), callerId, false);
		map.put(Params.TAG.toString(), tag, false);
		map.put(Params.NOTIFY_URL.toString(), notifyUrl, false);
				
		return new Conference(doHttpPost(URL_VOICE_CONFERENCE, map));
	}

	/**
	 * Fetches the current status of a call made previously
	 * @param txnRef The unique reference ID for the required transaction.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Call fetchCall(String txnRef) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.TXN_REF.toString(), txnRef, true);
			
		return new Call(doHttpPost(URL_VOICE_QUERY_STATUS, map));		
	}
	
	/**
	 * Retrieves the history of calls made by this application.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public CallHistory fetchHistory() throws HoiioException {
		return fetchHistory(null, null, null);
	}

	/**
	 * Retrieves the history of calls made by this application.
	 * @param from (optional) Retrieve call history made by this app starting from this date/time in "YYYY-MM-DD HH:MM:SS" (GMT+8) format. E.g. "2010-01-01 00:00:00". If omitted, call history will be retrieved from the earliest transaction.
	 * @param to (optional) Retrieve call history made by this app before this date/time in "YYYY-MM-DD HH:MM:SS" (GMT+8) format. E.g. "2010-01-01 00:00:00". If omitted, call history will be retrieved up to the current point of time.
	 * @param page (optional) Each request returns a maximum of 100 entries. This parameter indicates which subset of entries to return.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public CallHistory fetchHistory(Date from, Date to, Integer page) throws HoiioException {
		
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.FROM.toString(), DateUtil.dateToString(from), false);
		map.put(Params.TO.toString(), DateUtil.dateToString(to), false);
		map.put(Params.PAGE.toString(), page.toString(), false);
		
		return new CallHistory(doHttpPost(URL_VOICE_GET_HISTORY, map));
	}

	/**
	 * Retrieves the billable rate that will be charged for calls made
	 * @param dest1 The first number to call. Phone numbers should start with a "+" and country code (E.164 format), e.g. +6511111111.
	 * @param dest2 The second number to call. Phone numbers should start with a "+" and country code (E.164 format), e.g. +6511111111.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public CallRate fetchRate(String dest1, String dest2) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.DEST1.toString(), dest1, true);
		map.put(Params.DEST2.toString(), dest2, true);
					
		return new CallRate(doHttpPost(URL_VOICE_GET_RATE, map));
		
	}

	/**
	 * Hangs up a call that is currently in progress
	 * @param txnRef The unique reference ID for the call you want to hangup.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public CallHangUp hangup(String txnRef) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.TXN_REF.toString(), txnRef, true);
				
		return new CallHangUp(doHttpPost(URL_VOICE_HANGUP, map));
	}
	
	/**
	 * Dials out to 2 destination numbers and connect them together in a phone conversation.
	 * @param dest2 The second number to call. Phone numbers should start with a "+" and country code (E.164 format), e.g. +6511111111. This cannot be the same as your mobile number.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public CallTxn makeCall(String dest2) throws HoiioException {
		return makeCall(null, dest2, null, null, null);
	}

	/**
	 * Dials out to 2 destination numbers and connect them together in a phone conversation.
	 * @param dest1 (optional) The first number to call. Phone numbers should start with a "+" and country code (E.164 format), e.g. +6511111111. This cannot be the same as dest2 parameter. 
	 * @param dest2 The second number to call. Phone numbers should start with a "+" and country code (E.164 format), e.g. +6511111111. This cannot be the same as dest1 parameter.
	 * @param callerId (optional) This is the Caller ID that dest2 will see on their incoming call
	 * @param tag (optional) This is a text string containing your own reference ID for this transaction. This value will be included in the response for Notification, FetchVoice and FetchHistory for your reference. Max 256 characters.
	 * @param notifyUrl (optional) A fully-qualified HTTP/S callback URL on your web server to be notified when the call ends.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public CallTxn makeCall(String dest1, String dest2,
			String callerId, String tag, String notifyUrl) throws HoiioException {
		
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.DEST1.toString(), dest1, false);
		map.put(Params.DEST2.toString(), dest2, true);
		map.put(Params.CALLER_ID.toString(), callerId, false);
		map.put(Params.TAG.toString(), tag, false);
		map.put(Params.NOTIFY_URL.toString(), notifyUrl, false);
		
		return new CallTxn(doHttpPost(URL_VOICE_CALL, map));
	}
}
