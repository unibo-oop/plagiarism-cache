package view;

import model.interfaces.Product;

public interface ProductDetails {
	
	void setData(Product product);
	
	/**
	 * 
	 * @return the ID of the product
	 */
	String getId();
	
	/**
	 * 
	 * @return the name of the product
	 */
	String getName();
	
	/**
	 * 
	 * @return the category of the product
	 */
	String getCategory();
	
	/**
	 * 
	 * @return the quantity of this product
	 */
	int getQuantity();
	
	/**
	 * 
	 * @return the price
	 */
	double getPrice();
	
	/**
	 * 
	 * @return description of the product
	 */
	String getDescription();

}
