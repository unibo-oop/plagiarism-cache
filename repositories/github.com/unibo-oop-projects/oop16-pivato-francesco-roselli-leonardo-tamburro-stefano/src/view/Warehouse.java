package view;

import java.util.List;

import model.interfaces.*;

/**
 * 
 * @author Ste
 *
 */
public interface Warehouse {
	
	/**
	 * Set the table with all the products present in the database
	 * @param productList
	 */
	void populateTable(List<Product> productList);
	
	//String getSearchedString();

}
