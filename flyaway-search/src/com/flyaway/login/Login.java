package com.flyaway.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
	@Resource(name="jdbc/web_student_j")  // Connection pool setup under /WebContent/META-INF/Context
	private DataSource dataSource;
	
	// Servlet initialization with the database object.
	@Override
	public void init() throws ServletException {
		super.init();
    	
   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			// read student info from form data
			String uemail = request.getParameter("username");
			String upwd = request.getParameter("password");
			// For user login session store
			HttpSession session = request.getSession();
			RequestDispatcher dispatcher = null;
			
			// Server side validation
			if(uemail == null || uemail == ("")) {
				session.setAttribute("status", "invalidEmail");
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
			
			if(upwd == null || upwd == ("")) {
				session.setAttribute("status", "invalidUpwd");
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
			
			try {
				// get db connection
				myConn = dataSource.getConnection();
				// create sql for insert
				String sql = "select * from admin_users where uemail=? and upwd=?";
				// create prepared statement
				myStmt = myConn.prepareStatement(sql);
				
				// set params
				myStmt.setString(1, uemail);
				myStmt.setString(2, upwd);
				
				// execute statement
				myRs = myStmt.executeQuery();
				
				// retrieve data from result set row
				if (myRs.next()) {
					  session.setAttribute("name", myRs.getString("uname") );
					  dispatcher = request.getRequestDispatcher("index.jsp");
				}
				else {
					 request.setAttribute("status", "failed");
					 dispatcher = request.getRequestDispatcher("login.jsp");
				}	
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
		    }
	}
	
	


}
