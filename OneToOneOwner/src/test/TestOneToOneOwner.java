package test;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBManager;
import model.Address;

import model.Employee;

public class TestOneToOneOwner {
	DBManager dbManager; 
	Employee employee; 
	
	@Before
	public void init(){
		dbManager =   new DBManager(); 	
		dbManager.connect();
		dbManager.deleteAll(Employee.class); 
	}
	
	
	@Test
	public void testInsert(){	
		Address address = getMockAddress("Calle test", 3306);
		Employee employee =getMockEmployee("Pedro","Picapiedra"); 
		
		
		dbManager.connect();		
			dbManager.getEntitymanager().getTransaction().begin();			
			employee.setAdress(address);
			dbManager.getEntitymanager().persist(employee);		
			//Asignar explicitamente la referencia
			employee.getAdress().setEmployee(employee); 			
			dbManager.getEntitymanager().getTransaction().commit();		
		dbManager.close(); 
		
		Assert.assertEquals(true,employee.getId()>0);
		Assert.assertEquals(true,employee.getAdress().getId()>0);		
	
		dbManager.connect();
				ArrayList<Address> list = dbManager
						.selectEqual(Address.class, "employee.name", "Pedro");  
		dbManager.close(); 
		 
		Assert.assertEquals(1,list.size());		
	
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

}
