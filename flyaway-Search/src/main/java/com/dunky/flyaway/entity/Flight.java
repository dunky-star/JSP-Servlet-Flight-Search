package com.dunky.flyaway.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

@Entity  // Using annotation-based metadata is same as "native" strategy in xml-based metadata. 
@BatchSize(size=4)
@Table(name = "flight")
public class Flight {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "flight_from")
	private String from;
	
	@Column(name = "flight_to")
	private String to;
	
	@Column(name = "seat")
	private int seat;
	
	@Column(name = "flight_time")
	private String flightTime;
	
	@Column(name = "flight_date")
	private String flightDate;
	
	@Column(name = "flight_type")
	private String flightType;
	
	@OneToMany(mappedBy = "flight", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
  	      CascadeType.REFRESH }, fetch=FetchType.LAZY)
	private List<Passengers> passengers;
	
	
	// Constructors
	public Flight() {

    }

	public Flight(String from, String to, int seat, String flightTime, String flightDate, String flightType) {
		this.from = from;
		this.to = to;
		this.seat = seat;
		this.flightTime = flightTime;
		this.flightDate = flightDate;
		this.flightType = flightType;
	}

	// Getter, Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
