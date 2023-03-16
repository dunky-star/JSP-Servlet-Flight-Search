package com.dunky.flyaway.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.dunky.flyaway.entity.Flight;
import com.dunky.flyaway.entity.Users;

public class FlightDBUtil {
	
	private DataSource dataSource;
	
	public FlightDBUtil(DataSource theDataSource) {
		
		dataSource = theDataSource;
	}
	

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}
	


public List<Flight> searchFlights(String theSearchName)  throws Exception {
    List<Flight> flights = new ArrayList<>();
    
    Connection myConn = null;
    PreparedStatement myStmt = null;
    ResultSet myRs = null;
    try {
        
        // get connection to database
        myConn = dataSource.getConnection();
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // create sql to search for students by name
            String sql = "select * from flight where lower(flight_from) like ? or lower(flight_to) like ? "
            		+ "or lower(flight_type) like ? or lower(flight_date) like ?";
            // create prepared statement
            myStmt = myConn.prepareStatement(sql);
            // set params
            String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
            myStmt.setString(1, theSearchNameLike);
            myStmt.setString(2, theSearchNameLike);
            
        } else {
            // create sql to get all students
            String sql = "select * from flight order by flight_from";
            // create prepared statement
            myStmt = myConn.prepareStatement(sql);
        }
        
        // execute statement
        myRs = myStmt.executeQuery();
        
        // retrieve data from result set row
        while (myRs.next()) {
            
            // retrieve data from result set row
            int id = myRs.getInt("id");
            String from = myRs.getString("flight_from");
            String to = myRs.getString("flight_to");
            int seat = Integer.valueOf(myRs.getString("seat"));
            String flightTime = myRs.getString("flight_time");
            String flightDate = myRs.getString("flight_date");
            String flightType = myRs.getString("flight_type");
            
            // create new student object
            Flight tempFlight = new Flight(id, from, to, seat, flightTime, flightDate, flightType);
            
            // add it to the list of students
            flights.add(tempFlight);            
        }
        
        return flights;
    } finally {
        // clean up JDBC objects
        close(myConn, myStmt, myRs);
    }
  }


public void addAdminUser(Users theUser) throws Exception {

	Connection myConn = null;
	PreparedStatement myStmt = null;
	
	try {
		// get db connection
		myConn = dataSource.getConnection();
		
		// create sql for insert
		String sql = "insert into admin_users "
				   + "(uname, upwd, uemail, umobile) "
				   + "values (?, ?, ?, ?)";
		
		myStmt = myConn.prepareStatement(sql);
		
		// set the param values for the student
		myStmt.setString(1, theUser.getUname());
		myStmt.setString(2, theUser.getUpwd());
		myStmt.setString(3, theUser.getUemail());
		myStmt.setString(4, theUser.getUmobile());
		
		// execute sql insert
		myStmt.execute();
	}
	finally {
		// clean up JDBC objects
		close(myConn, myStmt, null);
	}
}



public Users adminUserLogin(String uemail, String upwd) throws Exception {

	Users theUser = null;
	
	Connection myConn = null;
	PreparedStatement myStmt = null;
	ResultSet myRs = null;

	
	try {
				
		// get connection to database
		myConn = dataSource.getConnection();
		
		// create sql to get selected student
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
			 uemail = myRs.getString("uemail");
			 upwd = myRs.getString("upwd");
			
		
			// use the studentId during construction
			theUser = new Users(uemail, upwd);
		}
		else {
			throw new Exception("Could not find user with email: " + uemail);
		}				
		
		return theUser;
	}
	finally {
		// clean up JDBC objects
		close(myConn, myStmt, myRs);
	}
}




}

