package com.RESTService.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Address {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	@Column(name = "Street")
	private String streetName;
	@Column(name = "City")
	private String city;
	@Column(name = "State")
	private String state;
//	
//	@OneToOne(mappedBy = "address", cascade = {CascadeType.DETACH, CascadeType.MERGE,
//			CascadeType.PERSIST,CascadeType.REFRESH})
//	private Student student;

}
