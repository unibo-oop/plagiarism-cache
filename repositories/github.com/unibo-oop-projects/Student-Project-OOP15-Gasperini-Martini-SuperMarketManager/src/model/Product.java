package model;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public interface Product {

	/**
	 * this method return the name of product
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * this method return the code of product
	 * 
	 * @return
	 */
	public int getCodeProduct();

	/**
	 * this method return the quantity of product
	 * 
	 * @return
	 */
	public int getQuantity();

	/**
	 * this method return the price of product
	 * 
	 * @return
	 */
	public int getPrice();

	/**
	 * this method set a new name product
	 * 
	 * @param name
	 */
	public void setName(String name);

	/**
	 * this method set a new price product
	 * 
	 * @param price
	 */
	public void setPrice(int price);

	/**
	 * this method set a new quantity product
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity);

	/**
	 * this method set a new code product
	 * 
	 * @param code
	 */
	public void setCode(int code);

}
