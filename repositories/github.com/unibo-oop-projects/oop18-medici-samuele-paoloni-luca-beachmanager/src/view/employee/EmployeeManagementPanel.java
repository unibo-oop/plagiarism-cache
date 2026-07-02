package view.employee;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.UserFactoryImpl;
import model.Employee;
import view.employee.subpanel.AddEmployeePanel;
import view.employee.subpanel.EmployeesListPanel;
import view.employee.subpanel.RemoveEmployeePanel;
import view.employee.subpanel.WorkdaySchedulePanel;

/**
 * Pannello principale per la gestione dei dipendenti
 * 
 * @author Samuele Medici, samuele.medici2@studio.unibo.it ( Mat. 0000718877 )
 *
 */

public class EmployeeManagementPanel extends JPanel implements EmployeeManagementInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4247759100643770011L;

	// Dimensione dello schermo
	private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

	// Controller per i dipendenti

	private UserFactoryImpl userFactorImpl = new UserFactoryImpl(this);

	// Lista dei dipendenti
	private Employee[] employeeList;

	// Card layout utilizzato per lo switch
	private CardLayout cardLayout = new CardLayout();

	// Pannello che contiene il layout per lo switch dei pannelli
	private JPanel cardPanel = new JPanel(this.cardLayout);

	// Pannelli per la gestione
	private EmployeesListPanel listPanel; // Pannello lista dei dipendenti
	private final String listPanelID = "listPanel";

	private AddEmployeePanel addEmpPanel; // Pannello Aggiunta dei dipendenti
	private final String addEmpPanelID = "AddEmployeePanel";

	private RemoveEmployeePanel removeEmpPanel; // Pannello licenziamento dipendenti
	private final String removeEmpPanelID = "RemoveEmployeePanel";

	private WorkdaySchedulePanel workdayPanel; // Pannello gestione gestione workday
	private final String workdayPanelID = "WorkdaySchedulePanel";

	public EmployeeManagementPanel() {
		super(new GridLayout(0, 2));
		// Ottenere tutta la lista degli employee
		this.employeeList = this.userFactorImpl.getAllEmployes();

		// Istanze dei pannelli
		this.listPanel = new EmployeesListPanel(this.employeeList);
		this.removeEmpPanel = new RemoveEmployeePanel(this.employeeList, this.userFactorImpl);
		this.addEmpPanel = new AddEmployeePanel(this.userFactorImpl);
		this.workdayPanel = new WorkdaySchedulePanel(this.employeeList);

		// Aggiungo uno scroll pane per ottenere lo scroll all'interno della lista dei
		// dipendenti
		JScrollPane scrollPane = new JScrollPane(this.listPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(this.dimension.width / 2, 200));

		// Costruisco il pannello dei bottoni
		JPanel buttonPanel = this.buildButtonPanel();

		this.cardPanel.add(this.listPanelID, scrollPane);
		this.cardPanel.add(this.addEmpPanelID, this.addEmpPanel);
		this.cardPanel.add(this.removeEmpPanelID, this.removeEmpPanel);
		this.cardPanel.add(this.workdayPanelID, this.workdayPanel);

		this.cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(cardPanel);

		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(buttonPanel);
	}

	/**
	 * Costruisce il pannello per i bottoni utilizzati per lo switch dei pannelli
	 * 
	 * @return Pannello dei bottoni
	 */
	private JPanel buildButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		// Bottone pannello lista
		JButton listButton = new JButton("Lista dipendenti");
		listButton.addActionListener(e -> {
			this.showListPanel();
		});

		// Bottone pannello aggiunta
		JButton addButton = new JButton("Aggiungi dipendente");
		addButton.addActionListener(e -> {
			this.showAddEmployeePanel();
		});

		// Bottone pannello licenziamento
		JButton removeButton = new JButton("Licenzia dipendente");
		removeButton.addActionListener(e -> {
			this.showRemoveEmployeePanel();
		});

		// Bottone pannello gestione scheduler
		JButton workdayButton = new JButton("Gestione turni");
		workdayButton.addActionListener(e -> {
			this.showWorkdayPanel();
		});

		// Centro i bottoni sull'asse X
		listButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		workdayButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		buttonPanel.add(listButton);
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		buttonPanel.add(workdayButton);

		return buttonPanel;
	}

	// Metodi per visualizzare i pannelli all'interno del CardLayout

	/**
	 * Mostra pannello per la lista dei dipendenti
	 */
	private void showListPanel() {
		if (!this.listPanel.isVisible()) {
			this.cardLayout.show(this.cardPanel, this.listPanelID);
		}
	}

	/**
	 * Mostra il pannello per l'aggiunta dei dipendenti
	 */
	private void showAddEmployeePanel() {
		if (!this.addEmpPanel.isVisible()) {
			this.cardLayout.show(this.cardPanel, this.addEmpPanelID);
		}
	}

	/**
	 * Mostra il pannello per l'aggiunta dei dipendenti
	 */
	private void showRemoveEmployeePanel() {
		if (!this.removeEmpPanel.isVisible()) {
			this.cardLayout.show(this.cardPanel, this.removeEmpPanelID);
		}
	}

	/**
	 * Mostra il pannello gestione turni
	 */
	private void showWorkdayPanel() {
		if (!this.workdayPanel.isVisible()) {
			this.cardLayout.show(this.cardPanel, this.workdayPanelID);
		}
	}

	@Override
	public void updateEmployeeList(Employee[] employeeList) {
		// aggiorno tutti i sottopannelli
		this.listPanel.updateEmployeeList(employeeList);
		this.removeEmpPanel.updateEmployeeList(employeeList);
		this.workdayPanel.updateEmployeeList(employeeList);
	}
}
