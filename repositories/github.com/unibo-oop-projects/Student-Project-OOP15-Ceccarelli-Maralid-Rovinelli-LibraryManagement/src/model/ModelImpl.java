package model;

import model.core.EmployeesImpl;
import model.core.EmployeesModel;
import model.core.InvoicesImpl;
import model.core.InvoicesModel;
import model.core.ShopAndWarehouseModel;
import model.core.ShopImpl;
import model.core.SubscriptionsImpl;
import model.core.SubscriptionsModel;
import model.core.WarehouseImpl;

/**
 * This class represents the core of the model. In this class they contain links
 * to maps of objects.
 * 
 * @author Mattia.Rovinelli
 *
 */
public class ModelImpl implements Model {

	private ShopAndWarehouseModel shop;
	private ShopAndWarehouseModel warehouse;
	private EmployeesModel employees;
	private InvoicesModel invoices;
	private SubscriptionsModel subscriptions;

	public ModelImpl() {
		shop = new ShopImpl();
		warehouse = new WarehouseImpl();
		employees = new EmployeesImpl();
		invoices = new InvoicesImpl();
		subscriptions = new SubscriptionsImpl();
	}

	public ShopAndWarehouseModel shop() {
		return this.shop;
	}

	public ShopAndWarehouseModel warehouse() {
		return this.warehouse;
	}

	public EmployeesModel employees() {
		return this.employees;
	}

	public InvoicesModel invoices() {
		return this.invoices;
	}

	public SubscriptionsModel subscriptions() {
		return this.subscriptions;
	}

}
