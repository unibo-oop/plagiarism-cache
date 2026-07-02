/**
 *@author Ceccarelli 
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;
import view.observer.AddEmployeeObserver;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class AddEmployeePanelImpl extends JPanel implements AddEmployeePanel, ActionListener {

	private static final long serialVersionUID = 1L;
	// prova
	private AddEmployeeObserver observer;
	private JLabel lblTitle;
	private JTextField txtName;
	private JTextField txtSurname;
	private JLabel lblUsername;
	private JTextField txtUsername;
	private JLabel lblPassword;
	private JPasswordField txtPassword;
	private JTextField txtCity;
	private JTextField txtStreet;
	private JTextField txtHouseNumber;
	private JTextField txtTaxCode;
	private JTextField txtTelephone;
	private JLabel lblCity;
	private JLabel lblAddress;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblStreet;
	private JLabel lblHouseNumber;
	private JLabel lblTelephone;
	private JLabel lblTaxCode;
	private JLabel lblBirthDay;
	private JComboBox<Integer> cmbDay;
	private JComboBox<Integer> cmbMonth;
	private JComboBox<Integer> cmbYear;
	private JButton btnAddEmployee;
	private JButton btnClear;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblYear;
	private JLabel lblMonth;
	private JLabel lblDay;

	public AddEmployeePanelImpl() {
		setBackground(SystemColor.activeCaption);
		this.setLayout(null);
		System.out.println(Calendar.getInstance().get(Calendar.YEAR));
		lblTitle = new JLabel("Aggiunta Dipendente");
		lblTitle.setForeground(new Color(255, 69, 0));
		lblTitle.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 880, 48);
		add(lblTitle);

		lblName = new JLabel("Nome:");
		lblName.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblName.setBounds(42, 83, 143, 14);
		add(lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("Calibri", Font.ITALIC, 13));
		txtName.setBounds(42, 101, 143, 20);
		add(txtName);
		txtName.setColumns(10);

		lblSurname = new JLabel("Cognome:");
		lblSurname.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblSurname.setBounds(42, 142, 140, 14);
		add(lblSurname);

		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Calibri", Font.ITALIC, 13));
		txtSurname.setBounds(42, 157, 143, 20);
		add(txtSurname);
		txtSurname.setColumns(10);

		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblUsername.setBounds(42, 201, 143, 14);
		add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Calibri", Font.ITALIC, 13));
		txtUsername.setBounds(42, 215, 143, 20);
		add(txtUsername);
		txtUsername.setColumns(10);

		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblPassword.setBounds(42, 261, 143, 14);
		add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Calibri", Font.ITALIC, 13));
		txtPassword.setBounds(42, 275, 143, 20);
		add(txtPassword);

		lblAddress = new JLabel("Indirizzo:");
		lblAddress.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblAddress.setBounds(39, 317, 143, 14);
		add(lblAddress);

		txtCity = new JTextField();
		txtCity.setFont(new Font("Calibri", Font.ITALIC, 13));
		txtCity.setBounds(39, 349, 86, 20);
		add(txtCity);
		txtCity.setColumns(10);

		txtStreet = new JTextField();
		txtStreet.setFont(new Font("Calibri", Font.ITALIC, 13));
		txtStreet.setBounds(134, 349, 86, 20);
		add(txtStreet);
		txtStreet.setColumns(10);

		txtHouseNumber = new JTextField();
		txtHouseNumber.setFont(new Font("Calibri", Font.ITALIC, 13));
		txtHouseNumber.setBounds(231, 349, 44, 20);
		add(txtHouseNumber);
		txtHouseNumber.setColumns(10);

		lblCity = new JLabel("città");
		lblCity.setFont(new Font("Calibri", Font.ITALIC, 12));
		lblCity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCity.setBounds(39, 337, 86, 14);
		add(lblCity);

		lblStreet = new JLabel("via");
		lblStreet.setFont(new Font("Calibri", Font.ITALIC, 12));
		lblStreet.setHorizontalAlignment(SwingConstants.CENTER);
		lblStreet.setBounds(134, 337, 86, 14);
		add(lblStreet);

		lblHouseNumber = new JLabel("N#");
		lblHouseNumber.setFont(new Font("Calibri", Font.ITALIC, 12));
		lblHouseNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblHouseNumber.setBounds(231, 337, 44, 14);
		add(lblHouseNumber);

		lblTaxCode = new JLabel("Codice Fiscale:");
		lblTaxCode.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblTaxCode.setBounds(42, 414, 143, 14);
		add(lblTaxCode);

		txtTaxCode = new JTextField();
		txtTaxCode.setFont(new Font("Calibri", Font.ITALIC, 13));
		txtTaxCode.setBounds(42, 431, 143, 20);
		add(txtTaxCode);
		txtTaxCode.setColumns(10);

		lblTelephone = new JLabel("Telefono:");
		lblTelephone.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblTelephone.setBounds(661, 83, 143, 14);
		add(lblTelephone);

		txtTelephone = new JTextField();
		txtTelephone.setBounds(661, 103, 143, 20);
		add(txtTelephone);
		txtTelephone.setColumns(10);

		lblBirthDay = new JLabel("Anno di nascita:");
		lblBirthDay.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblBirthDay.setBounds(661, 237, 143, 14);
		add(lblBirthDay);

		cmbDay = new JComboBox<Integer>();
		cmbDay.setEnabled(false);
		cmbDay.setBounds(823, 275, 50, 20);
		cmbDay.addActionListener(this);
		add(cmbDay);

		cmbMonth = new JComboBox<Integer>();
		cmbMonth.setEnabled(false);
		cmbMonth.setBounds(766, 275, 50, 20);
		cmbMonth.addActionListener(this);
		add(cmbMonth);
		this.setMonth();

		cmbYear = new JComboBox<Integer>();
		cmbYear.setBounds(661, 275, 95, 20);
		cmbYear.addActionListener(this);
		add(cmbYear);
		this.setYear();

		btnAddEmployee = new JButton("Aggiungi dipendente");
		btnAddEmployee.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		btnAddEmployee.setBounds(703, 469, 170, 40);
		btnAddEmployee.addActionListener(this);
		add(btnAddEmployee);

		btnClear = new JButton("Pulisci");
		btnClear.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		btnClear.setBounds(39, 470, 115, 38);
		btnClear.addActionListener(this);
		add(btnClear);

		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblEmail.setBounds(661, 403, 212, 14);
		add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Calibri", Font.ITALIC, 10));
		txtEmail.setBounds(661, 421, 212, 20);
		add(txtEmail);
		txtEmail.setColumns(10);

		lblYear = new JLabel("AAAA");
		lblYear.setBounds(661, 263, 46, 14);
		add(lblYear);

		lblMonth = new JLabel("MM");
		lblMonth.setBounds(767, 263, 46, 14);
		add(lblMonth);

		lblDay = new JLabel("GG");
		lblDay.setFont(new Font("Calibri", Font.ITALIC, 12));
		lblDay.setBounds(823, 263, 46, 14);
		add(lblDay);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object isPressed = e.getSource();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date hireDate = null;
		Date currentDate = Calendar.getInstance().getTime();
		try {
			hireDate = (Date) dateFormat.parse((dateFormat.format(currentDate)));
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

		if (isPressed == btnAddEmployee) {
			try {
				this.observer.saveEmployee(txtName.getText(), txtSurname.getText(),
						(txtCity.getText() + " " + txtStreet.getText() + " " + txtHouseNumber.getText()),
						txtUsername.getText(), txtPassword.getPassword(), txtEmail.getText(),
						Integer.parseInt(txtTelephone.getText()), txtTaxCode.getText(),
						(Date) dateFormat.parse(cmbYear.getSelectedItem().toString() + "/"
								+ cmbMonth.getSelectedItem().toString() + "/" + cmbDay.getSelectedItem().toString()),
						hireDate);

			} catch (NumberFormatException | ParseException e1) {
				this.displayMessage("Si prega di riempire in modo adeguato tutti i campi presenti ");
			}
		} else if (isPressed == btnClear) {
			this.cleanInterface();
		} else if (isPressed == cmbYear) {
			cmbMonth.setEnabled(true);
			setDay();
		} else if (isPressed == cmbMonth) {
			cmbDay.setEnabled(true);
			setDay();
		}
	}

	@Override
	public void cleanInterface() {
		this.txtName.setText("");
		this.txtSurname.setText("");
		this.txtUsername.setText("");
		this.txtPassword.setText("");
		this.txtCity.setText("");
		this.txtStreet.setText("");
		this.txtHouseNumber.setText("");
		this.txtTaxCode.setText("");
		this.txtEmail.setText("");
		this.txtTelephone.setText("");
		this.cmbDay.setSelectedItem("");
		this.cmbMonth.setSelectedItem("");
		this.cmbYear.setSelectedItem("");
	}

	@Override
	public void attachObserver(AddEmployeeObserver observer) {
		this.observer = observer;
	}

	@Override
	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Attenzione", JOptionPane.PLAIN_MESSAGE);
	}

	public void setDay() {
		cmbDay.removeAllItems();
		if (cmbMonth.getSelectedItem().toString().equals("2") && (Integer) cmbYear.getSelectedItem() % 4 == 0) {
			for (int i = 1; i <= 29; i++) {
				cmbDay.addItem(i);
			}
		} else if (cmbMonth.getSelectedItem().toString().equals("2") && (Integer) cmbYear.getSelectedItem() % 4 != 0) {
			for (int i = 1; i <= 28; i++) {
				cmbDay.addItem(i);
			}
		} else if (cmbMonth.getSelectedItem().toString().equals("4")
				|| cmbMonth.getSelectedItem().toString().equals("6")
				|| cmbMonth.getSelectedItem().toString().equals("9")
				|| cmbMonth.getSelectedItem().toString().equals("11")) {

			for (int i = 1; i <= 30; i++) {
				cmbDay.addItem(i);
			}
		} else if (!cmbMonth.getSelectedItem().toString().equals("")
				|| !cmbYear.getSelectedItem().toString().equals("")) {
			for (int i = 1; i <= 31; i++) {
				cmbDay.addItem(i);
			}
		}
	}

	public void setMonth() {
		for (int i = 1; i <= 12; i++) {
			cmbMonth.addItem(i);
		}
	}

	public void setYear() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = year - 16; i >= year - 100; i--) {
			cmbYear.addItem(i);
		}
	}
}
