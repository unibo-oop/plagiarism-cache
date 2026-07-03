package view.implementations;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import view.interfaces.AddBookingView;

public class AddBookingViewImpl implements AddBookingView {

	private static final int MAX_CHARACTERS = 15;
	private static final int STANDART_WEIGHT = 10;
	private static final int BUSINESS_WEIGHT = 20;
	
	private JFrame bookingFrame;
	private JLabel lblSurname;
	private JLabel lblNome;
	private JLabel lblTitle;
	private JLabel lblDocumentId;
	private JLabel lblClass;
	private JLabel lblFiscalCode;
	private JLabel lblAge;
	private JLabel lblEmail;
	private JLabel lblSelectFlight;
	private JLabel lblBagaglio;
	private JLabel lblTelefonocell;
	private JTextField txfPhoneNumber;
	private JTextField txfSurname;
	private JTextField txfName;
	private JTextField txfDocumentId;
	private JTextField txfFiscalCode;
	private JTextField txfAge;
	private JTextField txfEmail;
	private JButton btnBack;
	private JButton btnConfirm;
	private JRadioButton rdbFirstClass;
	private JRadioButton rdbEconomyClass;
	private JRadioButton rdbBusinessClass;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JCheckBox chkbxStandartLuggage;
	private JCheckBox chkbxBusinessLuggage;
	private DefaultComboBoxModel<Object> cmbModel;
	private BoundsPopupMenuListener cmbxPopupListener;
	private JComboBox<Object> cmbxSelectFlight;

	public AddBookingViewImpl() {

		initializeComponents();
		componentsSettings();
		new FrameSettings(bookingFrame, 730, 555, false);
		createLayout();
		
	}

	private void initializeComponents() {
		bookingFrame = new JFrame("Aggiungi Prenotazione");
		
		lblSurname = new JLabel("Cognome:");
		lblNome = new JLabel("Nome:");
		lblTitle = new JLabel("Airline Bookings");
		lblDocumentId = new JLabel("Documento ID:");
		lblAge = new JLabel("Eta':");
		lblEmail = new JLabel("e-mail:");
		lblClass = new JLabel("Classe:");
		lblSelectFlight = new JLabel("Sel. Volo:");
		lblBagaglio = new JLabel("Bagaglio:");
		lblFiscalCode = new JLabel("Cod. Fiscale:");
		lblTelefonocell = new JLabel("Telefono/Cell:");
		
		txfPhoneNumber = new JTextField(MAX_CHARACTERS);
		txfSurname = new JTextField(MAX_CHARACTERS);
		txfName = new JTextField(MAX_CHARACTERS);
		txfDocumentId = new JTextField(MAX_CHARACTERS);
		txfFiscalCode = new JTextField(MAX_CHARACTERS);
		txfAge = new JTextField(MAX_CHARACTERS);
		txfEmail = new JTextField(MAX_CHARACTERS);
		
		btnBack = new JButton("Indietro");
		btnConfirm = new JButton("Conferma");
		
		rdbFirstClass = new JRadioButton("Prima");
		rdbEconomyClass = new JRadioButton("Economy");		
		rdbBusinessClass = new JRadioButton("Business");	
		
		cmbModel = new DefaultComboBoxModel<>();
		cmbxPopupListener = new BoundsPopupMenuListener(true, false);
		cmbxSelectFlight = new JComboBox<>(cmbModel);
		
		chkbxStandartLuggage = new JCheckBox("Standart (10Kg)");
		chkbxBusinessLuggage = new JCheckBox("Business (+20kg)");
	}
	
	private void componentsSettings() {
		lblTitle.setFont(new Font("Dialog", Font.ITALIC, 50));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);;
		buttonGroup.add(rdbFirstClass);
		rdbEconomyClass.setSelected(true);
		buttonGroup.add(rdbEconomyClass);
		buttonGroup.add(rdbBusinessClass);
		chkbxStandartLuggage.setSelected(true);		
		cmbxSelectFlight.addPopupMenuListener(cmbxPopupListener);
		
	}
	
	private void createLayout() {
		
		GroupLayout groupLayout = new GroupLayout(bookingFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(87)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblTitle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblClass)
										.addComponent(lblSelectFlight)
										.addComponent(lblNome)
										.addComponent(lblSurname)
										.addComponent(lblDocumentId)
										.addComponent(lblBagaglio))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(2)
											.addComponent(chkbxStandartLuggage)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chkbxBusinessLuggage))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(rdbFirstClass)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(rdbEconomyClass)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(rdbBusinessClass))
												.addGroup(groupLayout.createSequentialGroup()
													.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(cmbxSelectFlight, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
														.addComponent(txfDocumentId)
														.addComponent(txfName)
														.addComponent(txfSurname))
													.addGap(63)))
											.addGap(61)
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblFiscalCode)
												.addComponent(lblAge)
												.addComponent(lblEmail)
												.addComponent(lblTelefonocell))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txfPhoneNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
													.addComponent(txfEmail)
													.addComponent(txfFiscalCode)
													.addComponent(txfAge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))))
							.addGap(108))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(33))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txfSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNome)))
								.addComponent(lblSurname))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDocumentId)
								.addComponent(txfDocumentId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(cmbxSelectFlight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelectFlight)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFiscalCode)
								.addComponent(txfFiscalCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAge)
								.addComponent(txfAge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail)
								.addComponent(txfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblTelefonocell)
								.addComponent(txfPhoneNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblClass)
						.addComponent(rdbFirstClass)
						.addComponent(rdbEconomyClass)
						.addComponent(rdbBusinessClass))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(chkbxBusinessLuggage)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblBagaglio)
							.addComponent(chkbxStandartLuggage)))
					.addGap(115)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConfirm))
					.addGap(20))
		);
		bookingFrame.getContentPane().setLayout(groupLayout);
	}

	public void addFlightToCmbx(Object object) {
		cmbModel.addElement(object);
	}

	public Object getFlight() {
		return cmbxSelectFlight.getSelectedItem();
	}

	public String getTravelClass() {
		if(rdbBusinessClass.isSelected()) {
			return rdbBusinessClass.getText();
		} else if(rdbFirstClass.isSelected()) {
			return rdbFirstClass.getText();
		} else {
		        return rdbEconomyClass.getText();
		}
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

	public String getAge() {
		return txfAge.getText();
	}

	public String getDocumentId() {
		return txfDocumentId.getText();
	}

	public int getWeight() {
		return chkbxBusinessLuggage.isSelected() ? STANDART_WEIGHT + BUSINESS_WEIGHT : STANDART_WEIGHT;
	}

	public void addConfirmListener(ActionListener confirmListener) {
		btnConfirm.addActionListener(confirmListener);
	}

	public void addBackListener(ActionListener backListener) {
		btnBack.addActionListener(backListener);
	}

	public void displayErrorMessage(String error) {
		JOptionPane.showMessageDialog(bookingFrame, error);
	}

	public void displayConfirmMessage(String confirm) {
		JOptionPane.showConfirmDialog(bookingFrame, confirm);
	}

	public void close() {
		bookingFrame.dispose();
	}
}
