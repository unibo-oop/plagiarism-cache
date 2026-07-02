package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import view.ViewImpl.CardName;

/**
 * Class for the Login panel, implements Userlogin.
 *
 * @author Luca Giorgetti
 *
 */
public class UserLoginImpl extends JPanel implements UserLogin {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final JPasswordField passwordTextField;
	private JTextField usernameTextField;
	private char[] password;

	/**
	 * enumeration for choosing login type.
	 *
	 * @author Luca Giorgetti
	 *
	 */
	public enum LoginType {
		/**
		 *
		 */
		USER, MANAGER
	}

	/**
	 * Create the panel. Need the calling class
	 *
	 * @param v
	 *            the calling class
	 * @param type
	 *            the type of login, user o manager
	 */
	public UserLoginImpl(final View v, final LoginType type) {
		final JLabel mainLabel;
		JLabel passwordLabel;
		final JLabel usernameLabel;
		final JButton backToSelectAccount;
		final JButton send;

		mainLabel = new JLabel("Esegui l'accesso:");
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setFont(new Font("Tahoma", Font.BOLD, ViewImpl.TITLE_SIZE));
		mainLabel.setBounds(12, 13, 776, 62);
		send = new JButton("Invio");
		send.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		send.setBounds(277, 225, 230, 45);
		this.add(send);
		this.usernameTextField = new JTextField();
		this.usernameTextField.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.FONT_SIZE));
		usernameLabel = new JLabel("Username:");
		usernameLabel
		.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));

		passwordLabel = new JLabel("Password:");
		passwordLabel
		.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));

		this.add(passwordLabel);
		if (type.equals(LoginType.USER)) {
			passwordLabel.setBounds(142, 151, 162, 45);
			usernameLabel.setBounds(142, 88, 162, 45);
			this.add(usernameLabel);
			this.usernameTextField.setBounds(315, 88, 329, 45);
			this.add(this.usernameTextField);
			send.addActionListener(e -> v.sendLogin());
		} else if (type.equals(LoginType.MANAGER)) {
			this.usernameTextField.setVisible(false);
			passwordLabel = new JLabel("Password di Sistema:");
			passwordLabel.setFont(new Font("Tahoma", Font.PLAIN,
					ViewImpl.FONT_SIZE));
			passwordLabel.setBounds(54, 151, 252, 45);
			send.addActionListener(e -> v.sendManagerLogin());
		}

		this.passwordTextField = new JPasswordField();
		this.passwordTextField.setBounds(316, 151, 329, 45);
		this.passwordTextField.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.FONT_SIZE));
		backToSelectAccount = new JButton("Torna alla scelta");
		backToSelectAccount.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.FONT_SIZE));
		backToSelectAccount.addActionListener(e -> v.swapView(CardName.START));
		backToSelectAccount.setBounds(505, 503, 252, 45);
		this.setLayout(null);
		this.add(mainLabel);
		this.add(passwordLabel);
		this.add(this.passwordTextField);
		this.add(backToSelectAccount);

		this.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);

	}

	@Override
	public String getUserUsername() {
		return this.usernameTextField.getText();
	}

	@Override
	public String getUserPassword() {
		this.password = this.passwordTextField.getPassword();
		String pwd = new String(this.password);
		return pwd;
	}

	@Override
	public String getManagerPassword() {
		this.password = this.passwordTextField.getPassword();
		String pwd = new String(this.password);
		return pwd;
	}

}
