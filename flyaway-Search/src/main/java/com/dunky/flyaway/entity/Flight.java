package com.dunky.flyaway.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Flight")
public class Flight {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flight_number")
	private int flightNumber;
	
	@Column(name = "from")
	private String from;
	
	@Column(name = "to")
	private String to;
	
	@Column(name = "seat")
	private int seat;
	
	@Column(name = "flight_time")
	private String flightTime;
	
	@Column(name = "flight_date")
	private String flightDate;
	
	@Column(name = "flight_type")
	private String flightType;
	
	@OneToMany(mappedBy = "Flight", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
  	      CascadeType.REFRESH })
	private List<Passengers> passengers;
	
	
	// Constructors
	public Flight() {

    }

	public Flight(String from, String to, int seat, String flightTime, String flightDate, String flightType,
			List<Passengers> passengers) {
		this.from = from;
		this.to = to;
		this.seat = seat;
		this.flightTime = flightTime;
		this.flightDate = flightDate;
		this.flightType = flightType;
		this.passengers = passengers;
	}

	// Getter, Setter
	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public String getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public List<Passengers> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passengers> passengers) {
		this.passengers = passengers;
	}
	
	
}
