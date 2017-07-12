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
	private int id;
	
	private String name; 
	private String surname;
	

	//Por defecto el join columna sera creada como address_id
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE},
					optional=false)
	private Address address;
	
	
	 //Por defecto el join columna sera creada como departament_id
	 @ManyToOne(optional=false, 
			 		cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	 private Departament departament; 

	 //mappedBy No es el dueño de la relacion por lo tanto no crea join columna 
	 @OneToMany(mappedBy = "employee", 
			 	cascade = CascadeType.ALL)
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
