<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.dunky.flyaway.controller.FlightPassController" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% 

if(session.getAttribute("name")==null){
	response.sendRedirect("login.jsp");
}
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Booking App. for Flyaway.</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>



<body>

<input type="hidden" id="status" value="<% request.getAttribute("ticket"); %>">

<div id="wrapper">
 <div id="header">
    <h2> Issued Ticket.</h2>
 </div>
</div>

<div id="container">
	<div id="content">
	
			
	</div>
</div>

<p>
	<a href="http://localhost:8080/flyaway-search/index.jsp"><b>Back</b></a>
</p>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	
	<script type="text/javascript">
	  var status = document.getElementById("ticket").value;
	  if("ticket" == "ticket1"){
		  swal("Ticket", "is", "ticket");
	  }
	  
	</script>

</body>
</html>