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
	<a href="<%= request.getContextPath() %>/adminpage">My page </a>
	<%-- <c:set var = "id" value = "${course.courseid}"/> --%>
<table border = "1" width = "100%">
         <tr>
            <th>Course ID</th>
            <th>Course Name</th>
            <th>Course Description</th>
            <th>Course Fee</th>
            <th>Action</th>
         </tr>
            
         <c:forEach var = "course" items = "${listcourse}">
            <tr>
               <td><c:out value = "${course.courseid}"/></td>
               <td><c:out value = "${course.coursename}"/></td>
               <td><c:out value = "${course.coursedesc}"/></td>
               <td><c:out value = "${course.coursefee}"/></td>
               <td><a href="delete?id=<c:out value='${course.courseid}' />">Delete</a>
               <a href="<%= request.getContextPath() %>/edit?id=<c:out value='${course.courseid}' />">Edit</a></td>
            </tr>
         </c:forEach>
      </table>
     <c:if test="${course != null}">
					<form action="edit" method="post">
				</c:if>
				<c:if test="${course == null}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${course != null}">Edit Course
            		</c:if>
						<c:if test="${course == null}">Add New Course
            		</c:if>
					</h2>
				</caption>

				<c:if test="${course != null}">
					<input type="hidden" name="id" value="<c:out value='${course.courseid}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Course Name</label> <input type="text"
						value="<c:out value='${course.coursename}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Course Desc</label> <input type="text"
						value="<c:out value='${user.email}' />" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>Course fee</label> <input type="text"
						value="<c:out value='${user.country}' />" class="form-control"
						name="country">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
				</body>
</html>