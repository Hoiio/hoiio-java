<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ClickToCall test</title>
</head>
<body>
	<h2>Click-2-Call example</h2>
	<form id="clickToCall" action="call" method="post">
	    <table>
	        <tr>
	            <td>Enter your mobile number: </td>
	            <td><input type="text" name="mobileNumber" value=""/></td>
	        </tr>
	        <tr>
	            <td colspan="2"><input type="submit" value="Call" name="call"/></td>
	        </tr>
	    </table>
	</form>
	<font style="color: red;">
		<%= request.getParameter("error") != null ? request.getParameter("error") : "" %>
	</font>
</body>
</html>