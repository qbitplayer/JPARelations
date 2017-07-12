package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Employee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name; 
	private String surname;
	
	
	@OneToOne(
			cascade={CascadeType.PERSIST,CascadeType.REMOVE},
			optional=false)
	//@PrimaryKeyJoinColumn
	private Address address; 
	
	
	
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Address getAdress() {
		return address;
	}
	public void setAdress(Address adress) {
		this.address = adress;
	} 
	
}
