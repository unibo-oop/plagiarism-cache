package View.cardlayout;

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

public class ViewLogin extends JPanel {
	
	private static final int index = 0;
	
	//CS
	private JButton btnNewButton;
	private JPasswordField pfPassword;
	private JTextField tfUsername;
	
	public ViewLogin() {
		
		this.setLayout(null);
		this.setSize(800, 800);
		
		this.btnNewButton = new JButton("LOGIN");
		this.btnNewButton.setBounds(30, 230, 89, 23);
		this.add(btnNewButton);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setForeground(Color.RED);
		lblUsername.setBounds(145, 199, 95, 29);
		this.add(lblUsername);
		
		
		this.tfUsername = new JTextField(20);
		this.tfUsername.setBounds(245, 199, 95, 23);
		this.tfUsername.setForeground(Color.BLACK);
		this.add(this.tfUsername);
		
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setForeground(Color.GREEN);
		lblPassword.setBounds(145, 241, 95, 23);
		this.add(lblPassword);
		
		
		this.pfPassword = new JPasswordField(20);
		this.pfPassword.setBounds(245, 241, 95, 23);
		this.pfPassword.setForeground(Color.BLACK);
		this.add(this.pfPassword);
	
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/images/john_300_200.png")));
		label.setBounds(30, 11, 300, 171);
		this.add(label);
		
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
    
    public static int getIndex()
    {
    	return index;
    }
    
    
}

