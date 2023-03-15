package com.flyaway.registration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.dunky.flyaway.dao.FlightDBUtil;


/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/Register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlightDBUtil flightDbUtil;
	
	@Resource(name="jdbc/web_student_j")  // Connection pool setup under /WebContent/META-INF/Context
	private DataSource dataSource;
	
	// Servlet initialization with the database object.
	@Override
	public void init() throws ServletException {
		super.init();
    	
    	// create our student db util ... and pass in the conn pool / datasource
		try {
			flightDbUtil = new FlightDBUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}
       
   	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String upwd = request.getParameter("pass");
		String umobile = request.getParameter("contact");
		
		out.print(uname);
		out.print(uemail);
		out.print(upwd);
		out.print(umobile);
		
	}

}
