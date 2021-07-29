<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>


<%
//String id = request.getParameter("userid");
String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String database = "elearning";
String userid = "root";
String password = "root";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Userpage</title>
</head>
<body>
<h1> Welcome <c:out value='${username}' /></h1>
<div align="left">
	<table style="width:80%">
	<tr> 
		<td> Courses available</td>
		<td><a href="course?userid=<c:out value='${userid}' /> ">Courses Available here</a></td>
	</tr>
	</table>
</div>
    

  
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
               <td><a href="unenroll?courseid=<c:out value='${course.courseid}' />&userid=<c:out value='${userid}' /> ">UnEnroll</a></td>
            </tr>
         </c:forEach>
      </table>
  
</body>
</html>