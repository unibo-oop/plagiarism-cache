package view.implementations;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import view.interfaces.LoginView;

public class LoginViewImpl implements LoginView {

	private static final int MAX_CHARACTERS = 20;
	
	private JFrame loginFrame;
	private JLabel lblUsername;
	private JLabel lblTitle;
	private JLabel lblPassword;
	private JTextField txfUsername;
	private JButton btnLogin;
	private JButton btnExit;
	private JPasswordField passwordField;
	
	public LoginViewImpl() {
		// Set Theme
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		initializeComponents();
		createLayout();
		new FrameSettings(loginFrame, 460, 310, false);
	}

	private void initializeComponents() {
		loginFrame = new JFrame("Login");
		
		lblTitle = new JLabel("Airline Login");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.ITALIC, 50));
		
		lblUsername = new JLabel("Username:");		
		lblPassword = new JLabel("Password:");
		
		txfUsername = new JTextField(MAX_CHARACTERS);
		passwordField = new JPasswordField(MAX_CHARACTERS);
		
		btnLogin = new JButton("Login");
		btnExit = new JButton("Esci");
	}

	private void createLayout() {
		
		GroupLayout groupLayout = new GroupLayout(loginFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(102)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPassword)
								.addComponent(lblUsername))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txfUsername)
								.addComponent(passwordField)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblTitle)
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(txfUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnExit))
					.addGap(22))
		);
		loginFrame.getContentPane().setLayout(groupLayout);
	}

	public String getUsername() {
		return this.txfUsername.getText();
	}

	public String getPassword() {
		return new String(this.passwordField.getPassword());
	}

	public void addLoginListener(ActionListener loginListener) {
		this.btnLogin.addActionListener(loginListener);
	}
	
	public void addExitListener(ActionListener exitListener) {
		btnExit.addActionListener(exitListener);
	}

	public void displayErrorMessage(String error) {
		JOptionPane.showMessageDialog(loginFrame, error);
	}

	public void close() {
		loginFrame.dispose();
	}

	public void displayConfirmMessage(String confirm) {
		JOptionPane.showConfirmDialog(loginFrame, confirm);
	}
}
