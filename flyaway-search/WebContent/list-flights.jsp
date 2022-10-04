<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.dunky.flyaway.controller.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Booking App. for Flyaway.</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>



<body>

<div id="wrapper">
 <div id="header">
    <h2> Flyaway Booking app.</h2>
 </div>
</div>

<div id="container">
	<div id="content">
				   
	   <table>
			<tr>
				<th> From </th>
				<th> To </th>
				<th> Email Address </th>
				<th> Action </th>
			</tr>
			
			<c:forEach var="tempStudent" items = "${STUDENT_LIST}">
			
			    <!-- set up a link for each student -->
				<c:url var="tempLink" value="StudentControllerServlet">
					<c:param name="command" value="LOAD" />
					<c:param name="studentId" value="${tempStudent.id}" />
				</c:url>
				
				
			    <!-- set up a link for each student -->
				<c:url var="deleteLink" value="StudentControllerServlet">
					<c:param name="command" value="DELETE" />
					<c:param name="studentId" value="${tempStudent.id}" />
				</c:url>
				
				<tr>
					<td>${tempStudent.firstName}</td>
					<td>${tempStudent.lastName}</td>
					<td>${tempStudent.email}</td>
					<td>
					<a href="${tempLink}">Update</a>
					|
					<a href="${deleteLink}"
						onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
						Delete</a>
					</td>
				</tr>
			</c:forEach>
			
		</table>
	</div>
</div>

</body>
</html>