package View;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import util.ImageLoader;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;

public class ViewLogin {
	
	Container contenuto = null;
	
	//CS
	private JButton btnNewButton;
	private JPasswordField pfPassword;
	private JTextField tfUsername;
	
	private JFrame frame;
	
	public ViewLogin() {
		
		this.frame = new JFrame("Risto");
		contenuto = frame.getContentPane();
		frame.getContentPane().setLayout(null);
		
		this.btnNewButton = new JButton("LOGIN");
		btnNewButton.setBounds(30, 230, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setForeground(Color.RED);
		lblUsername.setBounds(145, 199, 95, 29);
		frame.getContentPane().add(lblUsername);
		
		
		this.tfUsername = new JTextField(20);
		this.tfUsername.setBounds(245, 199, 95, 29);
		this.tfUsername.setForeground(Color.BLACK);
		frame.getContentPane().add(this.tfUsername);
		
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setForeground(Color.GREEN);
		lblPassword.setBounds(145, 241, 95, 23);
		frame.getContentPane().add(lblPassword);
		
		
		this.pfPassword = new JPasswordField(20);
		this.pfPassword.setBounds(245, 241, 95, 23);
		this.pfPassword.setForeground(Color.BLACK);
		frame.getContentPane().add(this.pfPassword);
	
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ImageLoader.getImage("john_300_200.png")));
		label.setBounds(30, 11, 300, 171);
		frame.getContentPane().add(label);
		
		//Christian Schepp
		frame.setSize(500, 500);
		frame.setVisible(true);
		
	}
	
	public JFrame getFrame()
	{
		return this.frame;
	}
	
	public void addButtonListener(ActionListener listener) {
        this.btnNewButton.addActionListener(listener);
    }
	
	public String getUsername() {
        return this.tfUsername.getText().trim();
    }
 
    public String getPassword() {
        return new String(this.pfPassword.getPassword());
    }
}
