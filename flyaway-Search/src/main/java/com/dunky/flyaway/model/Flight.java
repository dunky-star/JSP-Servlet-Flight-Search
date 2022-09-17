package com.dunky.flyaway.model;

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
	
	private int flightNumber;
	private String from;
	private String to;
	private int seat;
	private String flightTime;
	private String flightDate;
	private String Type;

}
