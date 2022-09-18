package com.dunky.flyaway.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Passengers")
public class Passengers {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passengerId")
    private int passengerId;
	
	private String firstName;
	
	private String lastName;
	
	private int age;
	
	private String gender;
	
	private int seatNumber;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_number")
    private Flight flight;

}
