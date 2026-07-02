package it.unibo.infomanager.infomng.model;

public class transactionsProducts implements transactionsProductsI {
	
	private modelStoreI product;
	private int quantity;
	private double price;
	
	public transactionsProducts(modelStoreI prodotto, int quantita, double prezzoUnitario){
		this.product = prodotto;
		this.quantity = quantita;
		this.price = prezzoUnitario;
	}
	@Override
	public modelStoreI getProductInvolved(){
		return this.product;
	}
	@Override
	public int getQuantity(){
		return this.quantity;
	}
	@Override
	public double getPrice(){
		return this.price;
	}
}	
