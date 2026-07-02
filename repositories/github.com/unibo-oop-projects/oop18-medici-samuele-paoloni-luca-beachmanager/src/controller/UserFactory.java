package controller;

import java.util.Date;
import java.util.Deque;

import model.Client;
import model.Employee;
import model.Person;
import model.SimpleUser;

public interface UserFactory {

	Person getSimpleUser(String name, String lastName, Date birthDate);
	
	Client getClient(String name, String lastName, Date birthDate, String id, Date registrationDate);
	
	Employee getEmployee(String name, String lastName, Date birthDate);
	
	Deque<SimpleUser> getAllSimpleUser();
	
	Client[] getAllClients();
	
	Employee[] getAllEmployes();
	
	void removeSimpleUser(SimpleUser person);
	
	void removeClient(Client client);
	
	void removeEmployee(Employee employee);
	
	void addEmployee(String name, String surname, Date birthdate);
	
	
}
