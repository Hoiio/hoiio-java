package com.example;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hoiio.sdk.HoiioService;
import com.hoiio.sdk.exception.HoiioException;

public class Call extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String dest1 = request.getParameter("mobileNumber");

		String forward;
		RequestDispatcher view;
		
		if (dest1 != null && !dest1.isEmpty()) {
			String error = call(dest1, "[DEST2_NUMBER]");
			if (error != null) {
				forward = "/index.jsp?error=" + error;
			} else {
				forward = "/calling.jsp";
			}
		} else {
			forward = "/index.jsp";
		}
		view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher view;
		view = request.getRequestDispatcher("/index.jsp");
		view.forward(request, response);
		
	}
	
	private String call(String dest1, String dest2) {
		HoiioService hoiioService = new HoiioService("[APP_ID]", "[ACCESS_TOKEN]");
		
		try {
			hoiioService.call(dest1, dest2, null, null, null);
			return null;
		} catch (HoiioException e) {
			return e.getException();
		}
	}
}
