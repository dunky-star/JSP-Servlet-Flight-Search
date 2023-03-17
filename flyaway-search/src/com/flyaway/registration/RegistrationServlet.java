package com.flyaway.registration;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.dunky.flyaway.dao.FlightDBUtil;
import com.dunky.flyaway.entity.Users;


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
		
		try {
			addAdminUser(request, response);
		} catch (Exception exc) {
			
			 throw new ServletException(exc);
		}
	 
	}
	
	// Method to add admin user to the database.
	private void addAdminUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RequestDispatcher dispatcher = null;

		// read student info from form data
		String uname = request.getParameter("name");
		String upwd = request.getParameter("pass");
		String uemail = request.getParameter("email");
		String umobile = request.getParameter("contact");	
		
		// Server side validation
		if(uname == null || uname == ("")) {
			 request.setAttribute("status", "invalidUname");
			 dispatcher = request.getRequestDispatcher("login.jsp");
			 dispatcher.forward(request, response);
		}
		
		if(uemail == null || uemail == ("")) {
			 request.setAttribute("status", "invalidEmail");
			 dispatcher = request.getRequestDispatcher("login.jsp");
			 dispatcher.forward(request, response);
		}
					
		if(upwd == null || upwd == ("")) {
			request.setAttribute("status", "invalidUpwd");
			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		
		if(umobile == null || umobile == ("")) {
			request.setAttribute("status", "invalidMobile");
			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		} else if(umobile.length() > 10) {
			request.setAttribute("status", "invalidMobile");
			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		
		// create a new admin object
		Users theUser = new Users(uname, upwd, uemail, umobile);
		
		// add the admin to the database
		flightDbUtil.addAdminUser(theUser);
				
		// send back to main page (the admin list)
		// SEND AS REDIRECT to avoid multiple-browser reload issue
        response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

}
