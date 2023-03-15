package com.dunky.flyaway.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dunky.flyaway.entity.Flight;
import com.dunky.flyaway.util.HibernateUtil;

public class FlightDao {
	
	public void saveFlight(Flight flight) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the Flight object
            session.save(flight);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateFlight(Flight flight) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the Flight object
            session.update(flight);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteFlight(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a Flight object
            Flight flight = session.get(Flight.class, id);
            if (flight != null) {
                session.delete(flight);
                System.out.println("flight is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public Flight getFlight(int id) {

        Transaction transaction = null;
        Flight flight = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get a flight object
            flight = session.get(Flight.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return flight;
    }
	
	/**
     * Get all Flight
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Flight> getAllFlight() {

        Transaction transaction = null;
        List <Flight> listOfFlight = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get a flight object

            listOfFlight = session.createQuery("from Flight ORDER BY flightType").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfFlight;
    }
    
    
    /**
     * Search all Flights
     * @return matching result
     */
    
    @SuppressWarnings("unchecked")
    public List<Flight> searchFlights(String theSearchName) {
    	
    	 Transaction transaction = null;
         List <Flight> listSearchFlight = null;
         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	             // start a transaction
	             transaction = session.beginTransaction();
	             // get a flight object
	        
		        //
		        // only search by name if theSearchName is not empty
		        //
		        if (theSearchName != null && theSearchName.trim().length() > 0) {
		            // search for firstName or lastName ... case insensitive
		        	listSearchFlight = session.createQuery("from Flight where lower(from) like :theName").getResultList();
		        	((Query) listSearchFlight).setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		        }
		        else {
		            // theSearchName is empty ... so just get all customers
		        	listSearchFlight = session.createQuery("from Flight ORDER BY flightType").getResultList();            
		        }
        
		        // commit transaction
		        transaction.commit();
		        
		  } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        e.printStackTrace();
		  }
         
        // execute the query
        List<Flight> flights = listSearchFlight;
                
        // return the results        
        return flights;
        
    }
	


}
