<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<div align="center">
	<h1>Login</h1>
	<form action="login" method="post">
		<table style="width:80%">
		<tr> 
			<td> Email Id</td>
			<td><input type="text" name="emailid" /></td>
		</tr>
		<tr> 
			<td> Password</td>
			<td><input type="password" name="password" /></td>
		</tr>
		</table>
		<input type="radio" id="admin" name="role" value="admin" />Admin 
		<input type="radio" id="user" name="role" value="user" /> User
		<input type="submit" name="login" />
	</form>
	<% String message = (String)request.getAttribute("alertMsg");%>
</div>
</body>
</html>