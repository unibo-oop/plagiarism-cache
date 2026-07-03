package model.interfaces;
import java.util.Map;

import model.enumerations.*;

/**
 * 
 * Informations about User
 */

public interface User {
	
	/**
	 * give the specific data of user
	 * @return a Person
	 */
	
	Person getPerson();
	
	/**
	 * give the user's subscription
	 * @return a Subscription
	 */
	
	Subscription getSubscription();
	
	/**
	 * @return the workout of the user
	 */
	
	public Workout getWorkout();
	
	/**
	 * @return a list containing the products bought by the user
	 */
	
	public Map<Product,Integer> getProductsBought();
	
	/**
	 * @param product to buy
	 * @param quantity of the product
	 * @return 
	 */
	
	public Status buyProduct(Product product, Integer quantity);
	
}
