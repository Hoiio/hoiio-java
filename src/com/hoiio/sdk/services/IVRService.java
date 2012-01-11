package com.hoiio.sdk.services;

import java.util.HashMap;
import java.util.Map;

import com.hoiio.sdk.exception.HoiioException;

public class IVRService extends HttpService {
	
	private static final String PARAM_DEST = "dest";
	private static final String PARAM_MSG = "msg";
	private static final String PARAM_CALLER_ID = "caller_id";
	private static final String PARAM_TAG = "tag";
	private static final String PARAM_NOTIFY_URL = "notify_url";
	private static final String PARAM_SESSION = "session";
	private static final String PARAM_MAX_DIGITS = "max_digits";
	private static final String PARAM_TIMEOUT = "timeout";
	private static final String PARAM_ATTEMPTS = "attempts";	
	private static final String PARAM_MAX_DURATION = "max_duration";
	
	private static final String URL_IVR_DIAL = "ivr/start/dial";
	private static final String URL_IVR_PLAY = "ivr/middle/play";
	private static final String URL_IVR_GATHER = "ivr/middle/gather";
	private static final String URL_IVR_RECORD = "ivr/middle/record";
	private static final String URL_IVR_TRANSFER = "ivr/end/transfer";
	private static final String URL_IVR_HANGUP = "ivr/end/hangup";
	
	public static Map<String, Object> dial(String appId, String accessToken, String dest,  
			String msg, String callerID, String tag, String notifyUrl) throws HoiioException {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.putAll(initParam(appId, accessToken));
		
		map.put(PARAM_DEST, dest);
		map.put(PARAM_MSG, msg);
		map.put(PARAM_CALLER_ID, callerID);
		map.put(PARAM_TAG, tag);
		map.put(PARAM_NOTIFY_URL, notifyUrl);
		
		return doHttpPost(URL_IVR_DIAL, map);
	}
	
	public static Map<String, Object> play(String appId, String accessToken, String session,  
			String msg, String tag, String notifyUrl) throws HoiioException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.putAll(initParam(appId, accessToken));
		
		map.put(PARAM_SESSION, session);
		map.put(PARAM_MSG, msg);
		map.put(PARAM_TAG, tag);
		map.put(PARAM_NOTIFY_URL, notifyUrl);
		
		return doHttpPost(URL_IVR_PLAY, map);
	}
	
	public static Map<String, Object> gather(String appId, String accessToken, String session,
			String msg, Integer maxDigits, Integer timeout, Integer attempts, String tag, 
			String notifyUrl) throws HoiioException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.putAll(initParam(appId, accessToken));
		
		map.put(PARAM_SESSION, session);
		map.put(PARAM_NOTIFY_URL, notifyUrl);
		map.put(PARAM_MSG, msg);
		map.put(PARAM_TAG, tag);
		map.put(PARAM_MAX_DIGITS, maxDigits == null ? null : maxDigits.toString());
		map.put(PARAM_TIMEOUT, timeout == null ? null : timeout.toString());
		map.put(PARAM_ATTEMPTS, attempts == null ? null : attempts.toString());
		
		return doHttpPost(URL_IVR_GATHER, map);
	}
	
	public static Map<String, Object> record(String appId, String accessToken, String session,
			String msg, String maxDuration, String tag, String notifyUrl) throws HoiioException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.putAll(initParam(appId, accessToken));
		
		map.put(PARAM_SESSION, session);
		map.put(PARAM_NOTIFY_URL, notifyUrl);
		map.put(PARAM_MSG, msg);
		map.put(PARAM_TAG, tag);
		map.put(PARAM_MAX_DURATION, maxDuration);
		
		return doHttpPost(URL_IVR_RECORD, map);
	}
	
	public static Map<String, Object> transfer(String appId, String accessToken, String session,
			String msg, String dest, String callerID, String tag, 
			String notifyUrl) throws HoiioException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.putAll(initParam(appId, accessToken));
		
		map.put(PARAM_SESSION, session);
		map.put(PARAM_NOTIFY_URL, notifyUrl);
		map.put(PARAM_MSG, msg);
		map.put(PARAM_TAG, tag);
		map.put(PARAM_DEST, dest);
		map.put(PARAM_CALLER_ID, callerID);
		
		return doHttpPost(URL_IVR_TRANSFER, map);
	}
	
	public static Map<String, Object> hangup(String appId, String accessToken, String session,
			String msg, String tag, String notifyUrl) throws HoiioException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.putAll(initParam(appId, accessToken));
		
		map.put(PARAM_SESSION, session);
		map.put(PARAM_NOTIFY_URL, notifyUrl);
		map.put(PARAM_MSG, msg);
		map.put(PARAM_TAG, tag);
		
		return doHttpPost(URL_IVR_HANGUP, map);
	}
}
