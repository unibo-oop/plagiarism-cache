package view.Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Implementations.LoginControllerImpl.LoginListener;
import view.Components.PrisonManagerJFrame;
import view.Components.PrisonManagerJPanel;
import view.Interfaces.Inter.Login;

public class LoginView extends PrisonManagerJFrame implements Login{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9055948983228935131L;
	
	final PrisonManagerJPanel south;
	final JButton login = new JButton("Login");
	final PrisonManagerJPanel center;
	final JLabel username = new JLabel("Username");
	final JTextField username1 = new JTextField(8);
	final JLabel password = new JLabel("Password");
	final JPasswordField password1 = new JPasswordField(8);
	final PrisonManagerJPanel north;
	final JLabel title = new JLabel("Prison Manager");
	
	/**
	 * costruttore
	 */
	public LoginView(){
		this.setSize(400, 130);
		this.getContentPane().setLayout(new BorderLayout());
		south = new PrisonManagerJPanel(new FlowLayout());
		south.add(login);
		this.getContentPane().add(BorderLayout.SOUTH,south);
		center = new PrisonManagerJPanel(new FlowLayout());
		center.add(username);
		center.add(username1);
		center.add(password);
		center.add(password1);
		this.getContentPane().add(BorderLayout.CENTER,center);
		north = new PrisonManagerJPanel(new FlowLayout());
		north.add(title);
		this.getContentPane().add(BorderLayout.NORTH,north);
		this.setVisible(true);
	}
	
	public String getUsername(){
		return username1.getText();
	}
	
	public String getPassword(){
		return new String(password1.getPassword());
	}
	
	public void displayErrorMessage(String error){
		JOptionPane.showMessageDialog(this, error);
	}
	
	public void addLoginListener(LoginListener loginListener){
		login.addActionListener(loginListener);
	}
}
