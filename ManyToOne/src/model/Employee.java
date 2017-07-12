package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Employee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name = "EMPLOYEE_ID")
	private int id;
	
	private String name; 
	private String surname;
	
	
	//Relacion ono a uno, este es el propietario de la relacion
	@OneToOne(fetch=FetchType.LAZY,
			cascade={CascadeType.PERSIST,CascadeType.REMOVE},
			optional=false)
   // @JoinColumn(name = "address_id")
	private Address address; 
	

	@ManyToOne(fetch=FetchType.LAZY,
			cascade={CascadeType.PERSIST,CascadeType.REMOVE},
			optional=false)
	private Departament departament; 
	
	
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
	
	public Departament getDepartament() {
		return departament;
	}
	public void setDepartament(Departament departament) {
		this.departament = departament;
	}
	
}
