package test;


import java.sql.Date;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBManager;
import model.Address;
import model.Comments;
import model.Departament;
import model.Employee;

public class TestOneToMany {
	DBManager dbManager; 
	Employee employee; 
	
	@Before
	public void init(){
		dbManager =   new DBManager(); 	
		dbManager.connect();
		dbManager.deleteAll(Employee.class); 
		dbManager.close();
	}
	
	
	@Test
	public void testInsert(){	
		
		Employee employee =getMockEmployee("Pedro","Picapiedra");
		
		
		Comments comment1 =getMockComments("user1","user1@user.com");
		Comments comment2 =getMockComments("user2","user2@user.com"); 
		Comments comment3 =getMockComments("user3","user3@user.com"); 
		Comments comment4 =getMockComments("user4","user4@user.com"); 
		
		 Address address = getMockAddress("Calle test",1234); 
		 Departament  departament = getMockDepartament("Marketin"); 
		
		
		dbManager.connect();
			
			
			dbManager.getEntityManager().getTransaction().begin();		
			
			employee.setAdress(address);
			employee.setDepartament(departament);
			
			    dbManager.getEntityManager().persist(employee);
			    
			    
					employee.getComments().add(comment1); 
					employee.getComments().add(comment2); 
					employee.getComments().add(comment3); 
					employee.getComments().add(comment4); 					
		    
					
					
			dbManager.getEntityManager().getTransaction().commit();
			
			
				
		dbManager.close(); 
		
		
		dbManager.connect();		
	     Employee recovered = dbManager
	    		.find(Employee.class, employee.getId()); 			
	    dbManager.close(); 
	
	   
		Assert.assertEquals(true,employee.getId()>0);
		Assert.assertEquals(true,employee.getAdress().getId()>0);	
		Assert.assertEquals(true,employee.getDepartament().getId()>0);
		
		   Assert.assertEquals(4,recovered.getComments().size()); 
		    Assert.assertEquals("user1",recovered.getComments().get(0).getUser()); 
		    Assert.assertEquals("user2",recovered.getComments().get(1).getUser()); 
			
	}
	
	public void updateDepartament(){
		
		
		
	}
	
	
	
	
	

	private Employee getMockEmployee(String name, String surname) {
		 Employee employee = new Employee();
		 employee.setName(name);
		 employee.setSurname(surname);
		 return employee;
	}
	
	private	Address getMockAddress(String street, int number) {
		Address address = new Address(); 
		 address.setStreet(street);
		 address.setNumber(number);
		 address.setCity("Barcelona");
		 address.setZipCode("08001");
		return  address;
	}
	
	private Departament getMockDepartament(String name){ 
		Departament departament = new Departament(); 
		departament.setName(name);
		return departament; 
	}
	
	private Comments getMockComments(String myUser, String email) { 
		Comments comments1 = new Comments();
		comments1.setUser(myUser);
		comments1.setEmail(email); 
		comments1.setSummary("Esto es un resumen"); 
		comments1.setComments("Esto es un comentario"); 
		comments1.setDatum(new Date(System.currentTimeMillis())); 
		comments1.setWebpage("poo.cifo"); 
		return comments1;
	}
	

}
