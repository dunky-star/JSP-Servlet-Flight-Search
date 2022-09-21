package com.dunky.flyaway;

import com.dunky.flyaway.dao.PassengersDao;
import com.dunky.flyaway.dao.FlightDao;
import com.dunky.flyaway.entity.Flight;
import com.dunky.flyaway.entity.Passengers;
import com.dunky.flyaway.entity.FlightTicket;

public class FlyAwayApp {
    public static void main(String[] args) {

        FlightDao flightDao = new FlightDao();
        PassengersDao passengersDao = new PassengersDao();

        Flight flight = new Flight("Kigali", "Joburg", 100, "05:00", "2023-01-01",  "Economy");
        flightDao.saveFlight(flight);
        
        Flight flight1 = new Flight("EBB", "DXB", 100, "16:45", "2022-12-25",  "Business");
        flightDao.saveFlight(flight);

        // create some passenger
        FlightTicket ticket1 = new FlightTicket();
        ticket1.getFlight_ticket();
        
        Passengers passenger1 = new Passengers("Lar", "Arma", 12, "Female", "30J", ticket1);
        passenger1.setFlight(flight);
        passengersDao.savePassengers(passenger1);

        FlightTicket ticket2 = new FlightTicket();
        ticket2.getFlight_ticket();
        Passengers passenger2 = new Passengers("Ayo", "Ted", 20, "Female", "40D", ticket2);
        passenger2.setFlight(flight1);
        passengersDao.savePassengers(passenger2);
        
        FlightTicket ticket3 = new FlightTicket();
        ticket3.getFlight_ticket();
        Passengers passenger3 = new Passengers("Anjana", "Nice", 23, "Male", "37K", ticket3);
        passenger3.setFlight(flight);
        passengersDao.savePassengers(passenger3);
    }
}
