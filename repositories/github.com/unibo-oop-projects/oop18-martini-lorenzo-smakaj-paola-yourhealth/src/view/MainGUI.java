package view;

import controller.Database;

public class MainGUI {
	
	public static void main(String[] args){
		try {
			Database.createTablePazienti();
			Database.createTableDottori();
			Database.createTableMacchinari();
			Database.createTableAmbulatori();
			Database.createTablePrestazioni();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		new LoginForm();
	}
}
