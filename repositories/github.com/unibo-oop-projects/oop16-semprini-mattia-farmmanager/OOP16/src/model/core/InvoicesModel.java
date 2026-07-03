package model.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import model.Implementations.Pair;
import model.Interfaces.CustomerModel;
import model.Interfaces.InvoiceModel;

public interface InvoicesModel {
	
	/**
	 * this method is used to store an invoice
	 * @param invoice
	 */
	public void addInvoice(InvoiceModel invoice);	
	/**
	 * this method is used to get stored invoices
	 * @return
	 */	
	public Map<Integer, ArrayList<Pair<Integer, InvoiceModel>>> getInvoices();
	
	/**
	 * this mehtod is used to replace stored invoice with given ones
	 * @param invoice
	 */
	
	public void updateInvoices(Map<Integer, ArrayList<Pair<Integer, InvoiceModel>>> invoice);
	
	/**
	 * this method is used to get stored invoice of a specific year
	 * @param year
	 * @return
	 */
	
	public List<Pair<Integer, InvoiceModel>> getInvoices(Calendar year);
	
	/**
	 * this mehtod is used to get stored invoices of a specific customer no year restricted
	 * @param customer
	 * @return
	 */
	
	public List<Pair<Integer, InvoiceModel>> getInvoiceFromCustomer(CustomerModel customer);
	/**
	 * this method is used to get stored invoice of a specific customer in a specific year
	 * @param customer
	 * @param data
	 * @returna list that contains
	 */
	
	public List<Pair<Integer, InvoiceModel>> getInvoice(CustomerModel customer, Calendar data);
	
	/**
	 * this method is used to get a specific invoice through progressive number
	 * @param customer
	 * @param year
	 * @param invoiceNumber
	 * @return
	 */
	
	public InvoiceModel getInvoice(CustomerModel customer, Calendar year, Integer invoiceNumber);
	
}
