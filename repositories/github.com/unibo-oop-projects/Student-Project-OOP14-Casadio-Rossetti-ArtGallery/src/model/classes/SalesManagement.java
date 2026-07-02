package model.classes;

import java.io.Serializable;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import model.interfaces.IExhibit;
import model.interfaces.ISalesManagement;

/**
 * Sales management implementation.
 * @author Sofia Rosetti
 *
 */

public class SalesManagement implements ISalesManagement, Serializable {

	private static final long serialVersionUID = -5661999114198662822L;
	private static final int START_YEAR = 1900;
	private static final int TICKETS = 500;
	private Calendar lastPurchaseDate = Calendar.getInstance();
	private Map<IExhibit, ExhibitData> data;
	
	/**
	 * Constructor.            
	 */
	public SalesManagement() {
		super();
		this.data = new HashMap<>();
		this.lastPurchaseDate.set(Calendar.YEAR, START_YEAR);
	}
	
	@Override
	public void addExhibit(final IExhibit ex) {
		this.data.put(ex, new ExhibitData(TICKETS, -ex.getCostExhibit()));
	}
	
	@Override
	public void resetTickets() {
		final Iterator<IExhibit> it = this.data.keySet().iterator();
		while (it.hasNext()) {
			this.data.get(it.next()).setAvailability(TICKETS);
		}
	}
	
	@Override
	public Map<IExhibit, ExhibitData> getData() {
		return this.data;
	}
	
	@Override
	public double getIncome(final Exhibit ex) {
		if (this.isExPresent(ex)) {
			return this.data.get(ex).getIncome();
		} else {
			throw new NoSuchElementException();
		}
	}
	
	@Override
	public void setData(final Map<IExhibit, ExhibitData> m) {
		this.data =  m;
	}
	
	@Override
	public double purchase(final IExhibit ex, final double percentage, final int number, 
			final double price) {
		Purchase p;
		if (percentage == 0) {
			p = new Purchase(ex, number, new Ticket(ex.getTitleExhibit(), price));
		} else {
			p = new Purchase(ex, number, new ReducedTicket(ex.getTitleExhibit(), price, percentage));
		}
		p.setTotal();
		if (this.isExPresent(ex)) {		
			this.data.get(ex).setIncome(this.data.get(ex).getIncome() + p.getTotal());
			this.data.get(ex).setAvailability(this.data.get(ex).getAvailability() - number);
		} else {
			throw new NoSuchElementException();
		}
		this.lastPurchaseDate = p.getDate();
		return p.getTotal();
	}
	
	@Override
	public int getAvailableTickets(final IExhibit ex) {
		if (this.isExPresent(ex)) {
			return this.data.get(ex).getAvailability();
		} else {
			throw new NoSuchElementException();
		}
	}
	
	@Override
	public boolean isExPresent(final IExhibit ex) {
		return this.data.containsKey(ex);
	}
	
	@Override
	public Calendar getLastDate() {
		return this.lastPurchaseDate;
	}

	@Override
	public String toString() {
		return "Purchases data: " + this.data.toString();
		
	}

}
