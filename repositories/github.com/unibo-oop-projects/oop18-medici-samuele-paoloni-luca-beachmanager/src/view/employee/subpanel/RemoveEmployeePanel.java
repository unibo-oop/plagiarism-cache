package view.employee.subpanel;

import java.awt.GridBagLayout;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controller.UserFactory;
import model.Employee;

/**
 * Pannello per rimuovere un dipendente dal sistema
 * 
 * @author Samuele Medici, samuele.medici2@studio.unibo.it (Mat. 0000718877 )
 *
 */
public class RemoveEmployeePanel extends JPanel implements EmployeeListConsumer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6237891517623637374L;

	// ComboBox per la lista dei dipendenti
	private JComboBox<String> employeeComboBox = new JComboBox<String>();

	// Lista dipendenti
	private Employee[] employeesList;

	/**
	 * Costruttore del pannello per la rimozione
	 * 
	 * @param employeeList Lista iniziale dei dipendenti
	 * @param userFactory  Controller per la gestione dei dipendenti
	 */
	public RemoveEmployeePanel(Employee[] employeeList, UserFactory userFactory) {
		super();

		// GridBagLayout per centrare i componenti
		this.setLayout(new GridBagLayout());

		this.employeesList = employeeList;

		// Aggiungo tutti i dipendenti alla comboBox
		this.updateView();

		JButton fireEmployee = new JButton("Licenzia");
		fireEmployee.addActionListener(e -> {
			userFactory.removeEmployee(this.employeesList[this.employeeComboBox.getSelectedIndex()]);
		});

		this.add(this.employeeComboBox);
		this.add(fireEmployee);
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
		this.employeeComboBox.removeAllItems();

		Arrays.stream(this.employeesList).forEach(employee -> {
			this.employeeComboBox.addItem(employee.getFullName());
		});

		// Il primo dell'elemento della lista è selezionato
		if (this.employeesList.length != 0) {
			this.employeeComboBox.setSelectedIndex(0);
		}

	}
}
