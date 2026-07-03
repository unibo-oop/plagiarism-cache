package sharelist.view.register;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import sharelist.view.login.LoginView;

/**
 * Registrazione di un nuovo utente
 * 
 * @author Elton Nallbati
 * @version 1.0
 */
public class Register extends JDialog {

	private static final long serialVersionUID = -8891090275523428074L;
	private final JPanel contentPanel = new JPanel();
	private boolean okState = false;
	
	private JTextField textField_Nome;
	private JTextField textField_Cognome;
	private JTextField textField_Username;
	private JPasswordField textField_Password;

	/**
	 * Costruttore Register
	 * 
	 * @param view
	 * 				View
	 */
	public Register(LoginView view) {
		super(view);
		
		setSize(new Dimension(300, 250));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JPanel data = new JPanel();
			contentPanel.add(data);
			GridBagLayout gbl_data = new GridBagLayout();
			gbl_data.columnWidths = new int[] {0, 0, 10};
			gbl_data.rowHeights = new int[] {0, 0, 0, 0, 0, 10};
			gbl_data.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_data.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			data.setLayout(gbl_data);
			{
				JLabel lable_Nome = new JLabel("Nome");
				GridBagConstraints gbc_lblNome = new GridBagConstraints();
				gbc_lblNome.insets = new Insets(0, 0, 5, 5);
				gbc_lblNome.anchor = GridBagConstraints.EAST;
				gbc_lblNome.gridx = 0;
				gbc_lblNome.gridy = 1;
				data.add(lable_Nome, gbc_lblNome);
			}
			{
				textField_Nome = new JTextField();
				textField_Nome.setColumns(15);
				GridBagConstraints gbc_textField_Nome = new GridBagConstraints();
				gbc_textField_Nome.insets = new Insets(0, 0, 5, 0);
				gbc_textField_Nome.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_Nome.gridx = 1;
				gbc_textField_Nome.gridy = 1;
				data.add(textField_Nome, gbc_textField_Nome);
				textField_Nome.setColumns(15);
			}
			{
				JLabel lable_Cognome = new JLabel("Cognome");
				GridBagConstraints gbc_lblCognome = new GridBagConstraints();
				gbc_lblCognome.anchor = GridBagConstraints.EAST;
				gbc_lblCognome.insets = new Insets(0, 0, 5, 5);
				gbc_lblCognome.gridx = 0;
				gbc_lblCognome.gridy = 2;
				data.add(lable_Cognome, gbc_lblCognome);
			}
			{
				textField_Cognome = new JTextField();
				textField_Cognome.setColumns(15);
				GridBagConstraints gbc_textField_Cognome = new GridBagConstraints();
				gbc_textField_Cognome.insets = new Insets(0, 0, 5, 0);
				gbc_textField_Cognome.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_Cognome.gridx = 1;
				gbc_textField_Cognome.gridy = 2;
				data.add(textField_Cognome, gbc_textField_Cognome);
				textField_Cognome.setColumns(10);
			}
			{
				JLabel lblEmail = new JLabel("Email");
				GridBagConstraints gbc_lblEmail = new GridBagConstraints();
				gbc_lblEmail.anchor = GridBagConstraints.EAST;
				gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
				gbc_lblEmail.gridx = 0;
				gbc_lblEmail.gridy = 3;
				data.add(lblEmail, gbc_lblEmail);
			}
			{
				textField_Username = new JTextField();
				textField_Username.setColumns(15);
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.insets = new Insets(0, 0, 5, 0);
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 1;
				gbc_textField.gridy = 3;
				data.add(textField_Username, gbc_textField);
			}
			{
				JLabel label = new JLabel("Password");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 0, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 4;
				data.add(label, gbc_label);
			}
			{
				textField_Password = new JPasswordField();
				textField_Password.setColumns(15);
				GridBagConstraints gbc_textField_1 = new GridBagConstraints();
				gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_1.gridx = 1;
				gbc_textField_1.gridy = 4;
				data.add(textField_Password, gbc_textField_1);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JSeparator separator = new JSeparator();
				buttonPane.add(separator);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okState = true;  
						setVisible(false);
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okState = false;
						setVisible(false);
					}
				});
			}
		}	
	}
	
	/**
	 *  Ritorna true se abbiamo premuto OK
	 */
	public boolean getOKState(){
		return this.okState; 
	}
	
	/**
	 *  Reinizializza i componenti
	 */
	public void reinit(){
		this.textField_Nome.setText("");
		this.textField_Cognome.setText("");
		this.textField_Username.setText("");
		this.textField_Password.setText("");
	}
	
	/**
	 *  Ritorna il nome
	 */
	public String getName(){
		return this.textField_Nome.getText();
	}
	
	/**
	 *  Ritorna il cognome
	 */
	public String getSurname(){
		return this.textField_Cognome.getText();
	}
	
	/**
	 *  Ritorna username
	 */
	public String getUsername(){
		return this.textField_Username.getText();
	}
	
	/**
	 *  Ritorna la password
	 */
	public String getPassword(){
		return this.textField_Password.getText();
	}
}
