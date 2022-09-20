package com.dunky.flyaway.entity;


import javax.persistence.Embeddable;


@Embeddable
public class FlightTicket {

	private String randomTicket = "XXXX12Z";
	
	public FlightTicket() {
	
	}

	public FlightTicket(String randomTicket) {
		super();
		this.randomTicket = randomTicket;
	}

	public String getRandomTicket() {
		return randomTicket;
	}

	public void setRandomTicket(String randomTicket) {
		this.randomTicket = randomTicket;
	}
	
			
}
