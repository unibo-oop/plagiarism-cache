package model.core;

import java.util.Map;
import model.InvoiceModel;

public interface InvoicesModel {
	/**
	 * this method update the map of invoice
	 * 
	 * @param invoices
	 */
	public void updateInvoices(Map<Integer, InvoiceModel> invoices);

	/**
	 * this method add a new invoice in the map of invoices
	 * 
	 * @param invoice
	 */
	public void addNewInvoice(InvoiceModel invoice);

	/**
	 * this method return the map of invoices
	 * 
	 * @return
	 */
	public Map<Integer, InvoiceModel> getInvoices();

}
