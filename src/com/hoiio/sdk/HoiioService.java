package com.hoiio.sdk;

import java.util.Date;
import java.util.Map;

import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.services.CallService;
import com.hoiio.sdk.services.IVRService;
import com.hoiio.sdk.services.SMSService;
import com.hoiio.sdk.util.StringUtil;

public class HoiioService {
	
	/** Developer Credentials **/
	private String appId;
	private String accessToken;
	
	/** Constructor **/
	public HoiioService(String appId, String accessToken) {
		this.appId = appId;
		this.accessToken = accessToken;
	}
	
	
	/****************************************************************************************
	 * 							CALL SERVICES												*
	 ****************************************************************************************/
	/**
	 * Make 2-way callback
	 * @param dest1		(optional) The first number to call in E.164 format (e.g. +6511111111). This cannot be the same as dest2 parameter. If omitted, the call will be made to the number registered in your developer account.
	 * @param dest2		The second number to call in E.164 format (e.g. +6511111111). This cannot be the same as dest1 parameter.	
	 * @param callerId	(optional) Caller ID that dest2 will see on their incoming call. Can be either developer's registered number/hoiio number/"private"
	 * @param tag		(optional) This is a text string containing your own reference ID for this transaction
	 * @param notifyUrl	(optional) A fully-qualified HTTP/S callback URL on your web server to be notified when the call ends
	 * @return 
	 * @throws HoiioException	Any error occurs
	 */
	public Map<String, Object> call(String dest1, String dest2, String callerId, 
			String tag, String notifyUrl) throws HoiioException {
		return CallService.call(appId, accessToken, dest1, dest2, callerId, tag, notifyUrl);
	}
	
	/**
	 * Get Call Rate 
	 * @param dest1		The first number to call in E.164 format (e.g. +6511111111).
	 * @param dest2		The second number to call in E.164 format (e.g. +6511111111).
	 * @return 	
	 * @throws HoiioException	Any error occurs
	 */
	public Map<String, Object> callGetRate(String dest1, String dest2) throws HoiioException {
		return CallService.getRate(appId, accessToken, dest1, dest2);
	}
	
	/**
	 * Query call status
	 * @param txnRef	The unique reference ID for the required transaction.
	 * @return	
	 * @throws HoiioException	Any error occurs
	 */
	public Map<String, Object> callQueryStatus(String txnRef) throws HoiioException {
		return CallService.queryStatus(appId, accessToken, txnRef);
	}
	
	/**
	 * Get call history
	 * @param from		(optional) Retrieve call history made by this app starting from this date/time in "YYYY-MM-DD HH:MM:SS" (GMT+8) format. E.g. "2010-01-01 00:00:00". If omitted, call history will be retrieved from the earliest transaction.
	 * @param to		(optional) Retrieve call history made by this app before this date/time in "YYYY-MM-DD HH:MM:SS" (GMT+8) format. E.g. "2010-01-01 00:00:00". If omitted, call history will be retrieved up to the current point of time.
	 * @param page		(optional) Each request returns a maximum of 100 entries. This parameter indicates which subset of entries to return. If omitted, the first page will be retrieved.
	 * @return	 
	 * @throws HoiioException	Any error occurs
	 */
	public Map<String, Object> callGetHistory(Date from, Date to, Integer page) throws HoiioException {
		return CallService.getHistory(appId, accessToken, from, to, page);
	}
	
	/**
	 * Make conference call
	 * @param dest		A comma-seperated list of destination numbers in E.164 format to be called and placed in the conference room
	 * @param room		(optional) A text string representing the conference room ID. Valid characters are a-z, A-Z, 0-9, period (.) and underscore (_) characters. Max 32 characters.
	 * @param callerId	(optional) The Caller ID that each destination number will see on their incoming call. 
	 * @param tag		(optional) This is a text string containing your own reference ID for this transaction
	 * @param notifyUrl	(optional) A fully-qualified HTTP/S callback URL on your web server to be notified when a call ends
	 * @return
	 * @throws HoiioException	Any error occurs
	 */
	public Map<String, Object> conference(String dest, String room, String callerId,  
			String tag, String notifyUrl) throws HoiioException {
		return CallService.conference(appId, accessToken, dest, room, callerId, tag, notifyUrl);
	}
	
	/**
	 * 
	 * @param txnRef	The unique reference ID for the call you want to hangup.
	 * @return
	 * @throws HoiioException
	 */
	public Map<String, Object> hangup(String txnRef) throws HoiioException {
		return CallService.hangup(txnRef, txnRef, txnRef);
	}
	
	/****************************************************************************************
	 * 							SMS SERVICES												*
	 ****************************************************************************************/
	
	/**
	 * Send SMS
	 * @param dest		The recipient number of the SMS in E.164 format (e.g. +6511111111).
	 * @param senderName	(optional) The sender name that the recipient of your SMS will see
	 * @param msg		Contents of the SMS message.
	 * @param tag		(optional) This is a text string containing your own reference ID for this transaction
	 * @param notifyUrl	(optional) A fully-qualified HTTP/S callback URL on your web server to be notified when the SMS has been delivered
	 * @return	
	 * @throws HoiioException
	 */
	public Map<String, Object> smsSend(String dest, String senderName, 
			String msg, String tag, String notifyUrl) throws HoiioException {
		return SMSService.send(appId, accessToken, dest, senderName, msg, tag, notifyUrl);
	}
	
	/**
	 * Get SMS rate
	 * @param dest	The recipient number of the SMS in E.164 format (e.g. +6511111111).
	 * @param msg	(optional) If provided, an estimate of the number of multipart SMS and total cost of sending this message will be included in the response. Otherwise, the rate per multipart SMS will be returned.
	 * @return	
	 * @throws HoiioException
	 */
	public Map<String, Object> smsGetRate(String dest, String msg) throws HoiioException {
		return SMSService.getRate(appId, accessToken, dest, msg);
	}
	
	/**
	 * Query SMS status 
	 * @param txnRef	The unique reference ID for the required transaction.
	 * @return	
	 * @throws HoiioException
	 */
	public Map<String, Object> smsQueryStatus(String txnRef) throws HoiioException {
		return SMSService.queryStatus(appId, accessToken, txnRef);
	}
	
	/**
	 * Get SMS history
	 * @param from		(optional) Retrieve SMS history made by this app starting from this date/time in "YYYY-MM-DD HH:MM:SS" (GMT+8) format. E.g. "2010-01-01 00:00:00". If omitted, SMS history will be retrieved from the earliest transaction.
	 * @param to		(optional) Retrieve SMS history made by this app before this date/time in "YYYY-MM-DD HH:MM:SS" (GMT+8) format. E.g. "2010-01-01 00:00:00". If omitted, SMS history will be retrieved up to the current point of time.
	 * @param page		(optional) Each request returns a maximum of 100 entries. This parameter indicates which subset of entries to return. If omitted, the first page will be retrieved.
	 * @return	 
	 * @throws HoiioException
	 */
	public Map<String, Object> smsGetHistory(Date from, Date to, 
			Integer page) throws HoiioException {
		return SMSService.getHistory(appId, accessToken, from, to, page);
	}
	
	/****************************************************************************************
	 * 							IVR SERVICES												*
	 ****************************************************************************************/
	
	/**
	 * Make outgoing IVR 
	 * @param msg		(optional) The message that you want to play after the call is answered. Max 500 characters.
	 * @param dest		The destination number to call in E.164 format (e.g. +6511111111).
	 * @param callerID	(optional) The Caller ID that the destination number will see on their incoming call
	 * @param tag		(optional) This is a text string containing your own reference ID for this transaction
	 * @param notifyUrl (optional) A fully-qualified HTTP/S URL on your web server to be notified when this action has completed execution
	 * @return
	 * @throws HoiioException
	 */
	public Map<String, Object> ivrDial(String msg, String dest, String callerID,
			String tag, String notifyUrl) throws HoiioException {
		return IVRService.dial(appId, accessToken, dest, msg, callerID, tag, notifyUrl);
	}
	
	/**
	 * IVR play the message
	 * @param session	The unique session ID for this particular call. (returned in one of the start blocks: dial or answer)
	 * @param msg		(optional) This is the message that you want to play to the user. Max 500 characters.
	 * @param tag		(optional) This is a text string containing your own reference ID for this transaction
	 * @param notifyUrl	(optional) A fully-qualified HTTP/S URL on your web server to be notified when this action has completed execution
	 * @return
	 * @throws HoiioException
	 */
	public Map<String, Object> ivrPlay(String session, String msg, String tag,
			String notifyUrl) throws HoiioException {
		return IVRService.play(appId, accessToken, session, msg, tag, notifyUrl);
	}
	
	/**
	 * IVR Gather
	 * @param session	The unique session ID for this particular call. (returned in one of the start blocks: dial or answer)
	 * @param msg		(optional) This is the message that you want to play to the user. Max 500 characters.
	 * @param maxDigits	(optional) the maximum digits people can enter
	 * @param timeout 	(optional) the time that user need to input the response
	 * @param attempts	(optional) number of user attempts
	 * @param tag		(optional) This is a text string containing your own reference ID for this transaction
	 * @param notifyUrl	A fully-qualified HTTP/S URL on your web server to be notified when this action has completed execution
	 * @return
	 * @throws HoiioException
	 */
	public Map<String, Object> ivrGather(String session, String msg, Integer maxDigits,
			Integer timeout, Integer attempts, String tag,
			String notifyUrl) throws HoiioException {
		return IVRService.gather(appId, accessToken, session, msg, maxDigits, timeout, attempts, tag, notifyUrl);
	}
	
	/**
	 * IVR Record
	 * @param session	The unique session ID for this particular call. (returned in one of the start blocks: dial or answer)
	 * @param msg		(optional) This is the message that you want to play to the user. Max 500 characters.
	 * @param maxDuration	(optional) maximum time of the record
	 * @param tag		(optional) This is a text string containing your own reference ID for this transaction
	 * @param notifyUrl	A fully-qualified HTTP/S URL on your web server to be notified when this action has completed execution
	 * @return
	 * @throws HoiioException
	 */
	public Map<String, Object> ivrRecord(String session, String msg, String maxDuration, 
			String tag, String notifyUrl) throws HoiioException {
		return IVRService.record(appId, accessToken, session, msg, maxDuration, tag, notifyUrl);
	}
	
	/**
	 * IVR Transfer
	 * @param session	The unique session ID for this particular call. (returned in one of the start blocks: dial or answer)
	 * @param msg		(optional) This is the message that you want to play to the user. Max 500 characters.
	 * @param dest		the destination to transfer the call to
	 * @param callerID	(optional) the callerID of the call
	 * @param tag		(optional) This is a text string containing your own reference ID for this transaction
	 * @param notifyUrl (optional) A fully-qualified HTTP/S URL on your web server to be notified when this action has completed execution
	 * @return
	 * @throws HoiioException
	 */
	public Map<String, Object> ivrTransfer(String session, String msg, String dest, 
			String callerID, String tag, String notifyUrl) throws HoiioException {
		return IVRService.transfer(appId, accessToken, session, msg, dest, callerID, tag, notifyUrl);
	}
	
	/**
	 * IVR Hangup
	 * @param session	The unique session ID for this particular call. (returned in one of the start blocks: dial or answer)
	 * @param msg		(optional) This is the message that you want to play to the user. Max 500 characters.
	 * @param tag		(optional) This is a text string containing your own reference ID for this transaction
	 * @param notifyUrl	(optional) A fully-qualified HTTP/S URL on your web server to be notified when this action has completed execution
	 * @return
	 * @throws HoiioException
	 */
	public Map<String, Object> ivrHangup(String session, String msg, String tag,
			String notifyUrl) throws HoiioException {
		return IVRService.hangup(appId, accessToken, session, msg, tag, notifyUrl);
	}
	
	/**
	 * Parse notify for call, sms and ivr
	 * @param notify	
	 * @return		
	 */
	public Map<String, String> parseNotify(String notify) {
		return StringUtil.parseNvp(notify);
	}
}
