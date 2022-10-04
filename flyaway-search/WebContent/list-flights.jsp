<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.dunky.flyaway.controller.FlightPassengersCtrl" %>
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
				<th> Seat </th>
				<th> Flight time </th>
				<th> Flight date </th>
				<th> Flight type </th>
				<th> Action </th>
			</tr>
			
			<c:forEach var="tempFlight" items = "${FLIGHT_LIST}">
					
				<tr>
					<td>${tempFlight.from}</td>
					<td>${tempFlight.to}</td>
					<td>${tempFlight.seat}</td>
					<td>${tempFlight.flightTime}</td>
					<td>${tempFlight.flightDate}</td>
					<td>${tempFlight.flightType}</td>
				</tr>
			</c:forEach>
			
		</table>
	</div>
</div>

</body>
</html>