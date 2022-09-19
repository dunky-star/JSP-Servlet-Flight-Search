package com.dunky.flyaway;

import com.dunky.flyaway.dao.PassengersDao;
import com.dunky.flyaway.dao.FlightDao;
import com.dunky.flyaway.entity.Passengers;
import com.dunky.flyaway.entity.Flight;

public class FlyAwayApp {
    public static void main(String[] args) {

        FlightDao flightDao = new FlightDao();
        PassengersDao passengersDao = new PassengersDao();

        Flight flight1 = new Flight("Ebbs", "DXB", 100, "16:45", "2022-Dec-09",  "Economy");
        flightDao.saveFlight(flight1);

        // create some passenger
        Passengers passenger1 = new Passengers("Lar", "Arma", 10, "Female", "30J");
        passenger1.setFlight(flight1);
        passengersDao.savePassengers(passenger1);

        Passengers passenger2 = new Passengers("Ayo", "Ted", 10, "Female", "40D");
        passenger2.setFlight(flight1);
        passengersDao.savePassengers(passenger2);
    }
}
