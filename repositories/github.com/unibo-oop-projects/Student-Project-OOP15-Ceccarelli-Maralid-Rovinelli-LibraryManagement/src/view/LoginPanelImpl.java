/**
 *@author Ceccarelli 
 */
package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import view.observer.LoginObserver;
import javax.swing.JButton;
import java.awt.SystemColor;

public class LoginPanelImpl extends JPanel implements LoginPanel, ActionListener{
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private static final long serialVersionUID = 1;
	private JButton btnLogin;
	private JButton btnClear;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private LoginObserver observer;
	private JButton btnRecorded;
	private JLabel lblRecorded;
	private JLabel lblTitle;

	public LoginPanelImpl() {
		super();
		setBackground(SystemColor.activeCaption);
		this.setLayout(null);
		
		
		lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Calibri", Font.ITALIC, 14));
		lblPassword.setBounds(716, 130, 160, 25);
		add(lblPassword);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setFont(new Font("Calibri", Font.ITALIC, 14));
		lblUsername.setBounds(23, 130, 160, 25);
		add(lblUsername);
		
		txtPassword = new JPasswordField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setFont(new Font("Calibri", Font.PLAIN, 13));
		txtPassword.setBounds(716, 155, 160, 20);
		add(txtPassword);
		
		txtUsername = new JTextField();
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setFont(new Font("Calibri", Font.PLAIN, 13));
		add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.setBounds(23, 155, 160, 20);
		
		
		btnLogin = new JButton("Accedi");
		btnLogin.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		btnLogin.setBounds(408, 145, 120, 40);
		btnLogin.addActionListener(this);
		add(btnLogin);
		
		btnClear = new JButton("Pulisci tutto");
		btnClear.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		btnClear.setBounds(739, 476, 137, 55);
		btnClear.addActionListener(this);
		add(btnClear);
		
		btnRecorded = new JButton("Registrati");
		btnRecorded.setEnabled(true);
		btnRecorded.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		btnRecorded.setBounds(23, 488, 143, 43);
		btnRecorded.addActionListener(this);
		add(btnRecorded);
		
		lblRecorded = new JLabel("Registra nuovo dipendente");
		lblRecorded.setFont(new Font("Calibri", Font.ITALIC, 13));
		lblRecorded.setBounds(23, 459, 143, 25);
		add(lblRecorded);
		
		lblTitle = new JLabel("Login");
		lblTitle.setForeground(new Color(255, 69, 0));
		lblTitle.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 35));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 880, 75);
		add(lblTitle);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object isPressed = e.getSource();

		if (isPressed == btnLogin) {
			this.observer.loginEmployee(txtUsername.getText().toString(), txtPassword.getPassword());
		} else if (isPressed == btnClear) {
			this.clearLogin();
		}else if (isPressed == btnRecorded){
			this.observer.registerEmployeeClicked();
		}		
	}

	@Override
	public void clearLogin() {
		this.txtUsername.setText("");
		this.txtPassword.setText("");
		
	}

	@Override
	public void attachObserver(LoginObserver observer) {
		this.observer = observer;
		
	}
	
	@Override
	public void displayMessage(String message){
		JOptionPane.showMessageDialog(null, message, "Attenzione", JOptionPane.PLAIN_MESSAGE);
	}
}
