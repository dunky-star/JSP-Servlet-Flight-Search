package com.dunky.flyaway.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dunky.flyaway.entity.Passengers;
import com.dunky.flyaway.util.HibernateUtil;

public class PassengersDao {
	
	public void savePassengers(Passengers passenger) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the passenger object
            session.save(passenger);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updatePassengers(Passengers passenger) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(passenger);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletepassengers(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a passenger object
            Passengers passenger = session.get(Passengers.class, id);
            if (passenger != null) {
                session.delete(passenger);
                System.out.println("Passenger is deleted");
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

    public Passengers getPassengers(int id) {

        Transaction transaction = null;
        Passengers passenger = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get a passenger object
            passenger = session.get(Passengers.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return passenger;
    }
	
	/**
     * Get all Passengers
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Passengers> getAllPassengers() {

        Transaction transaction = null;
        List <Passengers> listOfPassengers = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get a user object

            listOfPassengers = session.createQuery("from Passengers").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfPassengers;
    }

}
