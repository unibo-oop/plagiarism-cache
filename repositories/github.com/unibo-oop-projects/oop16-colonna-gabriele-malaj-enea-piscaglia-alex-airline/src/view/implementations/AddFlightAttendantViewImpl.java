package view.implementations;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import view.interfaces.AddFlightAttendantView;

public class AddFlightAttendantViewImpl implements AddFlightAttendantView {

	private static final int MAX_CHARACTERS = 15;

	private JFrame pilotFrame;
	private JLabel lblSurname;
	private JLabel lblNome;
	private JLabel lblTitle;
	private JLabel lblBirthDate;
	private JLabel lblPhone;
	private JLabel lblEmail;
	private JLabel lblSesso;
	private JLabel lblFiscalCode;
	private JTextField txfFiscalCode;
	private JTextField txfPhoneNumber;
	private JTextField txfSurname;
	private JTextField txfEmail;
	private JTextField txfName;
	private JButton btnBack;
	private JButton btnConfirm;
	private JRadioButton rdbtnFemale;
	private JRadioButton rdbtnMale;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox<Object> cmbxDay;
	private JComboBox<Object> cmbxMonth;
	private JComboBox<Object> cmbxYear;

	public AddFlightAttendantViewImpl() {

		initializeComponents();
		componentsSettings();
		new FrameSettings(pilotFrame, 730, 481, false);
		createLayout();

	}

	private void initializeComponents() {
		pilotFrame = new JFrame("Aggiungi Pilota");

		lblSurname = new JLabel("Cognome:");
		lblNome = new JLabel("Nome:");
		lblTitle = new JLabel("Airline Flight Attendants");
		lblPhone = new JLabel("Telefono/Cell:");
		lblBirthDate = new JLabel("Data di nascita:");
		lblEmail = new JLabel("e-mail:");
		lblSesso = new JLabel("Sesso:");
		lblFiscalCode = new JLabel("Cod. Fiscale:");

		txfFiscalCode = new JTextField(MAX_CHARACTERS);
		txfPhoneNumber = new JTextField(MAX_CHARACTERS);
		txfSurname = new JTextField(MAX_CHARACTERS);
		txfEmail = new JTextField(MAX_CHARACTERS);
		txfName = new JTextField(MAX_CHARACTERS);

		rdbtnMale = new JRadioButton("Maschio");
		rdbtnFemale = new JRadioButton("Femmina");
		rdbtnFemale.setSelected(true);

		btnBack = new JButton("Indietro");
		btnConfirm = new JButton("Conferma");

		cmbxDay = new JComboBox<>(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
						"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" });
		cmbxMonth = new JComboBox<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" });
		cmbxYear = new JComboBox<>(new String[] { "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947",
				"1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960",
				"1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973",
				"1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986",
				"1987", "1988", "1989", "1990", "1991", "1992", "1994", "1995", "1996", "1997", "1998", "1999" });
	}

	private void componentsSettings() {
		buttonGroup.add(rdbtnMale);
		buttonGroup.add(rdbtnFemale);
		lblTitle.setFont(new Font("Dialog", Font.ITALIC, 50));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	}

	private void createLayout() {

		GroupLayout groupLayout = new GroupLayout(pilotFrame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap(90, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 529, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(13)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblFiscalCode).addComponent(lblSurname).addComponent(lblNome))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txfSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txfFiscalCode, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblPhone)
										.addComponent(lblEmail))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txfPhoneNumber, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
				.addGap(97))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(526, Short.MAX_VALUE)
						.addComponent(btnConfirm).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE).addGap(30))
				.addGroup(groupLayout.createSequentialGroup().addGap(90)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblBirthDate)
								.addComponent(lblSesso))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addComponent(rdbtnMale)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(rdbtnFemale))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(cmbxDay, GroupLayout.PREFERRED_SIZE, 41,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(cmbxMonth, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(cmbxYear,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(407, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(21)
				.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE).addGap(35)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txfSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPhone).addComponent(txfPhoneNumber, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblSurname))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNome)
						.addComponent(txfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail).addComponent(txfEmail, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblFiscalCode).addComponent(
						txfFiscalCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(27)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblBirthDate)
						.addComponent(cmbxDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbxMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbxYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(98)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnBack)
										.addComponent(btnConfirm)))
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(22).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(rdbtnMale).addComponent(rdbtnFemale).addComponent(lblSesso))))
				.addGap(21)));
		pilotFrame.getContentPane().setLayout(groupLayout);
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
		return txfPhoneNumber.getText();
	}

	public String getEmail() {
		return txfEmail.getText();
	}

	public String getAttendantGender() {
		return rdbtnMale.isSelected() ? rdbtnMale.getText() : rdbtnFemale.getText();
	}

	public String getAttendantBirthDate() {
		return cmbxDay.getSelectedItem() + "/" + cmbxMonth.getSelectedItem() + "/" + cmbxYear.getSelectedItem();
	}

	public void addConfirmListener(ActionListener confirmListener) {
		btnConfirm.addActionListener(confirmListener);
	}

	public void addBackListener(ActionListener backListener) {
		btnBack.addActionListener(backListener);
	}

	public void displayErrorMessage(String error) {
		JOptionPane.showMessageDialog(pilotFrame, error);
	}

	public void displayConfirmMessage(String confirm) {
		JOptionPane.showConfirmDialog(pilotFrame, confirm);
	}

	public void close() {
		pilotFrame.dispose();
	}
}
