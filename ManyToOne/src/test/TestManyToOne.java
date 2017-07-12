package test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBManager;
import model.Address;
import model.Departament;
import model.Employee;

public class TestManyToOne {
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
		Address address = getMockAddress("Calle test", 3306);
		Employee employee1 =getMockEmployee("Pedro","Picapiedra");
		Employee employee2 =getMockEmployee("Pablo","Marmol"); 
		Employee employee3 =getMockEmployee("Blanca","Nives"); 
		Employee employee4 =getMockEmployee("Corre","Caminos"); 
		
		Departament  departament = getMockDepartament("Marketin"); 
		
		
		
		
		dbManager.connect();
			employee1.setAdress(address);
			employee1.setDepartament(departament); 
			dbManager.insert(employee1);
			
			employee2.setDepartament(departament); 
			dbManager.insert(employee2);
			
			employee3.setDepartament(departament); 
			dbManager.insert(employee3);
			
			employee4.setDepartament(departament); 
			dbManager.insert(employee4);
			
			
			employee1.getAdress();
			
		
		dbManager.close(); 
		
		Assert.assertEquals(true,employee1.getId()>0);
		Assert.assertEquals(true,employee1.getAdress().getId()>0);	
		Assert.assertEquals(true,employee1.getDepartament().getId()>0);
		
		Assert.assertEquals(true,employee2.getDepartament().getId()>0);	
		Assert.assertEquals(true,employee3.getDepartament().getId()>0);	
		Assert.assertEquals(true,employee4.getDepartament().getId()>0);	
		
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

}
