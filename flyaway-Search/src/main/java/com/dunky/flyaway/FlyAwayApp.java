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

        Flight flight = new Flight("Ebbs", "DXB", 100, "16:45", "2022-Dec-09",  "Economy");
        flightDao.saveFlight(flight);

        // create some passenger
        FlightTicket ticket1 = new FlightTicket();
        ticket1.getFlight_ticket();
        
        Passengers passenger1 = new Passengers("Lar", "Arma", 10, "Female", "30J", ticket1);
        passenger1.setFlight(flight);
        passengersDao.savePassengers(passenger1);

        FlightTicket ticket2 = new FlightTicket();
        ticket2.getFlight_ticket();
        Passengers passenger2 = new Passengers("Ayo", "Ted", 10, "Female", "40D", ticket2);
        passenger2.setFlight(flight);
        passengersDao.savePassengers(passenger2);
    }
}
