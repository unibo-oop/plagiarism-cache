package model;

import model.core.EmployeesModel;
import model.core.InvoicesModel;
import model.core.ShopAndWarehouseModel;
import model.core.SubscriptionsModel;

public interface Model {
	/**
	 * this method return the class of shopImpl
	 * 
	 * @return
	 */
	public ShopAndWarehouseModel shop();

	/**
	 * this method return the class of warehouseImpl
	 * 
	 * @return
	 */
	public ShopAndWarehouseModel warehouse();

	/**
	 * this method return the class of employees
	 * 
	 * @return
	 */
	public EmployeesModel employees();

	/**
	 * this method return the class of Invoices
	 * 
	 * @return
	 */
	public InvoicesModel invoices();

	public SubscriptionsModel subscriptions();
}
