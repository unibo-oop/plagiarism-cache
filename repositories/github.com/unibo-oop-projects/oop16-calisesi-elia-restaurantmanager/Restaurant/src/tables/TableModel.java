package tables;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.print.PrintException;

import com.itextpdf.text.DocumentException;

public interface TableModel {
	
	//Get all table's orders
	
	public List<Order> getListOrders();
	
	//Add new MenuItem to Table's order
	
	public void addToOrder(final String name) throws IOException;

	//Return table's orders for add item to JTable
	
	public String[][] getOrders();

	//Create table's bill in pdf and delete the table's orders from lounge
	
	public void getBill(final double disc) throws FileNotFoundException, DocumentException, PrintException, IOException;
	
	//Remove items from order
	public void removeItem(final String s,final int qty) throws IOException;
	
	//Remove table's file
	public void deleteTable();
}