package model.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import model.Implementations.CustomerImplementation;
import model.Implementations.EmployeeImplementation;
import model.Implementations.FieldImplementation;
import model.Implementations.PhytosanitaryImplementation;
import model.Implementations.PlantImplementation;
import model.Implementations.SupplierImplementation;
import model.Interfaces.CustomerModel;
import model.Interfaces.EmployeeModel;
import model.Interfaces.FieldModel;
import model.Interfaces.PhytosanitaryModel;
import model.Interfaces.PlantModel;
import model.Interfaces.SupplierModel;

public class ModelImplementation implements Model, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4141440384003096361L;
	private List<SupplierModel> suppliers= new ArrayList<>();;
	private List<FieldModel> fields = new ArrayList<>();;
	private List<PlantModel> plants = new ArrayList<>();;
	private List<PhytosanitaryModel> phytosanitary = new ArrayList<>();;
	private List<CustomerModel> customers = new ArrayList<>();;
	private List<EmployeeModel> employees = new ArrayList<>();;

	@Override
	public void addSupplier(String name, String address, String telephone) {
		for (SupplierModel s : this.suppliers) {
			if (s.getName().equals(name)) {
				throw new IllegalArgumentException("There is already another supplier with this name");
			}
		}
		SupplierModel supplier = new SupplierImplementation(name, address, telephone);
		this.suppliers.add(supplier);
	}

	@Override
	public void deleteSupplier(String supplier) {
		this.suppliers.remove(this.getSupplier(supplier));
	}

	@Override
	public List<SupplierModel> getSuppliers() {
		return this.suppliers;
	}
	
	@Override
	public SupplierModel getSupplier(String name) {
		for(SupplierModel s : this.suppliers){
			if(s.getName().equals(name)){
				return s;
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public void addField(String id, int height, int width) {
		for (FieldModel f : this.fields) {
			if (f.getFieldID().equals(id)) {
				throw new IllegalArgumentException("there is another field with this name");
			}
		}
		FieldModel field = new FieldImplementation(id, height, width);
		this.fields.add(field);
	}

	@Override
	public void deleteField(final String id) {
		this.fields.remove(this.getField(id));
	}

	@Override
	public List<FieldModel> getFields() {
		return this.fields;
	}

	@Override
	public FieldModel getField(String field) {
		for (FieldModel f : this.fields){
			if (f.getFieldID().equals(field)){
				return f;
			}
		}
		throw new NoSuchElementException();
	}
	
	@Override
	public void addPlant(String name, String description, int nOfDays) {
		for (PlantModel p : this.plants) {
			if (p.getName().equals(name)) {
				throw new IllegalArgumentException("There is already another supplier with this name");
			}
		}
		PlantModel plant = new PlantImplementation(name, description, nOfDays);
		this.plants.add(plant);
	}

	@Override
	public void deletePlant(final String name) {
		this.plants.remove(this.getPlant(name));
	}

	@Override
	public List<PlantModel> getPlants() {
		return this.plants;
	}
	
	@Override
	public PlantModel getPlant(String name) {
		for(PlantModel p : this.plants){
			if(p.getName().equals(name)){
				return p;
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public void addPhytosanitary(String name, String type, String description, int days) {
		for (PhytosanitaryModel p : this.phytosanitary) {
			if (p.getName() == name) {
				throw new IllegalArgumentException("There is already another phytosanitary with that name");
			}
		}
		PhytosanitaryModel phytosanitary = new PhytosanitaryImplementation(name, type, description, days);
		this.phytosanitary.add(phytosanitary);
	}

	@Override
	public void deletePhytosanitary(final String name) {
		this.phytosanitary.remove(this.getPhytosanitary(name));
	}

	@Override
	public List<PhytosanitaryModel> getPhytosanitaryes() {
		return this.phytosanitary;
	}

	@Override
	public PhytosanitaryModel getPhytosanitary(String name) {
		for(PhytosanitaryModel p : this.phytosanitary){
			if(p.getName().equals(name)){
				return p;
			}
		}
		throw new NoSuchElementException();
	}
	
	@Override
	public void addCustomer(String name, String address, String telephone) {
		for (CustomerModel c : this.customers) {
			if (c.getName().equals(name)) {
				throw new IllegalArgumentException("there is already another customer with that name");
			}
		}
		this.customers.add(new CustomerImplementation(name, address, telephone));
	}

	@Override
	public void deleteCustomer(final String name) {
		this.customers.remove(this.getCustomer(name));
	}

	@Override
	public List<CustomerModel> getCustomers() {
		return this.customers;
	}

	@Override
	public CustomerModel getCustomer(String name) {
		for(CustomerModel c : this.customers){
			if (c.getName().equals(name)){
				return c;
			}
		}
		throw new NoSuchElementException();
	}
	
	@Override
	public void addEmployee(String name, String surname, String telephone, String cf) {
		for (EmployeeModel e : this.employees) {
			if (e.getCF() == cf) {
				throw new IllegalArgumentException("there is already another customer with that id");
			}
		}
		this.employees.add(new EmployeeImplementation(name, surname, telephone, cf));
	}

	@Override
	public void deleteEmployee(final String cf) {
		this.employees.remove(this.getEmployee(cf));
	}

	@Override
	public List<EmployeeModel> getEmployees() {
		return this.employees;
	}

	@Override
	public EmployeeModel getEmployee(String cf) {
		for(EmployeeModel e : this.employees){
			if (e.getCF().equals(cf)){
				return e;
			}
		}
		throw new NoSuchElementException();
	}


}
