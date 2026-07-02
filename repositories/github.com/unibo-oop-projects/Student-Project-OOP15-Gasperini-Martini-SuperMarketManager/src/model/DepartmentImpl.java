package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public class DepartmentImpl implements Department, Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private int codeDepartment;
	private int maxProductDepartment;
	private int departmentAmount;
	private ArrayList<Product> listProduct;

	public DepartmentImpl(String name, int maxProductDepartment, int codeDepartment) {

		this.name = name;
		this.codeDepartment = codeDepartment;
		this.maxProductDepartment = maxProductDepartment;
		this.listProduct = new ArrayList<Product>();

	}

	public void setName(String name) {

		this.name = name;

	}

	public void setMaxPriductDepartment(int maxProductDepartment) {

		this.maxProductDepartment = maxProductDepartment;
	}

	public void setCode(int code) {

		this.codeDepartment = code;
	}

	public int getCodeDepartment() {

		return this.codeDepartment;

	}

	public int getMaxProductDepartment() {

		return this.maxProductDepartment;
	}

	public String getName() {

		return this.name;
	}

	public int getdepartmentAmount() {

		return this.departmentAmount;
	}

	public void insertProduct(Product product) {

		listProduct.add(product);

	}

	public int getListProductSize() {

		return listProduct.size();
	}

	public ArrayList<Product> getListProduct() {

		return listProduct;

	}

	public void deleteProduct(Product product) {

		listProduct.remove(product);

	}
	
	public int quantityTotal(){
		
		int count = 0;
		
		
		for(int i=0; i<listProduct.size(); i++){
				
				count = count + listProduct.get(i).getQuantity();
			
		     }
		
		  System.out.println(count);
		  return count;
		}


}
