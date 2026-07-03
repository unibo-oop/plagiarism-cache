package controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import model.Implementations.Pair;
import model.Implementations.State;
import model.Interfaces.CultivationModel;
import model.Interfaces.InvoiceModel;
import model.Interfaces.ShiftModel;
import model.Interfaces.SupplyModel;
import model.core.Model;

public interface ControllerModel {
	
	/**
	 * this method is used to save all the information contained on controller
	 */
	public void saveData();
	
	/**
	 * this method is used to load from file the informations
	 */
	
	public void loadData();
	
	/**
	 * this method is used to get the cultivations
	 * @return the list that contains the cultivations
	 */
	
	public List<CultivationModel> getCultivations();
	
	/**
	 * this method is used to get the list of the invoices of an specific year
	 * @param data
	 * @return the list that contains the invoices
	 */
	
	public List<Pair<Integer, InvoiceModel>> getInvoices(Calendar data);
	
	/**
	 * this method is used to get a list of the shifts of a specific employee
	 * @param CF
	 * @return
	 */
	
	public List<ShiftModel> getShifts(String CF);
	
	/**
	 * this mehtod is used to get the list of the supplies
	 * @return
	 */
	
	public List<SupplyModel> getSupplies();
	
	/**
	 * this method is used to get the salary of an employee of a specific month
	 * @param cf
	 * @param month
	 * @return the salary
	 */
	
	public Double getSalary(String cf,Calendar month, Double pricePerH);
	
	/**
	 * this method is used to get the model which contains all the basic information
	 * like the list of the employee, plants,suppliers, field ecc
	 * @return model
	 */
	
	public Model getModel();
	
	/**
	 * this method is used to add a cultivation in the database
	 * @param startDate
	 * @param nOfPlants
	 * @param supply
	 * @param fieldID
	 * @param annotation
	 * @param state
	 */
	
	public void addCultivation(Calendar startDate, int nOfPlants, String supply, String fieldID,
			String annotation, State state);
	/**
	 * this method is used to add an invoice in the database
	 * @param cliente
	 * @param data
	 * @param products
	 */
	
	public void addInvoice(String cliente, Calendar data, Map<CultivationModel, Pair<Integer, Double>> products );
	
	/**
	 * this method is used to add a supply in the database
	 * @param supplier
	 * @param plant
	 * @param fornitureID
	 * @param description
	 * @param nPlants
	 */
	
	public void addSupply(String supplier, String plant, String fornitureID, String description,
			int nPlants);
	
	/**
	 * this method is used to add a shift in the database
	 * @param data
	 * @param start
	 * @param end
	 * @param description
	 * @param employeeCF
	 */
	
	public void addShift(Calendar data, Calendar start, Calendar end, String description,String employeeCF);
	
	/**
	 * this method is used to add a treatment on a cultivation
	 * @param supply
	 * @param FieldID
	 * @param phytosanitary
	 * @param date
	 * @param Description
	 * @throws Exception 
	 */
	
	public void addTreatment(String supply, String FieldID, String phytosanitary, Calendar date, String Description) throws Exception;
	
	/**
	 * this method is used to add in the model an object.
	 * IMPORTANT!!! order of the given param MUST be te same of the costructor of that particoular element!!!
	 * @param param
	 * @param objects
	 */
	
	public void addToModel(ModelParam param,Object... objects);
	
	/**
	 * this method is used to get total minutes of a specific month concerning a specific employee
	 * @param cf
	 * @param month
	 * @return the minutes as integer
	 */
	public Integer getSalaryMinutes(String cf, Calendar month);
	
	public InvoiceModel getInvoice(String customer, Calendar year, Integer progressiveNumber);
	
	public List<Pair<Integer, InvoiceModel>> getInvoice(String customer);
	
}
