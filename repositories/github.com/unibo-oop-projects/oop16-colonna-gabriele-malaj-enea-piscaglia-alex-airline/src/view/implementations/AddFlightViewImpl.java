package view.implementations;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import view.interfaces.AddFlightView;

public class AddFlightViewImpl implements AddFlightView {

	private static final int MAX_CHARACTERS = 15;
	private static final int MAX_DIGITS = 4;

	private JFrame flightsFrame;
	private JLabel lblTitle;
	private JButton btnConfirm;
	private JButton btnBack;
	private JLabel lblSelectPlane;
	private JLabel lblSelectDestination;
	private JLabel lblSelectPilot;
	private JLabel lblSelectCopilot;
	private JLabel lblSelectFlightAtt;
	private JLabel lblBasicPrice;
	private JLabel lblDepartureTime;
	private JLabel lblArrivalTime;
	private JLabel lblArrivalDate;
	private JLabel lblMinuteSeparator;
	private JLabel lblMinSeparator;
	private JLabel lblDataPartenza;
	private JComboBox<Object> cmbxSelectPlane;
	private JComboBox<Object> cmbxSelectDestination;
	private JComboBox<Object> cmbxSelectPilot;
	private JComboBox<Object> cmbxSelectCoPilot;
	private CheckComboBox ccmbxSelectFlightAtt;
	private DefaultComboBoxModel<Object> cmbModelPlane;
	private DefaultComboBoxModel<Object> cmbModelDestination;
	private DefaultComboBoxModel<Object> cmbModelPilot;
	private DefaultComboBoxModel<Object> cmbModelCoPilot;
	private JTextField txfDaparturehour;
	private JTextField txfArrivalhour;
	private JTextField txfBasicPrice;
	private JTextField txfDepartureMinute;
	private JTextField txfArrivalMinute;
	private JComboBox<Object> cmbxYear;
	private JComboBox<Object> cmbxMonth;
	private JComboBox<Object> cmbxDay;
	private JComboBox<Object> cmbxArrivalDateYear;
	private JComboBox<Object> cmbxArrivalDateMonth;
	private JComboBox<Object> cmbxArrivalDateDay;

	public AddFlightViewImpl(Set<String> set) {
		
		initializeComponents(set);
		componentsSettings();
		
		new FrameSettings(flightsFrame, 730, 590, false);
		
		createLayout();
	}

	private void componentsSettings() {
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Dialog", Font.ITALIC, 50));

		cmbxSelectPlane.setPrototypeDisplayValue(cmbxSelectPlane.getSelectedItem());
		cmbxSelectDestination.setPrototypeDisplayValue(cmbxSelectDestination.getSelectedItem());
		cmbxSelectPilot.setPrototypeDisplayValue(cmbxSelectPilot.getSelectedItem());
		cmbxSelectCoPilot.setPrototypeDisplayValue(cmbxSelectCoPilot.getSelectedItem());

	}

	private void createLayout() {
		
		GroupLayout groupLayout = new GroupLayout(flightsFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(103)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 541, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDepartureTime)
								.addComponent(lblSelectDestination)
								.addComponent(lblSelectPlane)
								.addComponent(lblSelectPilot)
								.addComponent(lblSelectCopilot)
							        .addComponent(lblSelectFlightAtt))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								        .addComponent(ccmbxSelectFlightAtt, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(cmbxSelectCoPilot, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(cmbxSelectPilot, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(cmbxSelectDestination, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(cmbxSelectPlane, Alignment.LEADING, 0, 235, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblArrivalDate)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(txfDaparturehour, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblMinuteSeparator)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txfDepartureMinute, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
											.addGap(35)
											.addComponent(lblArrivalTime)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(txfArrivalhour, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblMinSeparator)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(btnConfirm, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
												.addComponent(txfArrivalMinute, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(cmbxArrivalDateDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cmbxArrivalDateMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cmbxArrivalDateYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))))
					.addGap(32))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(145)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblBasicPrice)
						.addComponent(lblDataPartenza))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(cmbxDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cmbxMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cmbxYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(txfBasicPrice))
					.addContainerGap(367, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addComponent(lblTitle)
					.addGap(63)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectPlane)
						.addComponent(cmbxSelectPlane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectDestination)
						.addComponent(cmbxSelectDestination, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSelectPilot)
						.addComponent(cmbxSelectPilot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectCopilot)
						.addComponent(cmbxSelectCoPilot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(lblSelectFlightAtt)
                                                .addComponent(ccmbxSelectFlightAtt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDepartureTime)
						.addComponent(txfDaparturehour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMinuteSeparator, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txfDepartureMinute, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblArrivalTime)
						.addComponent(txfArrivalhour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txfArrivalMinute, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMinSeparator))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataPartenza)
						.addComponent(cmbxDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbxMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbxYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblArrivalDate)
						.addComponent(cmbxArrivalDateDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbxArrivalDateMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbxArrivalDateYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBasicPrice)
						.addComponent(txfBasicPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(69)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack)
						.addComponent(btnConfirm))
					.addGap(20))
		);
		flightsFrame.getContentPane().setLayout(groupLayout);
	}

	private void initializeComponents(Set<String> set) {
		flightsFrame = new JFrame("Aggiungi Volo");

		btnBack = new JButton("Indietro");
		btnConfirm = new JButton("Conferma");

		lblTitle = new JLabel("Airline Flights");
		lblSelectPlane = new JLabel("Seleziona Aereo:");
		lblSelectDestination = new JLabel("Seleziona Destinazioine:");
		lblSelectPilot = new JLabel("Seleziona Pilota:");
		lblSelectCopilot = new JLabel("Seleziona Co-Pilota:");
		lblSelectFlightAtt = new JLabel("Seleziona Assistente:");
		lblDepartureTime = new JLabel("Ora Partenza:");
		lblArrivalTime = new JLabel("Ora Arrivo:");
		lblBasicPrice = new JLabel("Prezzo Base:");
		lblMinuteSeparator = new JLabel(":");
		lblMinSeparator = new JLabel(":");
		lblDataPartenza = new JLabel("Data Partenza:");
		lblArrivalDate = new JLabel("Data Arrivo:");
		
		cmbModelPlane = new DefaultComboBoxModel<>();
		cmbModelDestination = new DefaultComboBoxModel<>();
		cmbModelPilot = new DefaultComboBoxModel<>();
		cmbModelCoPilot = new DefaultComboBoxModel<>();

		cmbxSelectPlane = new JComboBox<>(cmbModelPlane);
		cmbxSelectDestination = new JComboBox<>(cmbModelDestination);
		cmbxSelectPilot = new JComboBox<>(cmbModelPilot);
		cmbxSelectCoPilot = new JComboBox<>(cmbModelCoPilot);
		ccmbxSelectFlightAtt = new CheckComboBox(set);
		
		cmbxDay = new JComboBox<>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
				"22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
		cmbxMonth = new JComboBox<>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
		cmbxYear = new JComboBox<>(new String[] {"2017", "2018"});
		
		cmbxArrivalDateDay = new JComboBox<>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
				"22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
		cmbxArrivalDateMonth = new JComboBox<>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
		cmbxArrivalDateYear = new JComboBox<>(new String[] {"2017", "2018"});
		
		txfDaparturehour = new JTextField(MAX_CHARACTERS);
		txfArrivalhour = new JTextField(MAX_CHARACTERS);
		txfBasicPrice = new JTextField(MAX_CHARACTERS);
		txfDepartureMinute = new JTextField(MAX_DIGITS);
		txfArrivalMinute = new JTextField(MAX_DIGITS);
	}

	public String getPlane() {
		return (String) cmbxSelectPlane.getSelectedItem();
	}

	public String getDestination() {
		return (String) cmbxSelectDestination.getSelectedItem();
	}

	public String getDepartureTime() {
		return txfDaparturehour.getText() + ":" + txfDepartureMinute.getText();
	}

	public String getArrivalTime() {
		return txfArrivalhour.getText() + ":" + txfArrivalMinute.getText();
	}

	public String getBasicPrice() {
		return txfBasicPrice.getText();
	}

	public String getPilot() {
		return (String) cmbxSelectPilot.getSelectedItem();
	}

	public String getCopilot() {
		return (String) cmbxSelectCoPilot.getSelectedItem();
	}

        public String[] getFlightAttendants() {
                return ccmbxSelectFlightAtt.getSelectedItems();
        }

	public void addPlaneToCmbx(Object planeId) {
		cmbModelPlane.addElement(planeId);
	}

	public void addDestinationToCmbx(Object destinationId) {
		cmbModelDestination.addElement(destinationId);
	}

	public void addPilotToCmbx(Object pilotId) {
		cmbModelPilot.addElement(pilotId);
	}

	public void addCopilotToCmbx(Object copilotId) {
		cmbModelCoPilot.addElement(copilotId);
	}

	public void addConfirmListener(ActionListener confirmListener) {
		btnConfirm.addActionListener(confirmListener);
	}

	public void addBackListener(ActionListener backListener) {
		btnBack.addActionListener(backListener);
	}

	public void displayConfirmMessage(String confirm) {
		JOptionPane.showConfirmDialog(flightsFrame, confirm);
	}

	public void displayErrorMessage(String error) {
		JOptionPane.showMessageDialog(flightsFrame, error);
	}

	public void close() {
		flightsFrame.dispose();
	}

	public String getDepartureDate() {
		return cmbxDay.getSelectedItem() + "/" + cmbxMonth.getSelectedItem() + "/" + cmbxYear.getSelectedItem();
	}
	
	public String getArrivalDate() {
		return cmbxArrivalDateDay.getSelectedItem() + 
				"/" + cmbxArrivalDateMonth.getSelectedItem() +
				"/" + cmbxArrivalDateYear.getSelectedItem();
	}
}
