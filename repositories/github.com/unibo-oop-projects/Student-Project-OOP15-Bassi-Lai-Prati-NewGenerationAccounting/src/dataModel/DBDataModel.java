/**
 * 
 */
package dataModel;

import java.util.LinkedList;

/**
 * il database che conterr� tutte le informazioni del programma.
 * 
 * @author Pentolo
 *
 */
public class DBDataModel {

	private LinkedList<Account> accounts;
	private boolean accountsModified = false;
	private LinkedList<Customers_Suppliers> customersSuppliers;
	private boolean customersSuppliersModified = false;
	private LinkedList<Movement> moviments;
	private boolean movimentsModified = false;
	private LinkedList<Product> products;
	private boolean productsModified = false;
	private String path;

	public DBDataModel(final LinkedList<Account> accounts, final LinkedList<Customers_Suppliers> customersSuppliers,
			final LinkedList<Movement> moviments, final LinkedList<Product> products, final String path) {
		super();
		this.path = path;
		this.accounts = accounts;
		this.customersSuppliers = customersSuppliers;
		this.moviments = moviments;
		this.products = products;
	}

	public DBDataModel(final String path) {
		this.path = path;
	}

	/**
	 * @return the accounts
	 */
	public LinkedList<Account> getAccounts() {
		return new LinkedList<Account>(accounts);
	}

	/**
	 * @return the customersSuppliers
	 */
	public LinkedList<Customers_Suppliers> getCustomersSuppliers() {
		return new LinkedList<Customers_Suppliers>(customersSuppliers);
	}

	/**
	 * @return the moviments
	 */
	public LinkedList<Movement> getMoviments() {
		return new LinkedList<Movement>(moviments);
	}

	/**
	 * @return the path
	 */
	public final String getPath() {
		return path;
	}

	/**
	 * @return the products
	 */
	public LinkedList<Product> getProducts() {
		return new LinkedList<Product>(products);
	}

	/**
	 * @return the isAccountsModified
	 */
	public final boolean isAccountsModified() {
		return accountsModified;
	}

	/**
	 * @return the isCustomersSuppliersModified
	 */
	public final boolean isCustomersSuppliersModified() {
		return customersSuppliersModified;
	}

	/**
	 * @return the isMovimentsModified
	 */
	public final boolean isMovimentsModified() {
		return movimentsModified;
	}

	/**
	 * @return the isProductsModified
	 */
	public final boolean isProductsModified() {
		return productsModified;
	}

	/**
	 * reset all the booleans of modified
	 */
	public void resetBooleans() {
		accountsModified = false;
		customersSuppliersModified = false;
		movimentsModified = false;
		productsModified = false;
	}

	/**
	 * @param accounts
	 *            the accounts to set
	 */
	public void setAccounts(LinkedList<Account> accounts) {
		this.accounts = accounts;
		accountsModified = true;
	}

	/**
	 * @param customersSuppliers
	 *            the customersSuppliers to set
	 */
	public void setCustomersSuppliers(LinkedList<Customers_Suppliers> customersSuppliers) {
		this.customersSuppliers = customersSuppliers;
		customersSuppliersModified = true;
	}

	/**
	 * @param moviments
	 *            the moviments to set
	 */
	public void setMoviments(LinkedList<Movement> moviments) {
		this.moviments = moviments;
		movimentsModified = true;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(LinkedList<Product> products) {
		this.products = products;
		productsModified = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DBDataModel [accounts=" + accounts + ", customersSuppliers=" + customersSuppliers + ", moviments="
				+ moviments + ", products=" + products + ", path=" + path + "]";
	}
}
