package com.dunky.flyaway.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.dunky.flyaway.dao.FlightDao;
import com.dunky.flyaway.entity.Flight;

/**
 * FlightPassengersCtrl.java Controller Servlet
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Geoffrey Duncan Opiyo
 */

@WebServlet("/FlightPassengersCtrl")
public class FlightPassengersCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlightDao flightDao;
       
	// Servlet initialization with the database object.
	@Override
	public void init() throws ServletException {
		super.init();
    
    	flightDao = new FlightDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing flights.
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listFlights(request, response);
				break;
								
		     }
		
	      }
	        catch (Exception exc) {
		      throw new ServletException(exc);
	        }
	}
	
	
    // Method to send details to Flight JSP for display of Flight information.
	private void listFlights(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get flights from db util
		List<Flight> flights = flightDao.getAllFlight();
		
		// add students to the request
		request.setAttribute("FLIGHT_LIST", flights);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-flights.jsp");
		dispatcher.forward(request, response);
	}

	
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }


}



