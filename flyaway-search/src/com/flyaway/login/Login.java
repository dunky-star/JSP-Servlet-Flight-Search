package com.flyaway.login;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.dunky.flyaway.dao.FlightDBUtil;
import com.dunky.flyaway.entity.Users;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
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
			adminUserLogin(request, response);
		} catch (Exception exc) {
			
			 throw new ServletException(exc);
		}
		
	}
	
	
	// Method to add admin user to the database.
		private void adminUserLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {

			// read student info from form data
			String uemail = request.getParameter("username");
			String upwd = request.getParameter("password");

			
			// create a new admin object
			Users theUser = new Users(uemail, upwd);
			
			// Login admin user
			flightDbUtil.adminUserLogin(theUser);
					
			// send back to main page (the admin list)
			// SEND AS REDIRECT to avoid multiple-browser reload issue
	        response.sendRedirect(request.getContextPath() + "/index.jsp");
		}

}
