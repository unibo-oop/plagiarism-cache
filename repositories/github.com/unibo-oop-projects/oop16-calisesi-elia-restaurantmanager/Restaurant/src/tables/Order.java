package tables;

import menu.MenuItem;

public class Order {
	private MenuItem mi;
	private int qty;
	
	public Order(final MenuItem mi, final int qty) {
		this.mi = mi;
		this.qty = qty;
	}
	
	public MenuItem getMi() {
		return mi;
	}

	public int getQty() {
		return qty;
	}
	
	public void setQty(final int qty) {
		this.qty = qty;
	}
}
