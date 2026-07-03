package model.Implementations;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import model.Interfaces.CultivationModel;
import model.Interfaces.CustomerModel;
import model.Interfaces.InvoiceModel;

public class InvoiceImplementation implements InvoiceModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3600931974323911259L;
	private CustomerModel customer;
	private Calendar data;
	private Map<CultivationModel, Pair<Integer, Double>> products = new HashMap<>();

	public InvoiceImplementation(CustomerModel customer, Calendar data, Map<CultivationModel, Pair<Integer, Double>> list) {
		this.customer = customer;
		this.data = data;getClass();
		this.products = list;
	}

	@Override
	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	@Override
	public void setData(Calendar data) {
		this.data = data;
	}

	@Override
	public void addProduct(CultivationModel cultivation, int weight, double price) {
		this.products.put(cultivation, new Pair<Integer, Double>(weight, price));

	}

	@Override
	public Set<CultivationModel> getProducts() {
		return this.products.keySet();
	}

	@Override
	public Double getTotalProduct(CultivationModel product) {
		Double result = this.products.get(product).getY() * this.products.get(product).getX();
		return result;
	}

	@Override
	public Double getTotal() {
		/*
		 * Double total = new Double(0); Iterator<Entry<CultivationModel,
		 * Pair<Integer, Double>>> it = this.products.entrySet().iterator();
		 * while (it.hasNext()){ Map.Entry<CultivationModel, Pair<Integer,
		 * Double>> product = (Entry<CultivationModel, Pair<Integer, Double>>)
		 * it.next(); total+= getTotalProduct(product.getKey()); } return null;
		 */
		Double total = new Double(0);
		Set<CultivationModel> set = this.products.keySet();
		for (CultivationModel c : set) {
			total += getTotalProduct(c);
		}
		return total;
	}

	@Override
	public CustomerModel getCustomer() {
		return this.customer;
	}

	@Override
	public Calendar getData() {
		return this.data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvoiceImplementation other = (InvoiceImplementation) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "InvoiceImplementation [customer=" + customer + ", products=" + products + "]";
	}

}
