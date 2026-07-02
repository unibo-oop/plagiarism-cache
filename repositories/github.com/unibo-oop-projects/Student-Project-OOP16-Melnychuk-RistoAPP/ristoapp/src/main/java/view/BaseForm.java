package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.DataContainer;
import util.FormValidator;

/**
 * Base class for the cancellation, modification and new reservation forms.
 *
 */
public class BaseForm extends JPanel implements FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Table, Name, DAteTime, number Of persons, Telephone, Menu (as notes)
	// The reference to the top level window
	protected MasterView masterView;
	protected Integer reservationId;

	protected JLabel header, name, email, phone, table, time, date, numberOfPersons, menu, errorLabel;
	protected JTextField nameField, emailField, phoneField, tableField, timeField, dateField, numberOfPersonsField,
			menuField;

	FormValidator val;

	private static Logger logger = Logger.getLogger("view");

	public BaseForm(final MasterView masterView) {
		this.masterView = masterView;
		initializeUI();
		val = new FormValidator();
	}

	protected void initializeUI() {
		reservationId = 0;
		setVisible(true);
		setSize(900, 500);
		setLayout(null);

		header = new JLabel("Fai una prenotatione!");
		header.setForeground(Color.blue);
		header.setFont(new Font("Serif", Font.BOLD, 20));

		name = new JLabel("Nome:");
		email = new JLabel("Email:");
		phone = new JLabel("Telefono:");
		table = new JLabel("Tavolo:");
		numberOfPersons = new JLabel("Numero di persona:");
		time = new JLabel("Ora:");
		date = new JLabel("Data:");
		menu = new JLabel("Menu:");
		errorLabel = new JLabel("");

		nameField = new JTextField();
		nameField.setName("Name");
		nameField.addFocusListener(this);

		emailField = new JTextField();
		emailField.setName("Email");
		emailField.addFocusListener(this);

		phoneField = new JTextField();
		phoneField.setName("Phone");
		phoneField.addFocusListener(this);

		tableField = new JTextField();
		tableField.setName("Table");
		tableField.addFocusListener(this);

		numberOfPersonsField = new JTextField();
		numberOfPersonsField.setName("No");
		numberOfPersonsField.addFocusListener(this);

		timeField = new JTextField();
		timeField.setName("Ora");
		timeField.addFocusListener(this);

		dateField = new JTextField();
		dateField.setName("Date");
		dateField.addFocusListener(this);

		menuField = new JTextField();
		menuField.setName("Menu");
		menuField.addFocusListener(this);

		// header.setBounds(100, 30, 400, 30);
		name.setBounds(80, 70, 200, 30);
		email.setBounds(80, 110, 200, 30);
		phone.setBounds(80, 150, 200, 30);
		table.setBounds(80, 190, 200, 30);
		numberOfPersons.setBounds(80, 230, 200, 30);
		time.setBounds(80, 270, 200, 30);
		date.setBounds(80, 310, 200, 30);
		menu.setBounds(80, 350, 200, 30);
		errorLabel.setBounds(80, 380, 400, 30);

		nameField.setBounds(300, 70, 200, 30);
		emailField.setBounds(300, 110, 200, 30);
		phoneField.setBounds(300, 150, 200, 30);
		tableField.setBounds(300, 190, 200, 30);
		numberOfPersonsField.setBounds(300, 230, 200, 30);
		timeField.setBounds(300, 270, 200, 30);
		dateField.setBounds(300, 310, 200, 30);
		menuField.setBounds(300, 350, 200, 30);

		// add(header);
		add(name);
		add(nameField);
		add(email);
		add(emailField);
		add(phone);
		add(phoneField);
		add(table);
		add(tableField);
		add(numberOfPersons);
		add(numberOfPersonsField);
		add(time);
		add(timeField);
		add(date);
		add(dateField);
		add(menu);
		add(menuField);
		add(errorLabel);
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JTextField getEmailField() {
		return emailField;
	}

	public JTextField getPhoneField() {
		return phoneField;
	}

	public JTextField getTableField() {
		return tableField;
	}

	public JTextField getTimeField() {
		return timeField;
	}

	public JTextField getDateField() {
		return dateField;
	}

	public JTextField getNumberOfPersonsField() {
		return numberOfPersonsField;
	}

	public JTextField getMenuField() {
		return menuField;
	}

	@Override
	public void focusLost(FocusEvent e) {
		boolean isError = false;
		logger.log(Level.INFO, "Focus lost" + e.getComponent().getName());
		if (e.getComponent().getName().equals("Name")) {
			isError = val.validateName(this.nameField.getText());
		}
		if (e.getComponent().getName().equals("Email")) {
			isError = val.validateEmail(this.emailField.getText());
		}
		if (e.getComponent().getName().equals("Phone")) {
			isError = val.validatePhone(this.phoneField.getText());
		}
		if (e.getComponent().getName().equals("Table")) {
			isError = val.validateTable(this.tableField.getText());
		}
		if (e.getComponent().getName().equals("No")) {
			isError = val.validateNoOfPersons(this.numberOfPersonsField.getText());
		}
		if (e.getComponent().getName().equals("Ora")) {
			isError = val.validateTime(this.dateField.getText(), this.timeField.getText());
			if (!isError)
				isError = val.validateIsReservationAvailable(this.dateField.getText(), this.timeField.getText(),
						this.getTableField().getText());
		}
		if (e.getComponent().getName().equals("Date")) {
			isError = val.validateDate(this.dateField.getText());
			if (!isError)
				isError = val.validateIsReservationAvailable(this.dateField.getText(), this.timeField.getText(),
						this.getTableField().getText());
		}
		if (e.getComponent().getName().equals("Menu")) {
			isError = val.validateMenu(this.menuField.getText());
		}
		if (isError) {
			errorLabel.setText("Errore: " + DataContainer.getData(DataContainer.VALIDATION_ERROR));
			this.repaint();
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		String error;
		logger.log(Level.INFO, "Focus gained" + e.getComponent().getName());
		if (e.getComponent().getName().equals("Name")) {
			error = (String) DataContainer.getData(DataContainer.VALIDATION_ERROR_NAME);
			if (error != null && error.equalsIgnoreCase("TRUE")) {
				errorLabel.setText("");
				DataContainer.remove(DataContainer.VALIDATION_ERROR_NAME);
			}
		}
		if (e.getComponent().getName().equals("Email")) {
			error = (String) DataContainer.getData(DataContainer.VALIDATION_ERROR_EMAIL);
			if (error != null && error.equalsIgnoreCase("TRUE")) {
				errorLabel.setText("");
				DataContainer.remove(DataContainer.VALIDATION_ERROR_EMAIL);
			}
		}
		if (e.getComponent().getName().equals("Phone")) {
			error = (String) DataContainer.getData(DataContainer.VALIDATION_ERROR_PHONE);
			if (error != null && error.equalsIgnoreCase("TRUE")) {
				errorLabel.setText("");
				DataContainer.remove(DataContainer.VALIDATION_ERROR_PHONE);
			}
		}
		if (e.getComponent().getName().equals("Table")) {
			error = (String) DataContainer.getData(DataContainer.VALIDATION_ERROR_TABLE);
			if (error != null && error.equalsIgnoreCase("TRUE")) {
				errorLabel.setText("");
				DataContainer.remove(DataContainer.VALIDATION_ERROR_TABLE);
			}
		}
		if (e.getComponent().getName().equals("No")) {
			error = (String) DataContainer.getData(DataContainer.VALIDATION_ERROR_NO);
			if (error != null && error.equalsIgnoreCase("TRUE")) {
				errorLabel.setText("");
				DataContainer.remove(DataContainer.VALIDATION_ERROR_NO);
			}
		}
		if (e.getComponent().getName().equals("Ora")) {
			error = (String) DataContainer.getData(DataContainer.VALIDATION_ERROR_TIME);
			if (error != null && error.equalsIgnoreCase("TRUE")) {
				errorLabel.setText("");
				DataContainer.remove(DataContainer.VALIDATION_ERROR_TIME);
			}
		}
		if (e.getComponent().getName().equals("Date")) {
			error = (String) DataContainer.getData(DataContainer.VALIDATION_ERROR_DATE);
			if (error != null && error.equalsIgnoreCase("TRUE")) {
				errorLabel.setText("");
				DataContainer.remove(DataContainer.VALIDATION_ERROR_DATE);
			}
		}
		if (e.getComponent().getName().equals("Menu")) {
			error = (String) DataContainer.getData(DataContainer.VALIDATION_ERROR_MENU);
			if (error != null && error.equalsIgnoreCase("TRUE")) {
				errorLabel.setText("");
				DataContainer.remove(DataContainer.VALIDATION_ERROR_MENU);
			}
		}

	}

}
