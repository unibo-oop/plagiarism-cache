package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import model.environment.open.OpenImpl;
import model.person.environment.EnvironmentImpl;
import model.person.ticket.PersonTicket;

public class PersonAndParkTest {
	private EnvironmentImpl environment = new EnvironmentImpl();
	private final int numberOfPerson = 10;
	private OpenImpl openPark ;
	private final PersonTicket per1 = new PersonTicket();
	private final PersonTicket per2 = new PersonTicket();
	private final PersonTicket per3 = new PersonTicket();
	private final PersonTicket per4 = new PersonTicket();
	private final PersonTicket per5 = new PersonTicket();
	private final PersonTicket per6 = new PersonTicket();
	private final PersonTicket per7 = new PersonTicket();
	private final PersonTicket per8 = new PersonTicket();
	private final PersonTicket per9 = new PersonTicket();
	private final PersonTicket per10 = new PersonTicket();


	@Test
	public void PeopleEntrance() {
		environment.peopleEntrance(per1);
		environment.peopleEntrance(per2);
		environment.peopleEntrance(per3);
		environment.peopleEntrance(per4);
		environment.peopleEntrance(per5);
		environment.peopleEntrance(per6);
	}
	@Test
	public void ParkTest() {
		openPark = new OpenImpl(numberOfPerson, environment);
		openPark.firstEntrance();
		this.PeopleEntrance();
		assertNotEquals(6, environment.getPersonList().size());
		
	}
	
	@Test
	public void TotalProfitTest() {
		int totalProfit = 0;
		totalProfit =+ environment.getEntrance().getAdultProfit();
		totalProfit =+ environment.getEntrance().getPassProfit();
		totalProfit =+ environment.getEntrance().getReducedProfit();
		assertEquals("Profit correctly calculated", environment.getEntrance().getProfit(), totalProfit);
		assertEquals(environment.getEntrance().getNumTickets(), environment.getPersonList().size());
		
		
	}

	@Test
	public void ProfitTest() {
		assertNotNull("Total profit has not to be null", environment.getEntrance().getProfit());
	}
	
	@Test
	public void ExitTest() {
		this.PeopleEntrance();
		int sizeList = environment.getPersonList().size();
		environment.exitPeople();
		assertNotEquals("Correctly removed a person", environment.getPersonList().size(), sizeList);
	}
	
	@Test
	public void RecirculationTest(){
		int sizeList = environment.getPersonList().size();
		environment.peopleEntrance(per7);
		environment.peopleEntrance(per8);
		environment.peopleEntrance(per9);
		environment.peopleEntrance(per10);
		environment.exitPeople();
		environment.exitPeople();
		assertEquals("Recirculation correctly working", sizeList, environment.getPersonList().size() -2);
	
	}
	
	
	
}
