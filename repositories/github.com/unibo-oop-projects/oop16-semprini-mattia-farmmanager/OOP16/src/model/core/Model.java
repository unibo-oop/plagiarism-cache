package model.core;

import java.util.List;
import java.util.NoSuchElementException;

import model.Interfaces.CustomerModel;
import model.Interfaces.EmployeeModel;
import model.Interfaces.FieldModel;
import model.Interfaces.PhytosanitaryModel;
import model.Interfaces.PlantModel;
import model.Interfaces.SupplierModel;

public interface Model {

	/**
	 * this method is used to store a supplier
	 * @param name
	 * @param address
	 * @param telephone
	 * @throw IllegalArgumentException if there is already another supplier with same name
	 */
	public void addSupplier(final String name, final String address, final String telephone);
	
	/**
	 * this method is used to delete a supplier trough his name
	 * it uses {@link Model#getSupplier(String)}} to find the supplier
	 * @param supplier
	 * @throws NoSuchElementException if the name is not present
	 */

	public void deleteSupplier(final String supplier);
	
	/**
	 * this method is used to get a suppler through his name
	 * @param name
	 * @return the supplier
	 * @throws NoSuchElementException if the suppler is not present
	 */
	
	public SupplierModel getSupplier(final String name);
	
	/**
	 * this method is used to get stored suppliers
	 * @return stored suppliers
	 */
	
	public List<SupplierModel> getSuppliers();
	
	/**
	 * this method is used to store a field
	 * @param id
	 * @param height
	 * @param width
	 * @throw IllegalArgumentException if there is already another field with same id
	 */
	
	public void addField(final String id, final int height, final int width);
	
	/**
	 * this method is used to delete a supplier trough his id
	 * it uses {@link Model#getField(String))}} to find the field
	 * @param id
	 * @throws NoSuchElementException if the id is not present
	 */

	public void deleteField(final String id);
	
	/**
	 * this method is used to get a field through his id
	 * @param field
	 * @return the field
	 * @throws NoSuchElementException if the id is not present
	 */
	
	public FieldModel getField(final String field);

	/**
	 * this method is used to get stored fields
	 * @return stored fields
	 */
	
	public List<FieldModel> getFields();
	
	/**
	 * this method is used to store a new plant
	 * @param name
	 * @param description
	 * @param nOfDays
	 * @throws IllegalArgumentException if there is already another plant with same name
	 */

	public void addPlant(final String name, final String description, final int nOfDays);
	
	/**
	 * this method is used to delete a plant through his name -
	 * it uses {@link #getPlant(String)} to find the plant
	 * @param name
	 * @throws NoSuchElementException if the plant is not present
	 */

	public void deletePlant(final String name);
	
	/**
	 * this method is used to get a plant from his name
	 * @param name
	 * @return the plant
	 * @throws NoSuchElementException if the name is not present
	 */

	public PlantModel getPlant(final String name);
	
	/**
	 * this method return the list of stored plants
	 * @return stored plants
	 */
	
	public List<PlantModel> getPlants();
	
	/**
	 * this method is used to store a phytosanitary
	 * @param name
	 * @param type
	 * @param description
	 * @param days
	 * @throws IllegalArgumentException if there is already another phytosanitary with same name
	 */

	public void addPhytosanitary(final String name, final String type, final String description, final int days);
	
	/**
	 * this method is used to delete a phytosanitary from his name -
	 * it uses {@link #getPhytosanitary(String)} to find the phytosanitary
	 * @param name
	 * @throws NoSuchElementException if the name is not present
	 */

	public void deletePhytosanitary(final String name);
	
	/**
	 * this method is used to get a phytosanitary from his name	
	 * @param name
	 * @return the phytosanitary
	 * @throws NoSuchElementException if the name is not present
	 */
	
	public PhytosanitaryModel getPhytosanitary(final String name);
	
	/**
	 * this method is used to get the list of the phytosanitary
	 * @return stored phytosanitary
	 */

	public List<PhytosanitaryModel> getPhytosanitaryes();
	
	/**
	 * this method is used to store a customer
	 * @param name
	 * @param address
	 * @param telephone
	 * @throws IllegalArgumentException if the name is already used
	 */

	public void addCustomer(final String name, final String address, final String telephone);
	
	/**
	 * this method is used to delete a customer from his name
	 * - it uses {@link #getCustomer(String)} to find the customer
	 * @param name
	 * @throws NoSuchElementException if the customer is not present
	 */

	public void deleteCustomer(final String name);
	
	/**
	 * this method is used to get the customer from his name
	 * @param name
	 * @return the customer
	 * @throws NoSuchElementException if the name is not present
	 */
	
	public CustomerModel getCustomer(final String name);
	
	/**
	 * this method is used to get the list of customers
	 * @return the list
	 */

	public List<CustomerModel> getCustomers();
	
	/**
	 * this mehtod is used to store a new employee
	 * @param name
	 * @param surname
	 * @param telephone
	 * @param cf
	 * @throws IllegalArgumentException if cf is already used
	 */

	public void addEmployee(final String name, final String surname, final String telephone, final String cf);
	
	/**
	 * this method is used to delete an employee from his cf
	 * - it uses {@link #getCustomer(String)} to get the employee
	 * @param cf
	 * @throws NoSuchElementException if the employee is not present
	 */

	public void deleteEmployee(final String cf);
	
	/**
	 * this method is used to get an employee from his cf
	 * @param cf
	 * @return the employee
	 * @throws NoSuchElementException if the employee is not present
	 */
	
	public EmployeeModel getEmployee(final String cf);
	
	/**
	 * this method is used to get the list of employees
	 * @return stored employeee
	 */

	public List<EmployeeModel> getEmployees();
}
