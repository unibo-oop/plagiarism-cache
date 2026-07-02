package view.employee.subpanel;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.stream.IntStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.UserFactory;
import model.Employee;

/**
 * Pannello per aggiungere un nuovo dipendente
 * 
 * @author Samuele Medici, samuele.medici2@studio.unibo.it ( Mat. 0000718877 )
 *
 */
public class AddEmployeePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ComboBox per la data di nascita
	private JComboBox<Integer> dayComboBox = new JComboBox<Integer>();
	private JComboBox<Integer> monthComboBox = new JComboBox<Integer>();
	private JComboBox<Integer> yearComboBox = new JComboBox<Integer>();

	// Input nome e cognome
	private JTextField nameField = new JTextField(30);
	private JTextField surnameField = new JTextField(30);

	// Campi per l'anagrafica del dipendente
	private String employeeName = "";
	private String employeeSurname = "";

	private int birthDay;
	private int birthMonth;
	private int birthYear;

	// Bottone aggiunta dipendente
	private JButton addButton = new JButton("Aggiungi dipendente");

	/**
	 * Pannello per l'aggiunta dei dipendenti
	 * @param userFactory controller per la gestione dei dipendenti
	 */
	public AddEmployeePanel(UserFactory userFactory) {
		super(true);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel namePanel = this.buildNamePanel();
		JPanel surnamePanel = this.buildSurnamePanel();
		JPanel birthdayPanel = this.buildBirthdayPanel();

		this.add(namePanel);
		this.add(surnamePanel);
		this.add(birthdayPanel);

		// Listener per l'aggiunta del dipendente nel sistema
		this.addButton.addActionListener(e -> {
			Date birthdate = new GregorianCalendar(this.birthYear, this.birthMonth - 1, this.birthDay).getTime();
			new Employee(this.employeeName, this.employeeSurname, birthdate).toString();

			userFactory.addEmployee(this.employeeName, this.employeeSurname, birthdate);

			this.clearAllInputs();

		});
		this.addButton.setEnabled(false);
		this.add(addButton);
	}

	/**
	 * Reset degli input
	 */
	private void clearAllInputs() {

		this.dayComboBox.setSelectedIndex(0);
		this.monthComboBox.setSelectedIndex(0);
		this.yearComboBox.setSelectedIndex(0);

		this.birthYear = 0;
		this.birthMonth = 0;
		this.birthDay = 0;

		this.nameField.setText("");
		this.surnameField.setText("");

		this.employeeName = "";
		this.employeeSurname = "";

		this.checkEnablingButton();
	}

	/**
	 * Costruisce il pannello per la data di nascita
	 * 
	 * @return Pannello per la data di nascita
	 */
	private JPanel buildBirthdayPanel() {
		JPanel birthdayPanel = new JPanel();

		final int dayMin = 1;
		final int dayMax = 32; // E' escluso l'ultimo valore

		final int monthMax = 13;
		final int yearMin = 1970;
		final int yearMax = 2001;

		// Valri per le comboBox

		// Stream che generano il range
		IntStream.range(dayMin, dayMax).forEach(e -> {
			this.dayComboBox.addItem(e);
		});

		IntStream.range(dayMin, monthMax).forEach(e -> {
			this.monthComboBox.addItem(e);
		});

		IntStream.range(yearMin, yearMax).forEach(e -> {
			this.yearComboBox.addItem(e);
		});

		// valori di default
		this.birthDay = dayMin;
		this.birthMonth = dayMin;
		this.birthYear = yearMin;

		// Aggiungo i listener

		// Listener giorno
		this.dayComboBox.addActionListener(e -> {
			int value = (Integer) this.dayComboBox.getSelectedItem();
			this.birthDay = value;
			this.checkEnablingButton();
		});

		// Listener mese
		this.monthComboBox.addActionListener(e -> {
			int value = (Integer) this.monthComboBox.getSelectedItem();
			this.birthMonth = value;
			this.checkEnablingButton();
		});

		// Listener mese
		this.yearComboBox.addActionListener(e -> {
			int value = (Integer) this.yearComboBox.getSelectedItem();
			this.birthYear = value;
			this.checkEnablingButton();
		});

		// Dati relativi alla data di nascita
		JLabel birthDay = new JLabel("Giorno: ");
		JLabel birthMonth = new JLabel("Mese: ");
		JLabel birthYear = new JLabel("Anno: ");

		birthdayPanel.add(birthDay);
		birthdayPanel.add(this.dayComboBox);

		birthdayPanel.add(birthMonth);
		birthdayPanel.add(this.monthComboBox);

		birthdayPanel.add(birthYear);
		birthdayPanel.add(this.yearComboBox);

		return birthdayPanel;
	}

	/**
	 * Costruisce il pannello per l'inserimento del nome
	 * 
	 * @param docListener Listener per il cambiamento all'input
	 * @return pannello inserimento nome
	 */
	private JPanel buildNamePanel() {
		JPanel namePanel = new JPanel();

		this.nameField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				employeeName = nameField.getText();
				checkEnablingButton();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				employeeName = nameField.getText();
				checkEnablingButton();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				employeeName = nameField.getText();
				checkEnablingButton();
			}

		});

		JLabel employeeName = new JLabel("Nome: ");

		namePanel.add(employeeName);
		namePanel.add(this.nameField);
		return namePanel;

	}

	/**
	 * Costruisce il pannello per l'inserimento del cognome
	 * 
	 * @return pannello inserimento cognome
	 */
	private JPanel buildSurnamePanel() {
		JPanel surnamePanel = new JPanel();

		this.surnameField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				employeeSurname = surnameField.getText();
				checkEnablingButton();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				employeeSurname = surnameField.getText();
				checkEnablingButton();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				employeeSurname = surnameField.getText();
				checkEnablingButton();
			}

		});

		JLabel employeeSurname = new JLabel("Cognome: ");

		surnamePanel.add(employeeSurname);
		surnamePanel.add(this.surnameField);

		return surnamePanel;
	}

	/**
	 * Controlla se ci sono campi sufficienti per aggiungere il dipendente
	 */
	private void checkEnablingButton() {
		boolean checkStatus = this.birthDay != 0 && this.birthMonth != 0 && this.birthYear != 0
				&& !this.employeeName.isEmpty() && !this.employeeSurname.isEmpty();
		this.addButton.setEnabled(checkStatus);

	}
}
