package model.interfaces;

public interface Product {
	
	/**
	 * @return the product's code
	 */
	
	public String getProductCode();

	/**
	 * @return the product's name
	 */
	
	public String getName();

	/**
	 * @return the product's description
	 */
	
	public String getDescription();

	/**
	 * @return the product's category
	 */
	
	public String getCategory();
	
	/**
	 * @return the product's price
	 */

	public double getPrice();
	
	/**
	 * @return the product's quantity
	 */
	
	public int getQuantity();
	
	/**
	 * @param The quantity of the product
	 */
	
	public void setQuantity(int quantity);

}
