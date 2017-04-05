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

import net.sf.json.JSONObject;

import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.objects.HoiioRequest;
import com.hoiio.sdk.objects.enums.TransferOnFailture;
import com.hoiio.sdk.objects.ivr.Dial;
import com.hoiio.sdk.objects.ivr.Gather;
import com.hoiio.sdk.objects.ivr.Hangup;
import com.hoiio.sdk.objects.ivr.Monitor;
import com.hoiio.sdk.objects.ivr.Play;
import com.hoiio.sdk.objects.ivr.Record;
import com.hoiio.sdk.objects.ivr.Transfer;

/**
 * Interactive voice responses (IVR) menus allows you to interact with your users through a voice call. You will be able to play a series of voice dialogs and your users will be able to respond via their telephone keypad. A common use case is a Customer Helpline where you might hear:<br/>
 * <i>"For sales enquiry, please press 1. For technical support, please press 2. To speak to our customer service officers, please press 0."</i> <br/>
 * Hoiio's IVR API allows developers to easily create IVR menus like the above using RESTful APIs. This can be achieved without the need for technical telecommunication knowledge. Coupled with our Hoiio Numbers and Text-to-Speech (TTS) technology, you can get started instantly without hassle.<br/>
 * <br/>
 * For more info please refer to: <a href="http://developer.hoiio.com/docs/ivr.html">http://developer.hoiio.com/docs/ivr.html</a>
 */
public class IvrService extends HttpService {
	
	private static enum Params {
		DEST, MSG, CALLER_ID, TAG, NOTIFY_URL, MONITOR_NOTIFY_URL, SESSION, MAX_DIGITS,
		TIMEOUT, ATTEMPTS, MAX_DURATION, ON_FAILURE;

		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private static final String URL_IVR_DIAL = "ivr/start/dial";
	private static final String URL_IVR_PLAY = "ivr/middle/play";
	private static final String URL_IVR_GATHER = "ivr/middle/gather";
	private static final String URL_IVR_RECORD = "ivr/middle/record";
	private static final String URL_IVR_MONITOR = "ivr/middle/monitor";
	private static final String URL_IVR_TRANSFER = "ivr/end/transfer";
	private static final String URL_IVR_HANGUP = "ivr/end/hangup";
	
	/**
	 * Constructs the service to make all IVR requests
	 * @param appId AppID of the developer
	 * @param accessToken AccessToken of the developer
	 */
	public IvrService(String appId, String accessToken) {
		this.appId = appId;
		this.accessToken = accessToken;
	}

	/**
	 * Makes a dial-out to a destination number
	 * @param dest The destination number to call. Phone numbers should start with a "+" and country code (E.164 format), e.g. +6511111111.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Dial dial(String dest) throws HoiioException {
		return dial(dest, null, null, null, null);
	}
	
	/**
	 * Makes a dial-out to a destination number
	 * @param dest The destination number to call. Phone numbers should start with a "+" and country code (E.164 format), e.g. +6511111111.
	 * @param msg (optional) The message that you want to play after the call is answered. Max 500 characters.
	 * @param callerID (optional) This is the Caller ID that the destination number will see on their incoming call.
	 * @param tag (optional) This is a text string containing your own reference ID for this transaction. This value will be included in the response for Notification for your reference. Max 256 characters.
	 * @param notifyUrl (optional) A fully-qualified HTTP/S URL on your web server to be notified when this action has completed execution. If omitted, the call will hang up after msg is played, and the notification will not be sent to you.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Dial dial(String dest, String msg, String callerID, String tag, String notifyUrl) throws HoiioException {
		
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.DEST.toString(), dest, true);
		map.put(Params.MSG.toString(), msg, false);
		map.put(Params.CALLER_ID.toString(), callerID, false);
		map.put(Params.TAG.toString(), tag, false);
		map.put(Params.NOTIFY_URL.toString(), notifyUrl, false);
		
		return new Dial(doHttpPost(URL_IVR_DIAL, map));
	}
	
	/**
	 * Gets user input via the telephone's keypad in IVR calls
	 * @param session The unique session ID for this particular call.
	 * @param notifyUrl A fully-qualified HTTP/S URL on your web server to be notified when this action has completed execution.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Gather gather(String session, String notifyUrl) throws HoiioException {
		return gather(session, notifyUrl, null, null, null, null, null);
	}
	
	/**
	 * Gets user input via the telephone's keypad in IVR calls
	 * @param session The unique session ID for this particular call.
	 * @param notifyUrl A fully-qualified HTTP/S URL on your web server to be notified when this action has completed execution.
	 * @param msg (optional) This is the message that you want to play to instruct the user to enter their input. Max 500 characters.
	 * @param maxDigits (optional) The maximum number of digits to gather. Once the user has input the maximum number of digits, the input will end. Users can also press # to end the input at any time.
	 * @param timeout (optional) The time (in seconds) that the user has to input his response. When timeout is reached, whatever the user has inputted up to that point will be the user's response. 
	 * @param attempts (optional) The number of attempts that the user has to input his response.
	 * @param tag (optional) This is a text string containing your own reference ID for this transaction. This value will be included in the response for Notification for your reference. Max 256 characters.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Gather gather(String session, String notifyUrl, String msg, 
			Integer maxDigits, Integer timeout, Integer attempts, String tag) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.SESSION.toString(), session, true);
		map.put(Params.NOTIFY_URL.toString(), notifyUrl, true);
		map.put(Params.MSG.toString(), msg, false);
		map.put(Params.TAG.toString(), tag, false);
		map.put(Params.MAX_DIGITS.toString(), maxDigits, false);
		map.put(Params.TIMEOUT.toString(), timeout, false);
		map.put(Params.ATTEMPTS.toString(), attempts, false);
		
		return new Gather(doHttpPost(URL_IVR_GATHER, map));
	}	
	
	/**
	 * Parses the JSON request to get the IVR session
	 * @param request the POST request sent from Hoiio to developer's notify URL in JSON format
	 * @return The IVR session for this particular call 
	 */
	public String getSession(String request) {
		return JSONObject.fromObject(request).getString(Params.SESSION.toString());
	}
	
	/**
	 * Hangs up the current call
	 * @param session The unique session ID for this particular call.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Hangup hangup(String session) throws HoiioException {
		return hangup(session, null, null, null);
	}
	
	/**
	 * Hangs up the current call
	 * @param session The unique session ID for this particular call.
	 * @param msg (optional) This message will be played to the user before the call is hangup. Max 500 characters.
	 * @param tag (optional) This is a text string containing your own reference ID for this transaction. This value will be included in the response for Notification for your reference. Max 256 characters.
	 * @param notifyUrl (optional) A fully-qualified HTTP/S URL on your web server to be notified when this action has completed execution. If omitted, the notification will not be sent to you.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Hangup hangup(String session, String msg, 
			String tag, String notifyUrl) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.SESSION.toString(), session, true);
		map.put(Params.NOTIFY_URL.toString(), notifyUrl, false);
		map.put(Params.MSG.toString(), msg, false);
		map.put(Params.TAG.toString(), tag, false);
		
		return new Hangup(doHttpPost(URL_IVR_HANGUP, map));
	}

	/**
	 * Records the call conversation, including any voice messages
	 * @param session The unique session ID for this particular call.
	 * @param notifyUrl A fully-qualified HTTP/S URL on your web server to be notified when this action has completed execution
	 * @param monitorNotifyUrl A fully-qualified HTTP/S URL on your web server to be notified when recording process has completed execution
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Monitor monitor(String session, String notifyUrl, String monitorNotifyUrl) throws HoiioException {
		return monitor(session, notifyUrl, monitorNotifyUrl,null, null);
	}
	
	/**
	 * Records the call conversation, including any voice messages
	 * @param session The unique session ID for this particular call.
	 * @param notifyUrl A fully-qualified HTTP/S URL on your web server to be notified when this action has completed execution
	 * @param monitorNotifyUrl A fully-qualified HTTP/S URL on your web server to be notified when recording process has completed execution
	 * @param msg (optional) This is the message that you want to play to inform the user before the start of the recording. Max 500 characters.
	 * @param tag (optional) This is a text string containing your own reference ID for this transaction. This value will be included in the response for Notification for your reference. Max 256 characters.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Monitor monitor(String session, String notifyUrl, String monitorNotifyUrl, String msg,
			String tag) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.SESSION.toString(), session, true);
		map.put(Params.NOTIFY_URL.toString(), notifyUrl, true);
		map.put(Params.MONITOR_NOTIFY_URL.toString(), monitorNotifyUrl, false);
		map.put(Params.MSG.toString(), msg, false);
		map.put(Params.TAG.toString(), tag, false);
		
		return new Monitor(doHttpPost(URL_IVR_MONITOR, map));
	}	
	
	/**
	 * Plays a voice message
	 * @param session The unique session ID for this particular call.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Play play(String session) throws HoiioException {
		return play(session, null, null, null);
	}
	
	/**
	 * Plays a voice message
	 * @param session The unique session ID for this particular call.
	 * @param msg (optional) This is the message that you want to play to the user. Max 500 characters.
	 * @param tag (optional) This is a text string containing your own reference ID for this transaction. This value will be included in the response for Notification for your reference. Max 256 characters.
	 * @param notifyUrl (optional) A fully-qualified HTTP/S URL on your web server to be notified when this action has completed execution. If omitted, the call will hang up after msg is played, and the notification will not be sent to you.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Play play(String session, String msg, String tag, String notifyUrl) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.SESSION.toString(), session, true);
		map.put(Params.MSG.toString(), msg, false);
		map.put(Params.TAG.toString(), tag, false);
		map.put(Params.NOTIFY_URL.toString(), notifyUrl, false);
		
		return new Play(doHttpPost(URL_IVR_PLAY, map));
	}

	/**
	 * Records voice messages from the user over the phone
	 * @param session The unique session ID for this particular call.
	 * @param notifyUrl A fully-qualified HTTP/S URL on your web server to be notified when this action has completed execution
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Record record(String session, String notifyUrl) throws HoiioException {
		return record(session, notifyUrl, null, null, null);
	}
	
	/**
	 * Records voice messages from the user over the phone
	 * @param session The unique session ID for this particular call.
	 * @param notifyUrl A fully-qualified HTTP/S URL on your web server to be notified when this action has completed execution
	 * @param msg (optional) This is the message that you want to play to inform the user before the start of the recording. Max 500 characters.
	 * @param maxDuration (optional) The maximum duration in seconds of the recording. Once the recording limit is reached, the call will automatically move on to the next IVR block.
	 * @param tag (optional) This is a text string containing your own reference ID for this transaction. This value will be included in the response for Notification for your reference. Max 256 characters.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Record record(String session, String notifyUrl, String msg, 
			Integer maxDuration, String tag) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.SESSION.toString(), session, true);
		map.put(Params.NOTIFY_URL.toString(), notifyUrl, true);
		map.put(Params.MSG.toString(), msg, false);
		map.put(Params.TAG.toString(), tag, false);
		map.put(Params.MAX_DURATION.toString(), maxDuration, false);
		
		return new Record(doHttpPost(URL_IVR_RECORD, map));
	}	
	
	/**
	 * Transfers the current call to another destination number anywhere in the world or a voice conference room
	 * @param session The unique session ID for this particular call.
	 * @param dest The destination to transfer the call to. This can be either a E.164 format destination number (start with a "+" and country code, e.g. +6511111111) or a conference room ID.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Transfer transfer(String session, String dest) throws HoiioException {
		return transfer(session, dest, null, null, null, null, null);
	}
	
	/**
	 * Transfers the current call to another destination number anywhere in the world or a voice conference room
	 * @param session The unique session ID for this particular call.
	 * @param dest The destination to transfer the call to. This can be either a E.164 format destination number (start with a "+" and country code, e.g. +6511111111) or a conference room ID.
	 * @param msg (optional) This is the message that you want to play to the user before the call is transferred. Max 500 characters.
	 * @param callerID (optional) This is the Caller ID that the destination number will see on their incoming call.
	 * @param onFailure (optional) Action to perform if the transfer fails: hangup, continue
	 * @param tag (optional) This is a text string containing your own reference ID for this transaction. This value will be included in the response for Notification for your reference. Max 256 characters.
	 * @param notifyUrl (optional) A fully-qualified HTTP/S URL on your web server to be notified when this action has completed execution. If omitted, the notification will not be sent to you.
	 * @return Object containing all the responses from the server
	 * @throws HoiioException
	 */
	public Transfer transfer(String session, String dest, String msg, String callerID,  
			TransferOnFailture onFailure, String tag, String notifyUrl) throws HoiioException {
		HoiioRequest map = new HoiioRequest();
		
		map.put(Params.SESSION.toString(), session, true);
		map.put(Params.DEST.toString(), dest, true);
		map.put(Params.NOTIFY_URL.toString(), notifyUrl, false);
		map.put(Params.MSG.toString(), msg, false);
		map.put(Params.TAG.toString(), tag, false);
		map.put(Params.CALLER_ID.toString(), callerID, false);
		map.put(Params.ON_FAILURE.toString(), onFailure.toString(), false);
		
		return new Transfer(doHttpPost(URL_IVR_TRANSFER, map));
	}	
}
