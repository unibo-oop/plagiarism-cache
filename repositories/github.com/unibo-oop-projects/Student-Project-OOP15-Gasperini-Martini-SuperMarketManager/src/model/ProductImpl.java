package model;

import java.io.Serializable;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public class ProductImpl implements Product, Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private int codeProduct;
	private int price;
	private int quantity;

	public ProductImpl(String name, int code, int price, int quantity) {

		this.name = name;
		this.codeProduct = code;
		this.price = price;
		this.quantity = quantity;
	}

	public String getName() {

		return this.name;
	}

	public int getCodeProduct() {

		return this.codeProduct;
	}

	public int getPrice() {

		return this.price;
	}

	public int getQuantity() {

		return this.quantity;
	}

	public void setName(String name) {

		this.name = name;
	}

	public void setPrice(int price) {

		this.price = price;
	}

	public void setQuantity(int quantity) {

		this.quantity = quantity;
	}

	public void setCode(int code) {

		this.codeProduct = code;
	}

}
