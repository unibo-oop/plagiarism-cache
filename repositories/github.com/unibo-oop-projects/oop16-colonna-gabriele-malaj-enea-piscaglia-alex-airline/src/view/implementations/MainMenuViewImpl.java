package view.implementations;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import view.interfaces.MainMenuView;

public class MainMenuViewImpl implements MainMenuView {

	private JFrame mainMenuFrame;
	private JLabel lblTitle;
	private JButton btnPlanes;
	private JButton btnFlights;
	private JButton btnLogout;
	private JButton btnStaff;
	private JButton btnDestinations;
	private JButton btnBookings;
	private JButton btnPilotes;
	private JButton btnIncomes;
	private JButton btnAttendants;

	public MainMenuViewImpl() {

		initComponents();
		new FrameSettings(mainMenuFrame, 720, 530, true);
		createLayout();
	}

	private void createLayout() {

		GroupLayout groupLayout = new GroupLayout(mainMenuFrame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(112, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblTitle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
														.createParallelGroup(Alignment.LEADING)
														.addComponent(btnAttendants, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(groupLayout
																.createParallelGroup(Alignment.LEADING, false)
																.addComponent(btnPilotes, GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(btnPlanes, GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(btnStaff, GroupLayout.DEFAULT_SIZE, 166,
																		Short.MAX_VALUE)))
														.addGap(158)
														.addGroup(groupLayout
																.createParallelGroup(Alignment.TRAILING, false)
																.addComponent(btnBookings, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
																.addComponent(btnFlights, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
																.addComponent(btnDestinations, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
																.addComponent(btnIncomes, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
										.addGap(104))
								.addGroup(Alignment.TRAILING,
										groupLayout
												.createSequentialGroup().addComponent(btnLogout,
														GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
												.addGap(293)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(34)
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addGap(52)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnFlights, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPlanes, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addGap(42)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnBookings, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPilotes, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addGap(42)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnStaff, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnDestinations, GroupLayout.PREFERRED_SIZE, 33,
												GroupLayout.PREFERRED_SIZE))
						.addGap(38)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnAttendants, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(btnIncomes, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
						.addGap(49).addComponent(btnLogout).addGap(19)));
		mainMenuFrame.getContentPane().setLayout(groupLayout);
	}

	private void initComponents() {
		mainMenuFrame = new JFrame("Main Menù");

		lblTitle = new JLabel("Airline Menù");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Dialog", Font.ITALIC, 50));

		btnPlanes = new JButton("Aerei");
		btnFlights = new JButton("Voli");
		btnPilotes = new JButton("Piloti");
		btnBookings = new JButton("Prenotazioni");
		btnStaff = new JButton("Staff");
		btnDestinations = new JButton("Destinazioni");
		btnLogout = new JButton("Logout");
		btnIncomes = new JButton("Incassi Mensili");
		btnAttendants = new JButton("Assistenti di volo");
	}

	public void addPlaneListener(ActionListener planeListener) {
		btnPlanes.addActionListener(planeListener);
	}

	public void addDestinationListener(ActionListener destListener) {
		btnDestinations.addActionListener(destListener);
	}

	public void addPilotListener(ActionListener pilotListener) {
		btnPilotes.addActionListener(pilotListener);
	}

	public void addFlightListener(ActionListener flightListener) {
		btnFlights.addActionListener(flightListener);
	}

	public void addBookingListener(ActionListener bookingListener) {
		btnBookings.addActionListener(bookingListener);
	}

	public void addStaffListener(ActionListener staffListener) {
		btnStaff.addActionListener(staffListener);
	}

	public void addLogoutListener(ActionListener logoutListener) {
		btnLogout.addActionListener(logoutListener);
	}

	public void displayErrorMessage(String error) {
	        JOptionPane.showMessageDialog(mainMenuFrame, error);
	}

	public void close() {
		mainMenuFrame.dispose();
	}

	public void addMonthlyIncomeListener(ActionListener incomeListener) {
		btnIncomes.addActionListener(incomeListener);
	}

	public void addFlightAttendantListener(ActionListener attendatnsListener) {
		btnAttendants.addActionListener(attendatnsListener);
	}

}