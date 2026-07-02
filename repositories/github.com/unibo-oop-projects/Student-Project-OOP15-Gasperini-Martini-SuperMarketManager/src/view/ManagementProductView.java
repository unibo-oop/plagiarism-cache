package view;

import java.util.ArrayList;

import javax.swing.JButton;


import controller.ProductController;


import model.Product;


/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public interface ManagementProductView {

	/**
	 * this method use the pattern Observer
	 * 
	 * @param controller
	 */
	public void addObserver(ProductController controller);

	/**
	 * this method give the list of products in the department
	 * 
	 * @param product
	 * @param department
	 */
	public void listProduct(ArrayList<Product> product);

	/**
	 * this method works with the component of JFrame
	 * 
	 * @param deleteProduct
	 * @param product
	 * @param modifyDepartment
	 * @param nameProduct
	 * @param view
	 * @param quantityProduct
	 * @param priceProduct
	 */
	public void actionEvent(JButton deleteProduct, Product product, JButton modifyDepartment,
			ManagementProductViewImpl view);
	
	
	
	/**
	 * 
	 * this method set the displey of the view
	 * @param products
	 */
	public void setPanel(ArrayList<Product> products);

}
