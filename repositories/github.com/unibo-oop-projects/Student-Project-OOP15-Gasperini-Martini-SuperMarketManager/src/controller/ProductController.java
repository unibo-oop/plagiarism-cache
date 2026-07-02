package controller;

import view.ModifyProductImpl;

import view.ManagementProductViewImpl;

import model.Department;

import model.Product;
import model.SuperMarket;


/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public interface ProductController {

	/**
	 * this method return the supermarket
	 * 
	 * @return
	 */
	SuperMarket getMarket();

	/**
	 * this method check the product after insert
	 * 
	 * @param department
	 * @param name
	 * @param code
	 * @param index
	 * @param price
	 * @param quantity
	 * 
	 * @return String(Department department, String name, int code, int
	 *         index, int price, int quantity)
	 */
	String insertProduct(Department department, String name, int code,
			int index, int price, int quantity);

	/**
	 * this method create the ModifyProductView
	 * 
	 * @param product
	 * @param view
	 * @param viewNameProduct
	 * @param viewQuantityProduct
	 * @param viewPriceProduct
	 */
	public void modifyController(Product product, ManagementProductViewImpl view);

	/**
	 * this method check the modify of product
	 * 
	 * @param code
	 * @param name
	 * @param price
	 * @param quantity
	 * @param viewNameProduct
	 * @param viewQuantityProduct
	 * @param viewPriceProduct
	 * 
	 * @return String(int code, String name, int price, int quantity, JLabel
	 *         viewNameProduct, JLabel viewQuantityProduct, JLabel
	 *         viewPriceProduct)
	 */
	public String changeProduct(int code, String name, int price, int quantity);

	/**
	 * this method check the quantity of department
	 * 
	 * @param department
	 * @param quantity
	 * 
	 * @return boolean((Department department, int quantity)
	 */
	boolean checkQuantity(Department department, int quantity);

	/**
	 * this method check the name of product
	 * 
	 * @param name
	 * 
	 * @return boolean(String name)
	 */
	public boolean checkName(String name);

	/**
	 * this method check the code of product
	 * 
	 * @param code
	 * 
	 * @return boolean(int code)
	 */
	public boolean checkCode(int code);

	/**
	 * this method delete a product
	 * 
	 * @param product
	 * @param quantityProduct
	 */
	public void deleteProdcut(Product product);

	/**
	 * exit
	 */
	void quit();

	/**
	 * exit from modify
	 */
	void quitModify();

	/**
	 * exit from ModifyProductView
	 * 
	 * @param view
	 */
	public void quitModifyProduct(ModifyProductImpl view);
	

}
