<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration form</title>
</head>
<body>
	<div align="center">
	<h1> User Registration form </h1>
	<form name="registerForm" action="register" method="post">
	<table style="width:80%">
	<tr> 
		<td> User id</td>
		<td><input type="text" name="userid" /></td>
	</tr>
	<tr> 
		<td> User name</td>
		<td><input type="text" name="username" /></td>
	</tr>
	<tr> 
		<td> Contact</td>
		<td><input type="text" name="contact" /></td>
	</tr>
	<tr> 
		<td> Email Id</td>
		<td><input type="text" name="emailid" /></td>
	</tr>
	<tr> 
		<td> Password</td>
		<td><input type="password" name="password" /></td>
	</tr>
	<tr> 
		<td>Address</td>
		<td><input type="text" name="address" /></td>
	</tr>
	</table>
	<input type="submit" name="Submit" />
	</form>
</div>
</body>
</html>