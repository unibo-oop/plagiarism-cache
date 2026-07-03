package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.interfaces.*;

public class DataImpl implements Serializable, Data {

	private static final long serialVersionUID = -5073695034309766688L;
	private final static  DataImpl SINGLETON = new DataImpl();

	private List<User> users = new ArrayList<>();

	private Map<String, String> loginData = new HashMap<>();
	
	private Map<Product, Integer> productInWharehouse = new HashMap<>();

	private List<Employee> employees = new ArrayList<>();

	private Map<LocalDate, List<Product>> productsSold = new HashMap<>();
	
	private BalanceImpl balance = BalanceImpl.getInstance();
	
	private DataImpl() {
		
	}
	
	public static DataImpl getInstance() {
		return SINGLETON;
	}
	
	public List<User> getUsersList() {
		return this.users;
	}
	
	public void addToUserList(User user) {
		this.users.add(user);
	}
	
	public void addToLoginData(Map<String, String> map) {
		this.loginData.putAll(map);
	}
	
	public Map<String, String> getLoginData() {
		return this.loginData;
	}
	
	public List<Product> getProductList() {
		return this.productInWharehouse.keySet().stream().collect(Collectors.toList());
	}
	
	public Map<Product, Integer> getProductInWharehouse() {
		return this.productInWharehouse;
	}
	
	@Override
	public Map<LocalDate, List<Product>> getProductSold() {
		return this.productsSold;
	}
	
	public BalanceImpl getBalance() {
		return this.balance;
	}
	
	public List<Employee> getEmployees(){
		return this.employees;
	}
	
	private List<Product> getOrPrepare(Product p) {
		List<Product> l = new ArrayList<>();
		l.add(p);
		return l;
	}
	
	protected boolean checkAvailability(Product product, int quantity) {
		if(this.productInWharehouse.get(product) - quantity >= 0) {
			return true;
		}
		return false;
	}
	
	protected void putInSoldProducts(LocalDate date, Product p) {
		if(!this.productsSold.containsKey(date)) {
			this.productsSold.put(date, getOrPrepare(p));
		}
		else {
			this.productsSold.get(date).addAll(getOrPrepare(p));
		}
	}

}
