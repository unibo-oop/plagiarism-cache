package model.Interfaces;

import java.util.Calendar;
import java.util.Set;


public interface InvoiceModel {
	
	/**
	 * this method is used to set the customer of this invoice
	 * @param customer
	 */
	
	public void setCustomer(CustomerModel customer);
	
	/**
	 * this method is used to set the data of this invoice
	 * 	
	 * @param data
	 */
	public void setData(Calendar data);
	
	/**
	 * this method is used to add a product, Map(K,V) where k is the cultivation, and v is a pair of weight (in kg) and price per kg
	 * @param invoice
	 */
	
	public void addProduct(CultivationModel cultivation, int weight, double price);
	
	/**
	 * this method is used to get a set of the products
	 * @return a set that contains all the products on the invoice
	 */
	
	public Set<CultivationModel> getProducts();
	
	/**
	 * with this method you can get the total about a single product
	 * @param product
	 * @return total about that specific product
	 */
	
	public Double getTotalProduct(CultivationModel product);
	
	/**
	 * this method is used to get the total of the invoice
	 * @return the total of the invoice
	 */
	
	public Double getTotal();
	
	/**
	 * this method is used to get the customer of this invoice
	 * @return the customer 
	 */
	
	public CustomerModel getCustomer();
	
	/**
	 * this method is used to get the data of this invoice	
	 * @return
	 */
	public Calendar getData();
	
	
	
}
