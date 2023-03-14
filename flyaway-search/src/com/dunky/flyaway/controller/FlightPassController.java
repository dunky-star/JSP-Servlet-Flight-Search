package com.dunky.flyaway.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dunky.flyaway.dao.FlightDao;
import com.dunky.flyaway.dao.PassengersDao;
import com.dunky.flyaway.entity.Flight;
import com.dunky.flyaway.entity.Passengers;

/**
 * FlightPassController.java Controller Servlet
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Geoffrey Duncan Opiyo
 */
@WebServlet("/FlightPassController")
public class FlightPassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlightDao flightDao;
	private PassengersDao passengersDao;
       
	// Servlet initialization with the database object.
		@Override
		public void init() throws ServletException {
			super.init();
		   
	    	flightDao = new FlightDao();
	    	passengersDao = new PassengersDao();
		}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing flights
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listFlights(request, response);
				break;
				
			case "LOAD":
				loadFlight(request, response);
				break;
				
			case "SAVE":
				savePassengers(request, response);
				break;
						
			case "SEARCH":
                searchFlights(request, response);
                break;
								
			default:
				listFlights(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	

	private void loadFlight(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read flight id from form data
			// String theFlightId = request.getParameter("flightId");
			int id = Integer.parseInt(request.getParameter("flightId"));
			
			// get flight from database (db util)
			Flight theFlight = flightDao.getFlight(id);
			
			// place flight in the request attribute
			request.setAttribute("THE_FLIGHT", theFlight);
			
			// send to jsp page: booking-flight-form.jsp.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/booking-flight-form.jsp");
			dispatcher.forward(request, response);		
	}

	private void searchFlights(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	

	// Method to send details to Flight JSP for display of Flight information.
		private void listFlights(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// get flights from db util
			List <Flight> flights = flightDao.getAllFlight();
			
			// add students to the request
			request.setAttribute("FLIGHT_LIST", flights);
					
			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-flights.jsp");
			dispatcher.forward(request, response);
		}
		
		
	// Method to enter and save booking details for a customer.
		private void savePassengers(HttpServletRequest request, HttpServletResponse response) 
				throws Exception {

				// read flight id from form data
				// String theFlightId = request.getParameter("flightId");
				int idFlight = Integer.parseInt(request.getParameter("flightId"));
				
				// get flight from database (db util)
				Flight theFlight = flightDao.getFlight(idFlight);
				
				// place flight in the request attribute
				request.setAttribute("THE_FLIGHT", theFlight);
				
				// send to jsp page: booking-flight-form.jsp.jsp
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher("/booking-confirmed-form.jsp");
				dispatcher.forward(request, response);		
		}
		

		
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
