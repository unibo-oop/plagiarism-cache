package model;
import java.io.Serializable;

import model.interfaces.*;

public class ProductImpl implements Product, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3580932584438063223L;
	private String code;
	private String name;
	private String description;
	private String category;
	private double price;
	private int quantity;
	
	public ProductImpl(String code, String name, String description, String category, double price, int quantity) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}

	@Override
	public String getProductCode() {
		return this.code;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String getCategory() {
		return this.category;
	}

	@Override
	public double getPrice() {
		return this.price;
	}
	
	@Override
	public int getQuantity() {
		return this.quantity;
	}
	
	@Override
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		
	}
	
	public static class ProductBuilder {
		private String code;
		private String name;
		private String description;
		private String category;
		private double price;
		private int quantity;
		
		public ProductBuilder code(String code) {
			this.code = code;
			return this;
		}
		
		public ProductBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public ProductBuilder description(String description) {
			this.description = description;
			return this;
		}
		
		public ProductBuilder category(String category) {
			this.category = category;
			return this;
		}
		
		public ProductBuilder price(double price) {
			this.price = price;
			return this;
		}
		
		public ProductBuilder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}
		
		public ProductImpl builder() {
			return new ProductImpl(this.code, this.name, this.description, this.category, this.price, this.quantity);
		}
	}
}
