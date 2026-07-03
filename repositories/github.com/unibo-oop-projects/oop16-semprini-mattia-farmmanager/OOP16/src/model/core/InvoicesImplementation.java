package model.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import model.Implementations.Pair;
import model.Interfaces.CustomerModel;
import model.Interfaces.InvoiceModel;

public class InvoicesImplementation implements InvoicesModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * una mappa dove integer sta per anno e l'arraylist è il "contenitore" dove
	 * ci sono tutte le fatture di quell'anno con i relativi numeri progressivi
	 */

	private Map<Integer, ArrayList<Pair<Integer, InvoiceModel>>> invoices = new HashMap<>();

	@Override
	public void addInvoice(InvoiceModel invoice) {
		if (this.invoices.containsKey(invoice.getData().get(Calendar.YEAR))) {
			ArrayList<Pair<Integer, InvoiceModel>> temp = new ArrayList<>(
					invoices.get(invoice.getData().get(Calendar.YEAR)));
			for (Pair<Integer, InvoiceModel> pair : temp) {
				if (pair.getY().equals(invoice)) {
					throw new IllegalArgumentException("this invoice already exists");
				}
			}
			temp.add(new Pair<Integer, InvoiceModel>(temp.size(), invoice));
			this.invoices.put(invoice.getData().get(Calendar.YEAR), new ArrayList<>(temp));
		} else {
			Pair<Integer, InvoiceModel> i = new Pair<Integer, InvoiceModel>(0, invoice);
			ArrayList<Pair<Integer, InvoiceModel>> array = new ArrayList<>();
			array.add(i);
			this.invoices.put(invoice.getData().get(Calendar.YEAR), array);
		}
	}

	@Override
	public List<Pair<Integer, InvoiceModel>> getInvoices(Calendar year) {
		if (this.invoices.containsKey(year.get(Calendar.YEAR)))
			return this.invoices.get(year.get(Calendar.YEAR));
		return Collections.emptyList();
	}

	/*
	 * questo metodo crea un iteratore che attraversa tutta la mappa per ogni
	 * anno prende la lista, la scorre e aggiunge alla lista di ritorno un pair
	 * che contiene il numero progressivo e la fattura, se alla fine dello
	 * scorrimento non è stato aggiunto nessun valore, ritorna una lista vuota
	 */
	@Override
	public List<Pair<Integer, InvoiceModel>> getInvoiceFromCustomer(CustomerModel customer) {
		List<Pair<Integer, InvoiceModel>> list = new ArrayList<>();
		Iterator<Entry<Integer, ArrayList<Pair<Integer, InvoiceModel>>>> it = this.invoices.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, ArrayList<Pair<Integer, InvoiceModel>>> element = it.next();
			ArrayList<Pair<Integer, InvoiceModel>> year = new ArrayList<>(element.getValue());
			for (Pair<Integer, InvoiceModel> invoice : year) {
				if (invoice.getY().getCustomer().getName().equals(customer.getName())) {
					list.add(invoice);
				}
			}
		}
		if (list.size() == 0)
			return Collections.emptyList();
		return list;
	}

	@Override
	public List<Pair<Integer, InvoiceModel>> getInvoice(CustomerModel customer, Calendar data) {
		List<Pair<Integer, InvoiceModel>> result = new ArrayList<>();
		if (this.invoices.containsKey(data.get(Calendar.YEAR))) {
			ArrayList<Pair<Integer, InvoiceModel>> list = this.invoices.get(data.get(Calendar.YEAR));
			for (Pair<Integer, InvoiceModel> i : list) {
				if (i.getY().getData() == data && i.getY().getCustomer().equals(customer)) {
					result.add(i);
				}
			}
		}
		return result;
	}

	@Override
	public Map<Integer, ArrayList<Pair<Integer, InvoiceModel>>> getInvoices() {
		return this.invoices;
	}

	@Override
	public void updateInvoices(Map<Integer, ArrayList<Pair<Integer, InvoiceModel>>> invoice) {
		this.invoices = invoice;
	}

	@Override
	public InvoiceModel getInvoice(CustomerModel customer, Calendar year, Integer invoiceNumber) {
		for (Pair<Integer, InvoiceModel> entry : this.getInvoice(customer, year)) {
			if (entry.getX() == invoiceNumber) {
				return entry.getY();
			}
		}
		throw new NoSuchElementException();
	}

}
