package test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBManager;
import model.Address;

import model.Employee;

public class testOneToOne {
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
		
		employee.setAdress(address);
		
		dbManager.connect();
			dbManager.insert(employee);
			employee.getAdress(); 
		dbManager.close(); 
		
		Assert.assertEquals(true,employee.getId()>0);
		Assert.assertEquals(true,employee.getAdress().getId()>0);		
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
