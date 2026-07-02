package controller;

import java.util.Date;
import java.util.Deque;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;

import model.Client;
import model.ClientImpl;
import model.Employee;
import model.Person;
import model.SimpleUser;
import view.employee.EmployeeManagementInterface;

public class UserFactoryImpl implements UserFactory {

	private Deque<SimpleUser> simpleUsers;
	private Deque<Client> clients;
	private Deque<Employee> employee;

	private EmployeeManagementInterface empManagementInterface;

	public UserFactoryImpl(EmployeeManagementInterface empManagementInterface) {
		this.simpleUsers = new LinkedList<>();
		this.clients = new LinkedList<>();
		this.employee = new LinkedList<>();

		this.generateEmployees();

		this.empManagementInterface = empManagementInterface;
	}

	@Override
	public Person getSimpleUser(String name, String lastName, Date birthDate) {
		this.simpleUsers.addLast(new SimpleUser(name, lastName, birthDate));
		return this.simpleUsers.getLast();
	}

	@Override
	public Client getClient(String name, String lastName, Date birthDate, String id, Date registrationDate) {
		this.clients.addLast(new ClientImpl(name, lastName, birthDate, id, registrationDate));
		return this.clients.getLast();
	}

	@Override
	public Employee getEmployee(String name, String lastName, Date birthDate) {
		// this.employee.addLast(new EmployeeImpl(name, lastName, birthDate));
		return this.employee.getLast();
	}

	@Override
	public Deque<SimpleUser> getAllSimpleUser() {
		return new LinkedList<SimpleUser>(this.simpleUsers);
	}

	@Override
	public Client[] getAllClients() {
		return this.clients.toArray(new Client[this.clients.size()]);
	}

	@Override
	public Employee[] getAllEmployes() {
		return this.employee.toArray(new Employee[this.employee.size()]);
	}

	@Override
	public void removeSimpleUser(SimpleUser person) {
		if (this.simpleUsers.contains(person)) {
			this.simpleUsers.remove(person);
		}
	}

	@Override
	public void removeClient(Client client) {
		if (this.clients.contains(client)) {
			this.clients.remove(client);
		}
	}

	@Override
	public void removeEmployee(Employee employee) {
		if (this.employee.contains(employee)) {
			this.employee.remove(employee);
			// Aggiorno il model per interfaccia di management dei dipendenti
			this.empManagementInterface.updateEmployeeList(this.getAllEmployes());
		}
	}

	/**
	 * Creo una lista casuale di dipendenti
	 */
	private void generateEmployees() {
		String[] names = { "Giorgio", "Giovanni", "Roberto", "Jessica", "Maria", "Umberto" };
		String[] surnames = { "Rossi", "Bianchi", "Verdi", "Medici", "Paoloni", "Ciriello" };

		IntStream.range(0, 10).forEach(e -> {
			Random r = new Random();
			String name = names[r.nextInt(names.length)];
			String surname = surnames[r.nextInt(surnames.length)];
			int birthDay = r.nextInt(32) + 1;
			int birthMonth = r.nextInt(12) + 1;
			int birthYear = r.nextInt(30 + 1) + 1970;

			Date birthdate = new GregorianCalendar(birthYear, birthMonth, birthDay).getTime();

			this.employee.add(new Employee(name, surname, birthdate));
		});

	}

	@Override
	public void addEmployee(String name, String surname, Date birthdate) {
		Employee emp = new Employee(name, surname, birthdate);
		this.employee.add(emp);
		this.empManagementInterface.updateEmployeeList(this.getAllEmployes());
	}

}
