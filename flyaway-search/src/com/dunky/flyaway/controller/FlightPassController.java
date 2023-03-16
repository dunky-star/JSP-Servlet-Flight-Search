package com.dunky.flyaway.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dunky.flyaway.dao.FlightDBUtil;
import com.dunky.flyaway.dao.FlightDao;
import com.dunky.flyaway.dao.PassengersDao;
import com.dunky.flyaway.entity.Flight;
import com.dunky.flyaway.entity.FlightTicket;
import com.dunky.flyaway.entity.Passengers;
// import com.dunky.flyaway.entity.Passengers;

/**
 * FlightPassController.java Controller Servlet
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Geoffrey Duncan Opiyo
 */
@WebServlet("/FlightPassController")
public class FlightPassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final FlightTicket flightTicket = null;
	private FlightDBUtil flightDbUtil;
	private FlightDao flightDao;
	private PassengersDao passengersDao;
	
	@Resource(name="jdbc/web_student_j")  // Connection pool setup under /WebContent/META-INF/Context
	private DataSource dataSource;
       
	// Servlet initialization with the database object.
		@Override
		public void init() throws ServletException {
			super.init();
		   
	    	flightDao = new FlightDao();
	    	passengersDao = new PassengersDao();
	    	
	    	// create our student db util ... and pass in the conn pool / datasource
			try {
				flightDbUtil = new FlightDBUtil(dataSource);
			}
			catch (Exception exc) {
				throw new ServletException(exc);
			}
			
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
				
			case "UPDATE":
				bookTickets(request, response);
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


	private void bookTickets(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			// read flightId and Passengers info from form data.
			int id = Integer.parseInt(request.getParameter("flightId"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String gender = request.getParameter("gender");
			String seatNumber = request.getParameter("seatNumber");
			
			// Create a passenger flight ticket.
			FlightTicket ticket1 = new FlightTicket();
			ticket1.getFlight_ticket();
			
			Flight flight = new Flight();
						
			// create a new passenger object and assigns ticket1 to it.
			Passengers thePassengers = new Passengers(firstName, lastName, gender, flight, seatNumber, ticket1);
			
						
			// perform update on database
			passengersDao.savePassengers(thePassengers);
						
			// send them back to the "ticket printing page" page
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/confirm-ticket.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

	
    private void searchFlights(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read search name from form data
        String theSearchName = request.getParameter("theSearchName");
        
        // search students from db util
        List<Flight> flights = flightDbUtil.searchFlights(theSearchName);
        
        // add students to the request
        request.setAttribute("FLIGHT_LIST", flights);
                
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-flights.jsp");
        dispatcher.forward(request, response);
    }
	

	// Method to send details to Flight JSP for display of Flight information.
		private void listFlights(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// get flights from db util
			List <Flight> flights = flightDao.getAllFlight();
			
			// add flights to the request
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
