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

<div id="wrapper">
 <div id="header">
    <h2> Issued Ticket.</h2>
 </div>
</div>

<div id="container">
	<div id="content">
			
		<!--  add a search box -->
		<form action="FlightPassController" method="GET">
		
			
            
        </form>	   
            
	   <table>
			<tr>
				<th> From </th>
				<th> To </th>
				<th> Seat </th>
				<th> Flight time </th>
				<th> Flight date </th>
				<th> Flight Price </th>
				<th> Flight type </th>
				<th> Book Flight </th>
			</tr>
			
			<c:forEach var="tempTicket" items = "${TICKET_LIST}">
			
			   <!-- set up a link for each flight -->
				<c:url var="tempLink" value="FlightPassController">
					<c:param name="command" value="LOAD" />
					<c:param name="flightId" value="${tempFlight.id}" />
				</c:url>
					
				<tr>
					<td>${tempFlight.from}</td>
					<td>${tempFlight.to}</td>
					<td>${tempFlight.seat}</td>
					<td>${tempFlight.flightTime}</td>
					<td>${tempFlight.flightDate}</td>
					<td>${tempFlight.flightPrice}</td>
					<td>${tempFlight.flightType}</td>
				
				</tr>
			</c:forEach>
			
		</table>
	</div>
</div>

<p>
	<a href="http://localhost:8080/flyaway-search/index.jsp"><b>Back</b></a>
</p>

</body>
</html>