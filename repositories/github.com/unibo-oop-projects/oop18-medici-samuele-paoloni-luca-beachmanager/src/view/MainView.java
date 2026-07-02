package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import view.employee.EmployeeManagementPanel;
import view.employee.WorkDayPanel;
import view.menus.SideMenu;
import view.umbrella.EquipmentPaymentPanel;
import view.umbrella.UmbrellaPanel;

/**
 * 
 * @author Samuele Medici, samuele.medici2@studio.unibo.it ( Mat. 0000718877 )
 * 
 * View principale
 */

public class MainView {
	
	
	// Data che rappresenta il giorno corrente
	// private Date date = new Date(System.currentTimeMillis());

	//private JLabel label =  new JLabel("Today: " + date.getTime());
	
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	private final int width = dimension.width * 2 / 3;
	private final int height = dimension.width / 2;
	
	// Pannelli
	private UmbrellaPanel umbrellaPanel;
	private final String umbrellaPanelID = "UmbrellaPanel";
	
	private EmployeeManagementPanel emPanel;
	private final String emPanelID = "EmployeeManagementPanel";
	
	private WorkDayPanel workDayPanel;
	private final String workDayPanelID = "WorkDayPanel";
	
	private EquipmentPaymentPanel equipmentPanel; 
	private final String equipmentPanelID = "EquipmentPaymentPanel";
	
	// JFrame principale
	JFrame mainFrame = new JFrame("Beach Manager");
	
	// JPanel principale
	JPanel mainPanel = new JPanel();
	CardLayout cardLayout = new CardLayout();
	
	JMenuBar menuBar = new JMenuBar();
	
	public MainView() {
		// Setto un CardLayout nel Pannello Principale
		this.mainPanel.setLayout(this.cardLayout);
		
		
		// Oggetti dei pannelli
		this.umbrellaPanel = new UmbrellaPanel(this.width, this.height);
		this.emPanel = new EmployeeManagementPanel();
		this.workDayPanel = new WorkDayPanel();
		this.equipmentPanel = new EquipmentPaymentPanel();
		
		
		// Aggiungo i pannelli a quello principale
		this.mainPanel.add(this.umbrellaPanelID, this.umbrellaPanel);
		this.mainPanel.add(this.emPanelID, this.emPanel);
		this.mainPanel.add(this.workDayPanelID, this.workDayPanel);
		this.mainPanel.add(this.equipmentPanelID, this.equipmentPanel);
		
		// Barra del menù
		SideMenu sideMenu = new SideMenu(this);
		this.menuBar.add(sideMenu);
		
		this.mainFrame.setJMenuBar(this.menuBar);

		// Aggiungo il pannello principale al frame setto le dimensioni
		this.mainFrame.getContentPane().add(this.mainPanel);
		
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// setto le dimensioni del mainFrame
		this.mainFrame.setSize(width, height);
		// Centro il mainFrame con le dimensioni appena settate
		this.mainFrame.setLocation(dimension.width / 2 - this.mainFrame.getSize().width / 2, 
				dimension.height / 2 - this.mainFrame.getSize().height / 2 );
		
	}
	/**
	 * Metodo che rende visibile il frame
	 */
	public void show() {
		this.mainFrame.setVisible(true);
	}
	
	// Metodi per cambiare il pannello visualizzato
	
	/**
	 * Apro il pannello per la visualizzazione ombrelli
	 */
	public void showUmbrellaPanel() {
		if (!this.umbrellaPanel.isVisible()) {
			this.cardLayout.show(this.mainPanel, this.umbrellaPanelID);
		}
	}
	
	/**
	 * Apro pannello gestione dipendenti
	 */
	public void showEmployeeManagementPanel() {
		if (!this.emPanel.isVisible()) {
			this.cardLayout.show(this.mainPanel, this.emPanelID);
		}
	}
	
	/**
	 * Apro pannello visualizzazione orario
	 */
	public void showWorkDayPanel() {
		if (!this.workDayPanel.isVisible()) {
			this.cardLayout.show(this.mainPanel, this.workDayPanelID);
		}
	}
	
	/**
	 * Apro pannello gestione pagamenti
	 */
	public void showEquipmentPanel() {
		if (!this.equipmentPanel.isVisible()) {
			this.cardLayout.show(this.mainPanel, this.equipmentPanelID);
		}
	}


}
