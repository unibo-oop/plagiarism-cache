package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.enumerations.ContractType;
import model.enumerations.ExerciseType;
import model.enumerations.LoginData;
import model.enumerations.OperationType;
import model.enumerations.Status;
import model.enumerations.SubscriptionType;
import model.interfaces.Data;
import model.interfaces.Employee;
import model.interfaces.Exercise;
import model.interfaces.Model;
import model.interfaces.Product;
import model.interfaces.User;
import model.interfaces.Validator;

public class Test {
	
	@org.junit.Test
	public void loginTest() {
		final Data data = DataImpl.getInstance();
		
		final Map<String, String> login = new HashMap<>();
		for (LoginData l : LoginData.values()) {
			login.put(l.getId(), l.getPassword());
		}
		
		data.addToLoginData(login);
		
		Validator v = new ValidatorImpl();
		assertEquals(v.validate("Leonardo", "Leo"), Status.ALL_RIGHT);
		assertEquals(v.validate("Stefano", "Leo"), Status.PASSWORD_WRONG);
		assertEquals(v.validate("Francesco", null), Status.NO_INPUT);
		assertEquals(v.validate("Messi", "Leo"), Status.ADMIN_NOT_FOUND);

	}

	@org.junit.Test
	public void BalanceTest() {
		Model m = new ModelImpl();
		
		User user1 = new UserImpl(new PersonImpl.PersonBuilder()
				.firstName("Stefano")
				.lastName("Tamburro")
				.taxCode("STFTMB25349SH")
				.birthday(LocalDate.of(1994, 5, 18))
				.email("stefano.tamburro@unibo.it")
				.telephoneNumber("3316598746")
				.build(),
				new SubscriptionImpl.SubscriptionBuilder()
				.code(new Random().nextInt())
				.type(SubscriptionType.ANNUAL)
				.signingDate(LocalDate.of(2016, 1, 5))
				.expirationDate(LocalDate.of(2016, 1, 5).plusMonths(12))
				//.price(250)
				.paymentDate(LocalDate.of(2016, 1, 12))
				.build());

		User user2 = new UserImpl(new PersonImpl.PersonBuilder()
				.firstName("Leonardo")
				.lastName("Roselli")
				.taxCode("RSLLRD95A112HS")
				.birthday(LocalDate.of(1995, 1, 12))
				.email("leonardo.roselli@studio.unibo.it")
				.telephoneNumber("3319370926")
				.build(),
				new SubscriptionImpl.SubscriptionBuilder()
				.code(new Random().nextInt())
				.type(SubscriptionType.ANNUAL)
				.signingDate(LocalDate.of(2016, 1, 7))
				.expirationDate(LocalDate.of(2016, 1, 7).plusMonths(12))
				//.price(250)
				.paymentDate(LocalDate.of(2016, 11, 12))
				.build());

		User user3 = new UserImpl(new PersonImpl.PersonBuilder()
				.firstName("Francesco")
				.lastName("Pivato")
				.taxCode("PVTFRN1267HG7H")
				.birthday(LocalDate.of(1995, 5, 18))
				.email("francesco.pivato@studio.unibo.it")
				.telephoneNumber("3319076590")
				.build(),
				new SubscriptionImpl.SubscriptionBuilder()
				.code(new Random().nextInt())
				.type(SubscriptionType.QUARTERLY)
				.signingDate(LocalDate.of(2016, 2, 5))
				.expirationDate(LocalDate.of(2016, 2, 5).plusMonths(4))
				//.price(250)
				.paymentDate(LocalDate.of(2016, 11, 12))
				.build());
		
		
		User userMODIFIED3 = new UserImpl(new PersonImpl.PersonBuilder()
				.firstName("ProvaModifica")
				.lastName("Pivato")
				.taxCode("PVTFRN1267HG7H")
				.birthday(LocalDate.of(1995, 5, 18))
				.email("francesco.pivato@studio.unibo.it")
				.telephoneNumber("3319076590")
				.build(),
				new SubscriptionImpl.SubscriptionBuilder()
				.code(new Random().nextInt())
				.type(SubscriptionType.QUARTERLY)
				.signingDate(LocalDate.of(2016, 2, 5))
				.expirationDate(LocalDate.of(2016, 2, 5).plusMonths(4))
				//.price(250)
				.paymentDate(LocalDate.of(2016, 11, 12))
				.build());
		
		
		m.saveUser(user1);
		m.saveUser(user2);
		m.saveUser(user3);
		
		assertEquals(DataImpl.getInstance().getUsersList().size(), 3);
		m.saveUser(userMODIFIED3);
		assertEquals(DataImpl.getInstance().getUsersList().size(), 3);
		assertEquals(DataImpl.getInstance().getUsersList().get(2).getPerson().getFirstName(), "ProvaModifica");
		
		Product p = new ProductImpl.ProductBuilder()
                .name("Maglietta")
                .category("Vestiario")
                .code("1")
                .description("prodotto messo a disposizione dalla nostra palestra")
                .price(50.0)
                .quantity(100)
                .builder();

		Employee employee1 = new EmployeeImpl(new PersonImpl.PersonBuilder()
				.firstName("ProvaImpiegato")
				.lastName("ROSE")
				.taxCode("PROVACODICE")
				.birthday(LocalDate.of(1995, 5, 18))
				.email("pi@bo.it")
				.telephoneNumber("3319034590")
				.build() , 900, LocalDate.of(2016, 11, 5), ContractType.UNDETERMINED);
		
		Exercise ex = new ExerciseImpl.ExerciseBuilder()
				 .name("primo es")
				 .reps("10")
				 .rest("5")
				 .set("set")
				 .type(ExerciseType.ABDOMINALS)
				 .weight("weight")
				 .build();
		
		m.addEmployee(employee1);
		
		m.addProduct(p);
		m.getUserList().get(m.retreiveData().getUsersList().indexOf(user1)).getWorkout()
															.setExerciseInDay("1", ex);
		assertTrue(m.getUserList().get(m.retreiveData().getUsersList().indexOf(user1))
															.getWorkout().getAllExercisesByDay("1")
															.contains(ex));
		m.getUserList().get(m.retreiveData().getUsersList().indexOf(user1)).getWorkout()
															.deleteExercise("1", ex);
		assertFalse(m.getUserList().get(m.retreiveData().getUsersList().indexOf(user1))
															.getWorkout().getAllExercisesByDay("1")
															.contains(ex));
		List<Product> productList = new ArrayList<>();
		productList.add(p);
		assertEquals(DataImpl.getInstance().getProductList(), productList);
		assertEquals(DataImpl.getInstance().getProductList(), productList);
		LocalDate date = LocalDate.now();
		Map<Product, Integer> map = new HashMap<>();
		map.put(p, p.getQuantity());
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee1);
		assertEquals(DataImpl.getInstance().getEmployees(), employeeList);
		BalanceImpl.getInstance().sellProduct(date, p, 3);
		assertEquals(DataImpl.getInstance().getProductInWharehouse(),map);
		Map<OperationType, Double> map1 = new HashMap<>();
		map1.put(OperationType.PRODUCT_SOLD, p.getPrice()*3);
		assertEquals(BalanceImpl.getInstance().getFinancialOperation().get(date), map1);
		m.dismissEmployee(employee1);
		employeeList.remove(employee1);
		assertEquals(DataImpl.getInstance().getEmployees(), employeeList);
		m.addEmployee(employee1);
		
		assertEquals(BalanceImpl.getInstance().getMonthlyRevenue().get(new Pair<>(2016, Month.JANUARY)), 1200.0, 0.0);
		assertEquals(BalanceImpl.getInstance().getMonthlyExpenses().get(new Pair<>(2016, Month.JANUARY)), 400.0, 0.0);
		assertEquals(BalanceImpl.getInstance().getMonthlyExpenses().get(new Pair<>(2016, Month.DECEMBER)), 400.0, 0.0);
		
	}
	
	

}
