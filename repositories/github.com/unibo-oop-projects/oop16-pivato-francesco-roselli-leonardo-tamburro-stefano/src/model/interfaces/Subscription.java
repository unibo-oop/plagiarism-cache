package model.interfaces;

import java.time.LocalDate;

import model.enumerations.*;

/**
 * Interface for Subscription's user
 *
 */

public interface Subscription {

	/**
	 * @return subscription's code
	 */
	
	int getCode();
	
	/**
	 * @return subscription's type
	 */
	
	SubscriptionType getType();
	
	/**
	 * @return subscription signing's date
	 */
	
	LocalDate getSigningDate();
	
	/**
	 * @return subscription deadline's date
	 */
	
	LocalDate getExpirationDate();
	
	/**
	 * set the payment date
	 */
	
	void setPaymentDate(LocalDate date);
	
	/**
	 * @return the prize of the subscription
	 */
	
	public double getPrice();
	
	/**
	 * @return true if the subscription is active, false otherwise
	 */
	
	boolean isActive();

	
	/**
	 * @return the paymentDate
	 */
	
	LocalDate getPaymentDate();
	
}
