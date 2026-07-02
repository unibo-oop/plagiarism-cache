package model.classes;
import model.interfaces.ITicket;

/**
 * Ticket implementation.
 * @author Sofia Rosetti
 *
 */
public class Ticket implements ITicket {
	
	private final String expName;
	private final double price;	
	
	/**
	 * Constructor.
	 * @param name
	 *            the exposition name
	 * @param newPrice
	 *            the ticket price                      
	 */
	public Ticket(final String name, final double newPrice) {
		super();
		this.expName = name;
		this.price = newPrice;
	}
	
	@Override
	public String getExpName() {
		return this.expName;
	}
	
	@Override
	public double getPrice() {
		return this.price;
	}
	
	@Override
	public String toString() {
		return "Ticket[exposition name=" + this.expName
				+ ", ticket price=" + this.price + "]";
	}

}
