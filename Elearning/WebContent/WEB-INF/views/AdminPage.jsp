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
<title>Admin Dashboard</title>
</head>
<body>
<h1> Welcome <c:out value='${adminname}' /></h1>
<div align="center">
	<table style="width:80%">
	<tr> 
		<td>All Courses</td>
		<td><a href="<%= request.getContextPath() %>/courseadmin">All Courses</a></td>
		<td><a href="<%= request.getContextPath() %>/userlist">All Users List</a></td>
	</tr>
	</table>
</div>
    
<sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://localhost:3306/elearning"
         user = "root" password = "root"/>
 
      <c:set var = "id" value = "${enrolled.courseid}"/>
 
      <sql:query dataSource = "${snapshot}" var = "count">
         SELECT * FROM course WHERE courseid = ?
         <sql:param value = "${courseid}" />
      </sql:query>
      
</body>
</html>