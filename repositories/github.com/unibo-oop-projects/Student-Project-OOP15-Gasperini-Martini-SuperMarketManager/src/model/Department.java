package model;

import java.util.ArrayList;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public interface Department {

	/**
	 * this method set a new name
	 * 
	 * @param name
	 */
	public void setName(String name);

	/**
	 * this method set a new maxProductDepartment
	 * 
	 * @param maxProductDepartment
	 */
	public void setMaxPriductDepartment(int maxProductDepartment);

	/**
	 * this method return the code of department
	 * 
	 * @return
	 */
	public int getCodeDepartment();

	/**
	 * this method return the maxproducts of Deparment
	 * 
	 * @return
	 */
	public int getMaxProductDepartment();

	/**
	 * this method return the name of Department
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * this method return the amount of department
	 * 
	 * @return
	 */
	public int getdepartmentAmount();

	/**
	 * this method return the list of product in the Department
	 * 
	 * @return
	 */
	public int getListProductSize();

	/**
	 * this method insert a new product in the Department
	 * 
	 * @param product
	 */
	public void insertProduct(Product product);

	/**
	 * this method return the list of product of Department
	 * 
	 * @return
	 */
	public ArrayList<Product> getListProduct();
	
	/**
	 * this method  delete a product
	 * 
	 * @return
	 */
	public void deleteProduct(Product product);
	
	/**
	 * this method return the total quantity of product in the department
	 * 
	 * @return
	 */
	public int quantityTotal();
}
