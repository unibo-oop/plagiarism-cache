package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import model.enumerations.OperationType;
import model.enumerations.Status;
import model.interfaces.Balance;
import model.interfaces.Product;
import model.interfaces.Subscription;

public class BalanceImpl implements Serializable, Balance {

	private static final long serialVersionUID = 5194130555686704712L;
	private static final BalanceImpl SINGLETON = new BalanceImpl();
	private static final double INITIAL_BUDGET = 100000.0;
	private static final double MONTHLY_TAX = 100.0;
	private static final double MONTHLY_OPERATIVE_EXPENSES = 300.0;
	private static final int UNIT = 1;
	private static final String PROFIT = "PROFIT";
	private static final String ESPENSE = "EXPENSE";
	private static final int STARTACTIVITY = 2016;
	
	private Map<LocalDate, Map<OperationType, Double>> financialOperation;
	private Map<Pair<Integer,Month>, Double> monthlyRevenue;
	private Map<Pair<Integer,Month>, Double> monthlyExpenses;
	private Map<Pair<Integer,Month>, Integer> MonthlyInscriptions;

	@Override
	public Map<LocalDate, Map<OperationType, Double>> getFinancialOperation() {
		return this.financialOperation;
	}
	
	@Override
	public Map<Pair<Integer,Month>, Double> getMonthlyRevenue() {
		return this.monthlyRevenue;
	}
	
	@Override
    public Map<Pair<Integer,Month>, Double> getMonthlyExpenses() {
		return this.monthlyExpenses;
	}
    
    @Override
    public Map<Pair<Integer,Month>, Integer> getMonthlyInscriptions() {
    	return this.MonthlyInscriptions;
    }
    
    private void populateMap() {
    	int courrentYear = LocalDate.now().getYear();
    	Month courrentMonth = LocalDate.now().getMonth();
    	for (int i = STARTACTIVITY; i <= courrentYear; i++) {
    		for ( Month c : Month.values()) {
    			if (i != courrentYear || c.compareTo(courrentMonth) <= 0) {
    				this.monthlyRevenue.put(new Pair<>(i,c), 0.0);
    				this.monthlyExpenses.put(new Pair<>(i,c), MONTHLY_OPERATIVE_EXPENSES + MONTHLY_TAX);
    				this.MonthlyInscriptions.put(new Pair<>(i,c), 0);
    			}
    		}
    	}
    }

	private BalanceImpl() {
		this.financialOperation = new HashMap<>();
		this.monthlyRevenue = new HashMap<>();
		this.monthlyExpenses = new HashMap<>();
		this.MonthlyInscriptions = new HashMap<>();
		populateMap();
	}

	protected  static BalanceImpl getInstance() {
		return SINGLETON;
	}

	private Map<OperationType, Double> prepareMap(Map<OperationType, Double> map, OperationType type, double cost) {
		map.merge(type, cost, (x,y)->x+y);
		return map;
	}

	private void setOperation(LocalDate date, OperationType type, double cost) {
		if(!this.financialOperation.containsKey(date)) {
			this.financialOperation.put(date, prepareMap(new HashMap<>(), type, cost));
		}
		else {
			prepareMap(this.financialOperation.get(date), type, cost);
		}
		if (type.getType().equals(PROFIT)) {
			this.monthlyRevenue.merge(new Pair<>(date.getYear(), date.getMonth()), cost, (x,y)->x+y);
		}
		else if (type.getType().equals(ESPENSE)) {
			this.monthlyExpenses.merge(new Pair<>(date.getYear(), date.getMonth()), cost, (x,y)->x+y);
		}
	}

	@Override
	public Status sellProduct(LocalDate date, Product product, int quantity) {
		if(DataImpl.getInstance().checkAvailability(product, quantity)) {
			DataImpl.getInstance().putInSoldProducts(date, product);
			setOperation(date, OperationType.PRODUCT_SOLD, product.getPrice()*quantity);
			return Status.ALL_RIGHT;
		}
		return Status.QUANTITY_NOT_AVAILABLE;
	}

	protected void subscriptionFirm(Subscription subscription) {
		setOperation(subscription.getPaymentDate(), OperationType.USER_REGISTRATION, subscription.getPrice());
		this.MonthlyInscriptions.merge(new Pair<>(subscription.getSigningDate().getYear(), 
													subscription.getSigningDate().getMonth()), 
													UNIT, (x, y)-> x + y);
	}
	
	@Override
	public double getCourrentBalance() {

		double profit = this.monthlyRevenue.values().stream().collect(Collectors.summingDouble(x->x));
		double expense = this.monthlyExpenses.values().stream().collect(Collectors.summingDouble(x->x));
		return INITIAL_BUDGET + (profit-expense);
	}
	
	private Map<Month, Double> computeProfit(int year, Map<Month, Double> map) {
		//calculateSalaryExpense();
		this.monthlyExpenses.entrySet()
							.stream()
							.filter(x-> x.getKey().getX() == year ? true : false)
							.forEach(e-> map.merge(e.getKey().getY(), e.getValue(), (x, y)-> x - y));
		
		return map;
	}
	
	@Override
	public Map<Month, Double> getProfitByYear(int year) {
		
		Map<Month, Double> m = new HashMap<>();
		
		this.monthlyRevenue.entrySet()
							.stream()
							.filter(x-> x.getKey().getX() == year ? true : false)
							.forEach(e-> m.put(e.getKey().getY(), e.getValue()
										- DataImpl.getInstance().getEmployees()
										.stream()
										.filter(x-> x.getSigningContract().getYear() >= year ? true : false)
										.filter(x-> x.getSigningContract().getMonth().compareTo(e.getKey().getY()) <= 0 ? true : false)
										.map(x-> x.getSalary()).collect(Collectors.summingDouble(x->x))));
		
		return computeProfit(year, m);
	}
	
	@Override
	public Map<Month, Integer> getInscriptionsByYear(int year) {
		
		Map<Month, Integer> m = new HashMap<>();
		
		this.MonthlyInscriptions.entrySet()
								.stream()
								.filter(x-> x.getKey().getX() == year ? true : false)
								.forEach(e-> m.put(e.getKey().getY(), e.getValue()));
		
		
		return m;
		
	}
	
	














}
