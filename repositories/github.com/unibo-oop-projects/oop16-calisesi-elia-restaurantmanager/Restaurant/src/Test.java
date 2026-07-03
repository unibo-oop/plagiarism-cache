import static org.junit.Assert.*;

import java.io.IOException;

import javax.print.PrintException;

import com.itextpdf.text.DocumentException;

import Gui.Lounge;
import menu.MenuItem;
import menu.MenuImpl;
import menu.MenuModel;
import tables.TableImpl;
import tables.TableModel;

public class Test {
	
	
	@org.junit.Test
	public void testMenu() throws IOException, ClassNotFoundException {
		MenuItem primo = new MenuItem("Primo",12);
		MenuItem secondo = new MenuItem("Secondo",15);
		MenuItem dolce = new MenuItem("Dolce",14);
		
		MenuModel m = new MenuImpl();
		m.addItem("Primo", 12);
		m.addItem("Secondo", 15);
		m.addItem("Dolce", 14);

		assertTrue(m.isPresentInMenu(primo.getName()));
		assertTrue(m.getMenu().size() == 3);
		
		m.removeItem(secondo.getName());
		assertFalse(m.isPresentInMenu("Secondo"));
		m.addItem(secondo.getName(), secondo.getPrice());
		
		m.addItem("Dolce", 18);
		assertTrue(m.getItem(dolce.getName()).getPrice() == 18);
	}
	
	@org.junit.Test
	public void test2() throws ClassNotFoundException, IOException, DocumentException, PrintException {
		TableModel table1 = new TableImpl(1);
		TableModel table2 = new TableImpl(2);
		
		table1.addToOrder("Primo");
		table1.addToOrder("Secondo");
		table1.addToOrder("Dolce");
		table1.addToOrder("Primo");
		table2.addToOrder("Dolce");
		
		assertTrue(table1.getListOrders().size() == 3);
		assertFalse(table2.getListOrders().size() == 0);
		
		table1.removeItem("Primo", 2);
		table1.removeItem("Secondo", 1);
		table1.removeItem("Dolce", 1);
		
		assertTrue(table1.getListOrders().isEmpty());
		Lounge.NAME.createNewFile();     //creo il file gen.dat per le generalità
		table2.getBill(10.0);	
		assertTrue(table2.getListOrders().isEmpty());
		
	}
	

}
