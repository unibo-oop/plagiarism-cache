package view.implementations;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import view.interfaces.AddPlaneView;

public class AddPlaneViewImpl implements AddPlaneView {

	private static final int MAX_CHARACTERS = 15;

	private JFrame planeFrame;
	private JLabel lblTitle;
	private JLabel lblAirlineName;
	private JLabel lblFirstClassSeats;
	private JLabel lblEconomyClassSeats;
	private JLabel lblBusinessClassSeats;
	private JTextField txfFirstClassSeats;
	private JTextField txfAirlineName;
	private JTextField txfEconomyClassSeats;
	private JTextField txfBusinessClassSeats;
	private JButton btnConfirm;
	private JButton btnBack;

	public AddPlaneViewImpl() {
		initializeComponents();
		new FrameSettings(planeFrame, 674, 479, false);
		createLayout();
	}

	private void createLayout() {

		GroupLayout groupLayout = new GroupLayout(planeFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(97)
					.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
					.addGap(109))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(184)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblFirstClassSeats)
						.addComponent(lblAirlineName)
						.addComponent(lblEconomyClassSeats)
						.addComponent(lblBusinessClassSeats))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txfBusinessClassSeats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txfEconomyClassSeats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txfFirstClassSeats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txfAirlineName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(325, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(455)
					.addComponent(btnConfirm, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(49))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(lblTitle)
					.addGap(67)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAirlineName)
						.addComponent(txfAirlineName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFirstClassSeats)
						.addComponent(txfFirstClassSeats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEconomyClassSeats)
						.addComponent(txfEconomyClassSeats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBusinessClassSeats)
						.addComponent(txfBusinessClassSeats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(102)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack)
						.addComponent(btnConfirm))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		planeFrame.getContentPane().setLayout(groupLayout);
	}

	private void initializeComponents() {
		planeFrame = new JFrame("Aggiungi Aereo");

		lblTitle = new JLabel("Airline Planes");
		lblTitle.setFont(new Font("Dialog", Font.ITALIC, 50));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

		lblAirlineName = new JLabel("Nome Compagnia Aerea:");
		lblFirstClassSeats = new JLabel("Posti Prima Classe:");
		lblEconomyClassSeats = new JLabel("Posti Economy Class:");
		lblBusinessClassSeats = new JLabel("Posti Business Class:");

		txfEconomyClassSeats = new JTextField(MAX_CHARACTERS);
		txfBusinessClassSeats = new JTextField(MAX_CHARACTERS);
		txfFirstClassSeats = new JTextField(MAX_CHARACTERS);
		txfAirlineName = new JTextField(MAX_CHARACTERS);
		
		btnBack = new JButton("Indietro");
		btnConfirm = new JButton("Conferma");

	}

	public String getNBusinessClassTotalSeats() {
		return txfBusinessClassSeats.getText();
	}

	public String getAirlineName() {
		return txfAirlineName.getText();
	}

	public String getNFirstClassTotalSeats() {
		return txfFirstClassSeats.getText();
	}

	public String getNEconomyClassTotalSeats() {
		return txfEconomyClassSeats.getText();
	}

	public void addConfirmListener(ActionListener confirmListener) {
		btnConfirm.addActionListener(confirmListener);
	}

	public void addBackListener(ActionListener backListener) {
		btnBack.addActionListener(backListener);
	}

	public void displayConfirmMessage(String confirm) {
		JOptionPane.showConfirmDialog(planeFrame, confirm);
	}

	public void displayErrorMessage(String error) {
		JOptionPane.showMessageDialog(planeFrame, error);
	}

	public void close() {
		planeFrame.dispose();
	}
}
