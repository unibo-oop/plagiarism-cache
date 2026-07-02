package view.menus;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import view.MainView;
import view.employee.EmployeeManagementPanel;

/**
 * Principale menù superiore per la selezione delle operazioni
 * @author Samuele Medici, samuele.medici2@studio.unibo.it ( Mat. 0000718877 )
 *
 */

public class SideMenu extends JMenu {
	

	private static final long serialVersionUID = 1L;
	
	private static final String UMBRELLA = "Umbrella";
	private static final String WORK_DAY = "Work Day";
	private static final String MANAGE_EMPLOYEE = "Management Employee";
	private static final String PAYMENT = "Payment";
	
	private static final String MENU_TITLE = "Operazioni";
	
	/**
	 * Construttore per la classe SideMenu
	 */
	public SideMenu(MainView parent) {
		// Aggiungo una barra al menù
		super(SideMenu.MENU_TITLE);		

		// aggiungo gli item che servono alla barra
		JMenuItem booking = new JMenuItem(SideMenu.UMBRELLA); // Pannelo visualizzazione informazioni ombrelloni
		booking.addActionListener(e -> {
			parent.showUmbrellaPanel();
		});
		
		JMenuItem workDay = new JMenuItem(SideMenu.WORK_DAY); // Pannello visualizzazione turni lavoratori
		workDay.addActionListener(e -> {
			parent.showWorkDayPanel();
		});
		
		JMenuItem manageEmployee = new JMenuItem(SideMenu.MANAGE_EMPLOYEE); // Pannello gestione dipendenti
		manageEmployee.addActionListener(e -> {
			parent.showEmployeeManagementPanel();
		});
		
		JMenuItem payment = new JMenuItem(SideMenu.PAYMENT); // Pannello gestione pagamenti 
		payment.addActionListener(e -> {
			parent.showEquipmentPanel();
		});
		
		this.add(booking);
		this.addSeparator();
		
		this.add(workDay);
		this.addSeparator();
		
		this.add(manageEmployee);
		this.addSeparator();
		
		this.add(payment);
		
	}

}
