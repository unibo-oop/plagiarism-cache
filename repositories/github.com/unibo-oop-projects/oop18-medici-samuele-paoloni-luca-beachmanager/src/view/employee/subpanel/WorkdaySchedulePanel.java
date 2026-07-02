package view.employee.subpanel;

import java.awt.GridBagLayout;
import java.text.DateFormatSymbols;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.Employee;
import utils.constants.Shift;

/**
 * Pannello per gestione della schedulazione degli orari
 * 
 * @author Samuele Medici, samuele.medici2@studio.unibo.it ( Mat. 0000718877 )
 *
 */
public class WorkdaySchedulePanel extends JPanel implements EmployeeListConsumer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3995710509958222251L;

	// Lista dipendenti
	private Employee[] employeeList;

	// ComboBox per la scelta del cambio turno
	private JComboBox<String> employeeComboBox = new JComboBox<String>();
	private JComboBox<String> weekDayComboBox = new JComboBox<String>();
	private JComboBox<String> shiftComboBox = new JComboBox<String>();

	public WorkdaySchedulePanel(Employee[] employeeList) {
		super();
	
		// GridBagLayout per centrare i componenti
		this.setLayout(new GridBagLayout());

		// Ottengo i giorni della settimana
		DateFormatSymbols dfs = new DateFormatSymbols();
		Arrays.stream(dfs.getShortWeekdays()).forEach(weekday -> {
			if (!weekday.isEmpty())
				this.weekDayComboBox.addItem(weekday);
		});

		// Aggiungo tutti i possibili turni
		Arrays.stream(Shift.values()).forEach(shift -> {
			this.shiftComboBox.addItem(shift.getValue());
		});

		JButton changeShift = new JButton("Assegna turno");
		changeShift.addActionListener(e -> {
			// Ottieni i valori dalle combobox
			Employee emp = this.employeeList[this.employeeComboBox.getSelectedIndex()];
			String weekday = (String) this.weekDayComboBox.getSelectedItem();
			String shift = (String) this.shiftComboBox.getSelectedItem();

			// this.resetInputs();
		});

		this.add(this.employeeComboBox);
		this.add(this.weekDayComboBox);
		this.add(shiftComboBox);

		this.updateEmployeeList(employeeList);

		this.add(changeShift);
	}

	/**
	 * Aggiorna la View
	 */
	private void updateView() {
		this.employeeComboBox.removeAllItems();

		// Utilizzo gli stream che sono più dichiarativi
		Arrays.stream(this.employeeList).forEach(employee -> {
			this.employeeComboBox.addItem(employee.getFullName());
		});

		this.resetInputs();
	}

	@Override
	public void updateEmployeeList(Employee[] employeeList) {
		this.employeeList = employeeList;
		this.updateView();
	}

	/**
	 * Reset ai valori di default
	 */
	private void resetInputs() {
		this.employeeComboBox.setSelectedIndex(0);
		this.weekDayComboBox.setSelectedIndex(0);
		this.shiftComboBox.setSelectedIndex(0);
	}

}
