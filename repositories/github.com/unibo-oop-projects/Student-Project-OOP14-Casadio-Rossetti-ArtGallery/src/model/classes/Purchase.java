package model.classes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import model.interfaces.IExhibit;
import model.interfaces.IPurchase;
import model.interfaces.ITicket;

/**
 * Purchase implementation.
 * 
 * @author Sofia Rosetti
 *
 */
public class Purchase implements IPurchase {
	
	private final Calendar date = GregorianCalendar.getInstance();
	private final IExhibit ex;
	private final int number;
	private final ITicket ticket;
	private double total;
	
	/**
	 * Constructor.
	 * @param newEx
	 *            the exhibit
	 * @param newNumber
	 *            the number of tickets
	 * @param newTicket
	 * 			  the type of the purchased ticket
	 */            
	public Purchase(final IExhibit newEx, final int newNumber, final ITicket newTicket) {
		this.ex = newEx;
		this.number = newNumber;
		this.ticket = newTicket;		
	}
	
	@Override
	public Calendar getDate() {
		return this.date;
	}
	
	@Override
	public IExhibit getExhibit() {
		return this.ex;
	}
	
	@Override
	public int getNumber() {
		return this.number;
	}
	
	@Override
	public ITicket getTicket() {
		return this.ticket;
	}
	
	@Override
	public double getTotal() {
		return this.total;
	}
	
	@Override
	public void setTotal() {
		this.total = this.ticket.getPrice() * this.number;
	}
	
	@Override
	public String toString() {
		return "Purchase[date of purchase=" + this.date + ", exhibit =" + this.ex 
				+ ", number of tickets =" + this.number + ", ticket =" + this.ticket + "]";
	}

}
