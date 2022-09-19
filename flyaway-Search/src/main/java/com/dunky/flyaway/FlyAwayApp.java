package com.dunky.flyaway;

import com.dunky.flyaway.dao.PassengersDao;
import com.dunky.flyaway.dao.FlightDao;
import com.dunky.flyaway.entity.Passengers;
import com.dunky.flyaway.entity.Flight;

public class FlyAwayApp {
    public static void main(String[] args) {

        FlightDao flightDao = new FlightDao();
        PassengersDao PassengersDao = PassengersDao();

        Flight flight1 = new Flight("Ebbs", "DXB", "yocare@yohunters.com");
        flightDao.saveFlight(flight1);

        // create some courses
        Course tempCourse1 = new Course("Full Stack Java - SimpliLearn Bootcamp");
        tempCourse1.setInstructor(instructor);
        courseDao.saveCourse(tempCourse1);

        Course tempCourse2 = new Course("The PHP Developer Masterclass");
        tempCourse2.setInstructor(instructor);
        courseDao.saveCourse(tempCourse2);
    }
}
