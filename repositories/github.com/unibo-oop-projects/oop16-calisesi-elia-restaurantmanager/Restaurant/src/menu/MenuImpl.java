package menu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

public class MenuImpl implements MenuModel {

	public static final String FILE_NAME = "Menu.txt";
	private File file = new File(FILE_NAME);
	private List<MenuItem> menu;
	
	public MenuImpl() throws ClassNotFoundException, IOException {
		file.createNewFile();
		this.menu = getMenu();
	}
	
	public void addItem(final String n, final double p) throws IOException {	
		boolean pres = false;
		if (this.menu.isEmpty()) {
			this.menu.add(new MenuItem(n,p));
			this.setMenu(this.menu);
		} else {
			for (MenuItem mi : this.menu) {
				if (mi.getName().equals(n) && mi.getPrice() != p){
					mi.setPrice(p);
					this.file.delete();
					this.setMenu(this.menu);
					pres = true;
					break;
				}  else if (mi.getName().equals(n) && mi.getPrice() == p) {
					pres = true;
					break;
				}
			}
			if (!pres) {
				this.menu.add(new MenuItem(n,p));
				this.setItem(n,p);
			}
		}
	}
	
	public void removeItem(final String n) throws IOException {
		boolean pres = false;
		for (MenuItem m : menu) {
			if (m.getName().equals(n)) {
				this.menu.remove(m);
				pres = true;
				break;
			}
		}
		if (!pres) {
			JOptionPane.showMessageDialog(null, "The product does't exists","Error", JOptionPane.ERROR_MESSAGE);
		}
		this.file.delete();
		this.setMenu(menu);
	}
	
	public MenuItem getItem(final String name) {
		for (MenuItem m : menu) {
			if(m.getName().equals(name)) {
				return m;
			}
		}
		return null;
	}
	
	public List<MenuItem> getMenu() throws ClassNotFoundException, IOException {
		List<MenuItem> lm = new LinkedList<>();
	    try (BufferedReader r = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
	    	String line = null;
	    	String[] item;
	    	while ((line = r.readLine()) != null) {
	    		item = line.split("_");
	    		lm.add(new MenuItem(item[0],Double.parseDouble(item[1])));	
	    	}
	    }
		return lm;
	}
	
	private void setItem(final String name, final double price) throws IOException {
		try (BufferedWriter w = new BufferedWriter(new FileWriter(file.getAbsolutePath(),true))) {
			{
				w.write(name);
				w.write("_");
				w.write(Double.toString(price));
				w.newLine();
				w.flush();
			}
		}
	}
	
	private void setMenu(final List<MenuItem> list) throws IOException {
		try (BufferedWriter w = new BufferedWriter(new FileWriter(file.getAbsolutePath())))
			{
			    for (MenuItem mi : list) {
			    	w.write(mi.getName());
			    	w.write("_");
			    	w.write(Double.toString(mi.getPrice()));
			    	w.newLine();
			    	w.flush();
			    }
			}	
	}

	@Override
	public boolean isPresentInMenu(String s) {
		for (MenuItem mi : this.menu) {
			if (mi.getName().equals(s)) {
				return true;
			}
		}
		return false;
	}
}
