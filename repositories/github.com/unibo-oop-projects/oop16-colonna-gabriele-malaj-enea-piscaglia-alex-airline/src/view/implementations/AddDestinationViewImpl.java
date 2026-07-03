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

import view.interfaces.AddDestinationView;

public class AddDestinationViewImpl implements AddDestinationView {

	private static final int MAX_CHARACTERS = 15;

	private JFrame destinationFrame;
	private JLabel lblTitle;
	private JLabel lblPaese;
	private JLabel lblCitt;
	private JLabel lblAereoporto;
	private JLabel lblIdDestinazione;
	private JTextField txfCity;
	private JTextField txfCountry;
	private JTextField txfAirport;
	private JTextField txfDestinationId;
	private JButton btnConfirm;
	private JButton btnBack;

	public AddDestinationViewImpl() {
		
		initializeComponents();
		
		new FrameSettings(destinationFrame, 674, 479, false);
		
		createLayout();
	}

	private void createLayout() {

		GroupLayout groupLayout = new GroupLayout(destinationFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(97)
					.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
					.addGap(109))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(184)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCitt)
						.addComponent(lblPaese)
						.addComponent(lblAereoporto)
						.addComponent(lblIdDestinazione))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txfDestinationId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txfAirport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txfCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txfCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
						.addComponent(lblPaese)
						.addComponent(txfCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCitt)
						.addComponent(txfCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAereoporto)
						.addComponent(txfAirport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIdDestinazione)
						.addComponent(txfDestinationId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(102)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack)
						.addComponent(btnConfirm))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		destinationFrame.getContentPane().setLayout(groupLayout);
	}

	private void initializeComponents() {
		destinationFrame = new JFrame("Aggiungi Destinazione");

		lblTitle = new JLabel("Airline Destinations");
		lblTitle.setFont(new Font("Dialog", Font.ITALIC, 50));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

		lblPaese = new JLabel("Paese:");
		lblCitt = new JLabel("Città:");
		lblAereoporto = new JLabel("Aereoporto:");
		lblIdDestinazione = new JLabel("ID Destinazione:");

		txfAirport = new JTextField(MAX_CHARACTERS);
		txfDestinationId = new JTextField(MAX_CHARACTERS);
		txfCity = new JTextField(MAX_CHARACTERS);
		txfCountry = new JTextField(MAX_CHARACTERS);
		
		btnBack = new JButton("Indietro");
		btnConfirm = new JButton("Conferma");

	}

	public String getDestinationId() {
		return txfDestinationId.getText();
	}

	public String getCountry() {
		return txfCountry.getText();
	}

	public String getCity() {
		return txfCity.getText();
	}

	public String getAirport() {
		return txfAirport.getText();
	}

	public void addConfirmListener(ActionListener confirmListener) {
		btnConfirm.addActionListener(confirmListener);
	}

	public void addBackListener(ActionListener backListener) {
		btnBack.addActionListener(backListener);
	}

	public void displayConfirmMessage(String confirm) {
		JOptionPane.showConfirmDialog(destinationFrame, confirm);
	}

	public void displayErrorMessage(String error) {
		JOptionPane.showMessageDialog(destinationFrame, error);
	}

	public void close() {
		destinationFrame.dispose();
	}
}
