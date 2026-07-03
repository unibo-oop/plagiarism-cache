package menu;

public class MenuItem {
	
	private String name;
	private double price;
	
	public MenuItem(final String name, final double price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setName(final String name) {
		this.name = name;
	}

	public void setPrice(final double price) {
		this.price = price;
	}
}
