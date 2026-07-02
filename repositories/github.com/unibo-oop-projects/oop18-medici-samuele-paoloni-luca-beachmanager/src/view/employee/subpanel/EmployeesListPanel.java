package view.employee.subpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Employee;

/**
 * Pannello per la visualizzazione della lista dei pannelli
 * 
 * @author Samuele Medici, samuele.medici2@studio.unibo.it ( Mat. 0000718877 )
 *
 */

public class EmployeesListPanel extends JPanel implements EmployeeListConsumer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1632303812174558212L;
	// Lista dei dipendenti
	private Employee[] employeesList;

	/**
	 * Lista dei dipendenti
	 * @param employeesList array dei dipendenti
	 */
	public EmployeesListPanel(Employee[] employeesList) {
		super();
		this.setLayout(new GridLayout(0,1));
		this.setPreferredSize(new Dimension(200, 1000));

		this.employeesList = employeesList;
		this.updateView();
		
	}

	/**
	 * Oggetto della lista dipendenti
	 * @param emp Dipendente 
	 * @return Pannello che contiene le informazioni del dipendente
	 */
	private JPanel generateEmployeePanelItem(Employee emp) {
		JPanel empPanel = new JPanel(new GridLayout(0, 2));
		empPanel.add(new JLabel("Nome: " + emp.getName()));
		empPanel.add(new JLabel("Cognome: " + emp.getSurname()));

		// Format date
		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		String finalDate = format.format(emp.getBirthday());
		empPanel.add(new JLabel("Data di nascita: " + finalDate));
		
		//Aggiungo bordo sotto al pannello del dipendente
		empPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

		return empPanel;
	}

	@Override
	public void updateEmployeeList(Employee[] newEmployeeList) {
		this.employeesList = newEmployeeList;
		this.updateView();
	}

	/**
	 * Aggiorna la View
	 */
	public void updateView() {
		// Elimino tutto il contenuto precedente
		this.removeAll();
		// Per ogni dipendente aggiungo un pannello con le informazioni
		Arrays.stream(this.employeesList).forEach(employee -> {
			this.add(this.generateEmployeePanelItem(employee));
		});
		
	}
}
