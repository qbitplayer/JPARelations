package test;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBManager;
import model.Address;

import model.Employee;

public class TestOneToOne {
	DBManager dbManager; 
	Employee employee; 
	
	@Before
	public void init(){
		dbManager =   new DBManager(); 	
		dbManager.connect();
		dbManager.deleteAll(Employee.class); 
		dbManager.close(); 		
	}
	
	//@Test
	public void testInsert(){	
		Address address = getMockAddress("Calle test", 3306);
		Employee employee =getMockEmployee("Pedro","Picapiedra"); 
		Employee recovered=null; 	
		employee.setAdress(address);
		
		dbManager.connect();
			dbManager.insert(employee);
			//employee.getAdress(); 
		dbManager.close(); 	
		
		Assert.assertEquals(true,employee.getId()>0);
		Assert.assertEquals(true,employee.getAddress().getId()>0);		
	}
	
	
	@Test
	public void testUpdat(){	
		Address address = getMockAddress("Calle test", 3306);
		Employee employee =getMockEmployee("Pedro","Picapiedra"); 
		
		employee.setAdress(address);
		
		
		
		
		dbManager.connect();
			dbManager.insert(employee);
		dbManager.close(); 	
		
		/**** pasa la vida  **/
	
		
		int id = employee.getId(); 
		
		dbManager.connect();
		
		dbManager.getEntitymanager()
					.getTransaction().begin();
		
		     Employee employeeUpdate = dbManager 
							    		 .getEntitymanager()
							    		   .find(Employee.class,id); 
		
			employeeUpdate.setName("UpdateTestName");
			employeeUpdate.setSurname("UpdateTestSurname");
			
			employeeUpdate.getAddress()
									.setCity("Tarragona"); 
			employeeUpdate.getAddress()
									.setStreet("StreetUpdated");
		
		dbManager.getEntitymanager()
					.getTransaction().commit();
			
		dbManager.close();
	

		Assert.assertEquals("UpdateTestName",employeeUpdate.getName());
		Assert.assertEquals("UpdateTestSurname",employeeUpdate.getSurname());
		Assert.assertEquals("Tarragona",employeeUpdate.getAddress().getCity());
		Assert.assertEquals("StreetUpdated",employeeUpdate.getAddress().getStreet());
		
	}
	
	
	
	
	//@Test
	public void testSelect(){	
		Address address = getMockAddress("Calle test", 3306);
		Employee employee =getMockEmployee("Pedro","Picapiedra"); 
		Address address2 = getMockAddress("Calle test", 3306);
		Employee employee2 =getMockEmployee("Pedro","Picapiedra"); 
		
		
		ArrayList<Employee> list=null; 
		
		
		address.setCity("Girona");
		address2.setCity("Tarragona");
		
		employee.setAdress(address);
		employee2.setAdress(address2);
	
		
		dbManager.connect();
		
				dbManager.insert(employee);
				dbManager.insert(employee2);
				
			list = dbManager
					.selectEqual(Employee.class, "address.city", "Girona"); 
		dbManager.close(); 	
		
		Assert.assertEquals(1,list.size());
		Assert.assertEquals("Girona",list.get(0).getAddress().getCity());		
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
		 address.setZipCode("08001" + number);
		return  address;
	}

}
