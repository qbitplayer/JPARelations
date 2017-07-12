package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private Address address; 
	
	 @ManyToOne(optional=false, cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	 private Departament departament; 

	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	 private List<Comments> comments = new ArrayList<Comments>();
	
	public List<Comments> getComments() {
		return comments;
	}
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
