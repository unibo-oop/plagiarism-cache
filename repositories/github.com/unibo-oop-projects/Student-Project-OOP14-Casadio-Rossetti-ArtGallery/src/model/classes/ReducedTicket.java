package model.classes;

/**
 * This class models a reduced ticket with a discount percentage.
 * @author Sofia Rosetti
 *
 */
public class ReducedTicket extends Ticket {
	
	private final double percentage;
	
	/**
	 * Constructor. 
	 * @param name
	 *            the exposition name
	 * @param price
	 *            the ticket price              
	 * @param newPercentage
	 *            the discount percentage                      
	 */
	public ReducedTicket(final String name, final double price, final double newPercentage) {
		super(name, price);
		this.percentage = newPercentage;
	}
	
	/**
	 * This method returns the discount percentage of the ticket. 
	 * 
	 * @return the discount percentage
	 */
	public double getDiscountPercentage() {
		return this.percentage;
	}
	
	/**
	 * This method allows to get the discount import of the ticket.
	 * 
	 * @return the discount import
	 */
	public double getDiscount() {
		return super.getPrice() * this.percentage / 100;
	}
	
	@Override
	public double getPrice() {
		return super.getPrice() - this.getDiscount();
	} 

}
