package model.enumerations;

/**
 *
 * Interface for subscription's type
 */
public enum SubscriptionType {
	SELECT_SUBSCRIPTION(0.0),
	DAILY(10),
	MONTHLY(300), 
	QUARTERLY(700), 
	SEMESTRAL(950), 
	ANNUAL(1200);
	
	private double price;
	
	public double getPrice() {
        return price;
    }
	
	private SubscriptionType(double price) {
		this.price = price;
	}
	
}
