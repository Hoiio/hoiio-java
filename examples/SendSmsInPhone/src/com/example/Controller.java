package com.example;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hoiio.sdk.HoiioService;
import com.hoiio.sdk.exception.HoiioException;

public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static final String TAG_CONFIRM = "confirm";
	private static final String NOTIFY_URL = "NOTIFY_URL";
	
	private HoiioService hoiioService = new HoiioService("APP_ID", "ACCESS_TOKEN");

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String callState = request.getParameter("call_state");
		
		Map<String, String> notifyParams = hoiioService.parseNotify(request.getQueryString());
		
		if ("ringing".equals(callState)) {
			// Receive an incoming call
			confirmSubscription((String)notifyParams.get("session"));
		} else if ("ongoing".equals(callState)) {
			if (TAG_CONFIRM.equals(request.getParameter("tag"))) {
				checkDigitPressed(notifyParams);
			}
		} else if ("ended".equals(callState)) {
			// can check status here
		} else {
			
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String callState = request.getParameter("call_state");
		
		Map<String, String> notifyParams = hoiioService.parseNotify(request.getQueryString());
		
		if ("ringing".equals(callState)) {
			// Receive an incoming call
			confirmSubscription((String)notifyParams.get("session"));
		} else if ("ongoing".equals(callState)) {
			if (TAG_CONFIRM.equals(request.getParameter("tag"))) {
				checkDigitPressed(notifyParams);
			}
		} else if ("ended".equals(callState)) {
			// can check status here
		} else {
			response.getWriter().write("received");
		}
	}
	
	private void confirmSubscription(String session) {
		try {
			hoiioService.ivrGather(session, "Please confirm your SMS subscription by pressing 1", 
				1, null, 3, TAG_CONFIRM, NOTIFY_URL);
		} catch (HoiioException e) {
			e.printStackTrace();
		}
	}
	
	private void checkDigitPressed(Map<String, String> notifyParams) {
		if ("1".equals(notifyParams.get("digits"))) {
			addToSubscriber(notifyParams.get("from"));
		} else {
			hangup(notifyParams.get("session"));
		}
	}
	
	private void addToSubscriber(String number) {
		try {
			hoiioService.smsSend(number, null, "Thank you for subscribing. " +
					"This message confirms your subscription to <SMS-In-Phone-Call>. " +
					"This is an example SMS response from Hoiio.", null, null);
		} catch (HoiioException e) {
			e.printStackTrace();
		}
	}
	
	private void hangup(String session) {
		try {
			hoiioService.ivrHangup(session, "You have entered an invalid value. " +
				"Your subscription is not confirmed. Please try again.", null, null);
		} catch (HoiioException e) {
			e.printStackTrace();
		}
	}
}
