package menu;

import java.io.IOException;
import java.util.List;

public interface MenuModel {
	
	/*Add new MenuItem or change its price in Menu*/
	
	public void addItem(final String name, final double p) throws IOException;
	
	/*Remove a MenuItem from Menu*/
	public void removeItem(final String name) throws IOException;
	
	/*Get all the Menu*/
	public List<MenuItem> getMenu() throws ClassNotFoundException, IOException;

	/*Get an Item from Menu*/
	public MenuItem getItem(final String name);
	
	public boolean isPresentInMenu(final String s);
}
