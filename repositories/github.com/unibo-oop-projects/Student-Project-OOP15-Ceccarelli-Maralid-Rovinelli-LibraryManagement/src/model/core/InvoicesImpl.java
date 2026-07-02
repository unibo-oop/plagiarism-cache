package model.core;

import java.util.HashMap;
import java.util.Map;

import model.InvoiceModel;

/**
 * This class represents the invoices of the library list. This class is
 * composed of a list and the processing methods.
 * 
 * @author Mattia.Rovinelli
 *
 */
public class InvoicesImpl implements InvoicesModel {

	private Map<Integer, InvoiceModel> invoices = new HashMap<Integer, InvoiceModel>();

	public void updateInvoices(Map<Integer, InvoiceModel> invoices) {
		this.invoices = invoices;
	}

	public void addNewInvoice(InvoiceModel invoice) {
		this.invoices.put(this.invoices.size(), invoice);
	}

	public Map<Integer, InvoiceModel> getInvoices() {
		return this.invoices;
	}
}
