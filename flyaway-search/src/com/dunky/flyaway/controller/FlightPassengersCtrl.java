package com.dunky.flyaway.controller;

import java.io.IOException;
import java.sql.SQLException;

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

@WebServlet("/")
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
			        doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		 
		 try {
	            switch (action) {
	                case "/searchFlight":
	                    searchForm(request, response);
	                    break;
	               
	                default:
	                    listFlights(request, response);
	                    break;
	            }
	        }
	         catch (Exception exc) {
		      throw new ServletException(exc);
	         }
	}
	
	
    private void searchForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	// Method to send details to Flight JSP for display of Flight information.
	private void listFlights(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	        
			// get flights from db util
			List <Flight> flights = flightDao.getAllFlight();
			
	        // add flight to the request
			request.setAttribute("FLIGHT_LIST", flights);
			
			// send to JSP page (view)
	        RequestDispatcher dispatcher = request.getRequestDispatcher("list-flights.jsp");
	        dispatcher.forward(request, response);
		
	}

	
}



