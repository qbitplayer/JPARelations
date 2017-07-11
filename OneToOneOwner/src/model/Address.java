package model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address { 
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String street; 
	private int    number; 
	private String zipCode; 
	private String city;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="address")
	private Employee employee; 
	
	public int getId() {
		return id;
	} 
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	

	
	
}
