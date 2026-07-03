package sharelist.view.login;

import sharelist.controller.ViewObserver;
import sharelist.view.register.Register;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Registrazione di un nuovo utente
 * 
 * @author Elton Nallbati
 * @version 1.0
 */
public class LoginView extends JFrame implements LoginViewInterface {
	
	private static final long serialVersionUID = -1330823594864047263L;
	final public static String WINDOW_TITLE = "Login";
	
	private final JPanel contentPanel = new JPanel();
	
	private JLabel JLabel_Username;
	private JTextField textField_Username;
	private JLabel JLabel_Password;
	private JPasswordField textField_Password;
	private JButton JButton_Login;
	private JButton JButton_Register;
	
	final private Register registerDialog = new Register(this);
	private ViewObserver controller;
	
	/**
	 * Costruttore LoginView
	 */
	public LoginView(){
		super(WINDOW_TITLE);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.buildLayout();
		this.setHandlers();
		this.setVisible(true);
	}
	
	/**
	 *  Permette al Controller di fare scomparire LoginView dopo essersi logati correttamente
	 */
	@Override
	public void loginResponse(){
		setVisible(false);
		dispose();
	}
	
	/**
	 *  Permette di fare visualizzare all'utente messagi d'errore provenienti dal server
	 *  
	 * @param message
	 * 				messaggio da visualizzare
	 */
	@Override
	public void commandFailed(String message) {
		JOptionPane.showMessageDialog(this, message, "Errore", JOptionPane.ERROR_MESSAGE);		
	}
	
	/**
	 *  Permette di fare visualizzare all'utente l'esito della registrazione proveniente dal server
	 *  
	 * @param message
	 * 				messaggio da visualizzare
	 */
	@Override
	public void commandRegisterOk(String message) {
		JOptionPane.showMessageDialog(this, message, "Esito Registrazione", JOptionPane.INFORMATION_MESSAGE);		
	}
	
	/**
	 *  Resetto i campi username e password
	 */
	@Override
	public void clearData() {
		textField_Username.setText("");
		textField_Password.setText("");
	}
	
	/**
	 *  Assegno il controllore a LoginView
	 *  
	 * @param controller
	 * 				Controllore
	 */
	@Override
	public void attachViewObserver(ViewObserver controller){
		this.controller = controller;
	}
	
	/**
	 *  Costruisco il Layout
	 */
	private void buildLayout(){		
		setSize(new Dimension(300, 150));
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
			gbl_data.rowHeights = new int[] {0, 0, 10};
			gbl_data.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_data.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			data.setLayout(gbl_data);
			{
				JLabel_Username = new JLabel("Email");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 0;
				data.add(JLabel_Username, gbc_label);
			}
			{
				textField_Username = new JTextField();
				textField_Username.setColumns(15);
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.insets = new Insets(0, 0, 5, 0);
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 1;
				gbc_textField.gridy = 0;
				data.add(textField_Username, gbc_textField);
			}
			{
				JLabel_Password = new JLabel("Password");
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.anchor = GridBagConstraints.EAST;
				gbc_label.insets = new Insets(0, 0, 0, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 1;
				data.add(JLabel_Password, gbc_label);
			}
			{
				textField_Password = new JPasswordField();
				textField_Password.setColumns(15);
				GridBagConstraints gbc_textField_1 = new GridBagConstraints();
				gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_1.gridx = 1;
				gbc_textField_1.gridy = 1;
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
				JButton_Login = new JButton("Login");
				buttonPane.add(JButton_Login);
				getRootPane().setDefaultButton(JButton_Login);
			}
			{
				JButton_Register = new JButton("Register");
				buttonPane.add(JButton_Register);
			}
		}
	}
	
	/**
	 *  Setto gli ascoltatori
	 */
	private void setHandlers(){
		this.JButton_Login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				controller.commandLogin(textField_Username.getText(), textField_Password.getText());
			}
		});
		this.JButton_Register.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				registerDialog.reinit();
				registerDialog.setVisible(true);
			}
		});
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				quitHandler();
			}
		});
		this.registerDialog.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentHidden(ComponentEvent e){
				if (registerDialog.getOKState()){
					Object[] o = new Object[]{	
												registerDialog.getName(),
												registerDialog.getSurname(), 
												registerDialog.getUsername(),
												registerDialog.getPassword()
					};
					controller.commandRegister(o);
				}
			}
		});
	}
	
	/**
	 *  Chiedo all'utente conferma per uscire dall'applicazione
	 */
	private void quitHandler(){
		int n = JOptionPane.showConfirmDialog(this,
			    "Vuoi chiudere veramente?",
			    "Sto chiudendo..", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION){
			controller.commandQuit();
		}
	}
}
