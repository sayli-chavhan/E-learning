<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<h1> WELCOME TO E-LEARNING PORTAL </h1>
	<table style="width:80%">
	<tr> 
		<td> Login Here...</td>
		<td><a href="<%= request.getContextPath() %>/showLoginpage">LOGIN </a></td>
	</tr>
	<tr> 
		<td> Register here...</td>
		<td><a href="<%= request.getContextPath() %>/showRegPage">REGISTER</a></td>
	</tr>
	</table>
</div>
</body>
</html>