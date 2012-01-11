package com.example;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.hoiio.sdk.HoiioService;
import com.hoiio.sdk.exception.HoiioException;

@SuppressWarnings("serial")
public class RejectIncomingCallServlet extends HttpServlet {
	
	private static final String BLACKLISTED_NUMBER = "[LIST_OF_BLACKLISTED_NUMBERS]";
	private static final String NOTIFY_URL = "[NOTIFY_URL]";
	
	private HoiioService hoiioService = new HoiioService("[APP_ID]", "[ACCESS_TOKEN]");
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String callState = request.getParameter("call_state");
		
		Map<String, String> notifyParams = hoiioService.parseNotify(request.getQueryString());
		
		String session = notifyParams.get("session");
		
		if ("ringing".equals(callState)) {
			// Receive an incoming call
			String number = notifyParams.get("from").replace("%2B", "+");

			if (BLACKLISTED_NUMBER.contains(number)) {
				// do reject
				hangup(session);
			} else {
				// Can do transfer here, but now only play a message
				playMessage(session, "Congratulations! You got through");
			}
		} else if ("ongoing".equals(callState)) {
			hangup(session);			
		} else if ("ended".equals(callState)) {
			// can check status and stuff here
		} else {
			// do nothing for now
		}
	}
	
	private void playMessage(String session, String message) {
		try {
			hoiioService.ivrPlay(session, message, null, NOTIFY_URL);
		} catch (HoiioException e) {
			e.printStackTrace();
		}
	}
	
	private void hangup(String session) {
		try {
			hoiioService.ivrHangup(session, null, null, NOTIFY_URL);
		} catch (HoiioException e) {
			e.printStackTrace();
		}
	}
}
