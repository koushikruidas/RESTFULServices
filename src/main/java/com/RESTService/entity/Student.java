package com.RESTService.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Roll")
	private int roll;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last-name")
	private String lastName;
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "address")
//	private Address address;
	
	public Student() {}
	
	public Student(/*int roll,*/ String firstName, String lastName/*, Address address */) {
//		this.roll = roll;
		this.firstName = firstName;
		this.lastName = lastName;
//		this.address = address;
	}
	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
//	public int getRoll() {
//		return roll;
//	}
//	public void setRoll(int roll) {
//		this.roll = roll;
//	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
//	public Address getAddress() {
//		return address;
//	}
//	public void setAddress(Address address) {
//		this.address = address;
//	}
	
}
