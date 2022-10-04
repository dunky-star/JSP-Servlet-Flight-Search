package com.dunky.flyaway.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
