package view;



import controller.ProductController;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public interface ModifyProduct {

	/**
	 * this method use the pattern Observer
	 * 
	 * @param controller
	 */
	public void addObserver(ProductController controller);

	/**
	 * this method works with the components of JFrame
	 * 
	 * @param code
	 * @param name
	 * @param price
	 * @param quantity
	 * @param viewNameProduct
	 * @param viewQuantityProduct
	 * @param viewPriceProduct
	 */
	public void setData(int code, String name, int price, int quantity);

}
