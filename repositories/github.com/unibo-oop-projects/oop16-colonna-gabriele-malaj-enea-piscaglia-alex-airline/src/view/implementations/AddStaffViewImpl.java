package view.implementations;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import view.interfaces.AddStaffView;
import javax.swing.JComboBox;

public class AddStaffViewImpl implements AddStaffView {

	private static final int MAX_CHARACTERS = 15;

	private JFrame staffFrame;
	private JLabel lblAirlineStaff;
	private JLabel lblName;
	private JLabel lblUsername;
	private JLabel lblSurname;
	private JLabel lblFiscalCode;
	private JLabel lblGender;
	private JLabel lblBirthDate;
	private JLabel lblCellphone;
	private JLabel lblPassword;
	private JLabel lblConfirmPassword;
	private JLabel lblEmail;
	private JTextField txfName;
	private JTextField txfSurname;
	private JTextField txfFiscalCode;
	private JTextField txfUsername;
	private JTextField txfEmail;
	private JTextField txfCellphone;
	private JRadioButton rdbtnFemale;
	private JRadioButton rdbtnMale;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnConfirm;
	private JButton btnBack;
	private JPasswordField pfPassword;
	private JPasswordField pfConfirmPassword;
	private JSeparator separator;
	private JComboBox<Object> cmbxDay;
	private JComboBox<Object> cmbxMonth;
	private JComboBox<Object> cmbxYear;
	
	public AddStaffViewImpl() {
		initializeComponents();
		frameSettings(staffFrame, 745, 535, false);
		createLayout();
	}

	void frameSettings(JFrame frame, int width, int height, boolean resizable) {
		frame.setSize(width, height);
		frame.setResizable(resizable);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (frame.getWidth() / 2);
		int yPos = (dim.height / 2) - (frame.getHeight() / 2);

		frame.setLocation(xPos, yPos);
	}
	private void createLayout() {

		GroupLayout groupLayout = new GroupLayout(staffFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(78)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblAirlineStaff, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblName)
								.addComponent(lblSurname)
								.addComponent(lblFiscalCode)
								.addComponent(lblGender)
								.addComponent(lblBirthDate))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(rdbtnMale)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnFemale))
								.addComponent(txfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txfSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txfFiscalCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(cmbxDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cmbxMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cmbxYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(60)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEmail, Alignment.TRAILING)
								.addComponent(lblUsername, Alignment.TRAILING)
								.addComponent(lblPassword, Alignment.TRAILING)
								.addComponent(lblConfirmPassword, Alignment.TRAILING)
								.addComponent(lblCellphone, Alignment.TRAILING))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txfCellphone)
								.addComponent(pfPassword, 126, 126, Short.MAX_VALUE)
								.addComponent(txfUsername)
								.addComponent(pfConfirmPassword, 126, 126, Short.MAX_VALUE)
								.addComponent(txfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(114, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(514)
					.addComponent(btnConfirm, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addGap(57))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addComponent(lblAirlineStaff)
					.addGap(60)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(127)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail)
								.addComponent(txfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCellphone)
								.addComponent(txfCellphone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblName)
										.addComponent(txfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblSurname)
										.addComponent(txfSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(txfUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblUsername))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPassword)
										.addComponent(pfPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblFiscalCode)
									.addComponent(txfFiscalCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblConfirmPassword)
									.addComponent(pfConfirmPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(59)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBirthDate)
								.addComponent(cmbxDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbxMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbxYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(127)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGender)
								.addComponent(rdbtnMale)
								.addComponent(rdbtnFemale))))
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack)
						.addComponent(btnConfirm))
					.addGap(21))
		);
		staffFrame.getContentPane().setLayout(groupLayout);
	}

	private void initializeComponents() {
		staffFrame = new JFrame("Aggiungi Staff");

		lblAirlineStaff = new JLabel("Airline Staff");
		lblAirlineStaff.setFont(new Font("Dialog", Font.ITALIC, 50));
		lblAirlineStaff.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblName = new JLabel("Nome:");
		lblSurname = new JLabel("Cognome:");
		lblFiscalCode = new JLabel("Cod. Fiscale:");
		lblGender = new JLabel("Sesso:");
		lblBirthDate = new JLabel("Data di Nascita:");
		lblUsername = new JLabel("Username:");
		lblPassword = new JLabel("Password:");
		lblConfirmPassword = new JLabel("Conferma Password:");
		lblEmail = new JLabel("e-mail:");
		lblCellphone = new JLabel("Cellulare:");
		
		btnBack = new JButton("Indietro");
		btnConfirm = new JButton("Conferma");
		
		txfName = new JTextField(MAX_CHARACTERS);
		txfSurname = new JTextField(MAX_CHARACTERS);
		txfFiscalCode = new JTextField(MAX_CHARACTERS);
		txfUsername = new JTextField(MAX_CHARACTERS);
		txfEmail = new JTextField(MAX_CHARACTERS);
		txfCellphone = new JTextField(MAX_CHARACTERS);
		
		pfPassword = new JPasswordField();
		pfConfirmPassword = new JPasswordField();
		
		cmbxDay = new JComboBox<>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
				"22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
		cmbxMonth = new JComboBox<>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
		cmbxYear = new JComboBox<>(new String[] {"1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949",
				"1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", 
				"1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969",
				"1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979",
				"1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", 
				"1990", "1991", "1992", "1994", "1995", "1996", "1997", "1998", "1999"});
		
		
		rdbtnMale = new JRadioButton("Maschio");
		rdbtnMale.setSelected(true);
		buttonGroup.add(rdbtnMale);
		rdbtnFemale = new JRadioButton("Femmina");
		buttonGroup.add(rdbtnFemale);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);	
	}

	public String getName() {
		return txfName.getText();
	}

	public String getSurname() {
		return txfSurname.getText();
	}

	public String getFiscalCode() {
		return txfFiscalCode.getText();
	}

	public String getTelephoneNumber() {
		return txfCellphone.getText();
	}

	public String getEmail() {
		return txfEmail.getText();
	}

	public String getUsername() {
		return txfUsername.getText();
	}

	public String getPassword() {
		return new String(pfPassword.getPassword());
	}
	
	public String getConfirmPassWord() {
		return new String(pfConfirmPassword.getPassword());
	}

	public String getGender() {
		return rdbtnMale.isSelected() ? rdbtnMale.getText() : rdbtnFemale.getText();
	}

	public String getBirthDate() {
		return cmbxDay.getSelectedItem() + "/" + cmbxMonth.getSelectedItem() + "/" + cmbxYear.getSelectedItem();
	}

	public void addConfirmListener(ActionListener confirmListener) {
		btnConfirm.addActionListener(confirmListener);
	}

	public void addBackListener(ActionListener backListener) {
		btnBack.addActionListener(backListener);
	}

	public void displayConfirmMessage(String confirm) {
		JOptionPane.showConfirmDialog(staffFrame, confirm);
	}

	public void displayErrorMessage(String error) {
		JOptionPane.showMessageDialog(staffFrame, error);
	}

	public void close() {
		staffFrame.dispose();
	}
}
