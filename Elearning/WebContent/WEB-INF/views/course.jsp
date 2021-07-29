<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
  
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ALL COURSES HERE</title>
</head>
<body>
	<h1>All Courses Here...</h1>
	<a href="<%= request.getContextPath() %>/userpage">My page </a>
	
<table>
				<thead>
					<tr>
						<th>Course id</th>
						<th>Course Name</th>
						<th>Course Desc</th>
						<th>Course Fee</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="user" items="${listCourse}">

						<tr>
 						<c:set var = "userid" value = "${userid}"/>
							<td><c:out value="${user.courseid}" /></td>
							<td><c:out value="${user.coursename}" /></td>
							<td><c:out value="${user.coursedesc}" /></td>
							<td><c:out value="${user.coursefee}" /></td>
							<c:set var = "courseid" value = "${user.courseid}"/>
							<td><a href="enroll?courseid=<c:out value='${user.courseid}' />&userid=<c:out value='${userid}' /> ">Enroll</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
</html>