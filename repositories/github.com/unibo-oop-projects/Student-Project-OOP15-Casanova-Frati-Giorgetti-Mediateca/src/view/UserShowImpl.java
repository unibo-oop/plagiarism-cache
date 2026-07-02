package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * Class which implements methods for user showing interface.
 *
 * @author Luca Giorgetti
 *
 */
public class UserShowImpl implements UserShow {
	private static final int FRAME_LENGHT = 500;
	private static final int FRAME_WIDTH = 600;
	private String name;
	private String surname;
	private String password;
	private String username;
	private String bookPref1;
	private String bookPref2;
	private String bookPref3;
	private String filmPref1;
	private String filmPref2;
	private String filmPref3;
	private String telephone;
	private String email;
	private String birthDate;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void startUserShow(final View v) {

		final JFrame mainFrame = new JFrame();
		JPanel mainPanel = new JPanel();
		mainFrame.setSize(UserShowImpl.FRAME_LENGHT, UserShowImpl.FRAME_WIDTH);
		mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setResizable(false);
		mainFrame.setTitle("Utente");

		mainFrame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		final JLabel nameL = new JLabel("Name: " + this.name);
		nameL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		nameL.setBounds(20, 14, 362, 25);

		mainFrame.getContentPane().add(nameL);

		final JLabel surnameL = new JLabel();
		surnameL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		surnameL.setBounds(20, 44, 362, 25);
		surnameL.setText("Cognome: " + this.surname);
		mainFrame.getContentPane().add(surnameL);

		final JLabel usernameL = new JLabel("Username: " + this.username);
		usernameL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		usernameL.setBounds(20, 74, 362, 25);
		mainFrame.getContentPane().add(usernameL);

		final JLabel passwordL = new JLabel("Password: " + this.password);
		passwordL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		passwordL.setBounds(20, 104, 362, 25);
		mainFrame.getContentPane().add(passwordL);

		final JLabel telephoneL = new JLabel("Telefono: " + this.telephone);
		telephoneL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		telephoneL.setBounds(20, 194, 362, 25);
		mainFrame.getContentPane().add(telephoneL);

		final JLabel emailL = new JLabel("Email: " + this.email);
		emailL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		emailL.setBounds(20, 134, 362, 25);
		mainFrame.getContentPane().add(emailL);

		final JLabel birthDateL = new JLabel("Data di nascita: "
				+ this.birthDate);
		birthDateL.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		birthDateL.setBounds(20, 164, 362, 25);
		mainFrame.getContentPane().add(birthDateL);

		final JLabel bookPref1L = new JLabel(this.bookPref1.toString());
		bookPref1L.setHorizontalAlignment(SwingConstants.CENTER);
		bookPref1L.setBounds(20, 254, 362, 25);
		bookPref1L.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		mainFrame.getContentPane().add(bookPref1L);

		final JLabel bookPref2L = new JLabel(this.bookPref2.toString());
		bookPref2L.setHorizontalAlignment(SwingConstants.CENTER);
		bookPref2L.setBounds(20, 284, 362, 25);
		bookPref2L.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		mainFrame.getContentPane().add(bookPref2L);

		final JLabel bookPref3L = new JLabel(this.bookPref3.toString());
		bookPref3L.setHorizontalAlignment(SwingConstants.CENTER);
		bookPref3L.setBounds(20, 314, 362, 25);
		bookPref3L.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		mainFrame.getContentPane().add(bookPref3L);

		final JLabel filmPref1L = new JLabel(this.filmPref1.toString());
		filmPref1L.setHorizontalAlignment(SwingConstants.CENTER);
		filmPref1L.setBounds(20, 374, 362, 25);
		filmPref1L.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		mainFrame.getContentPane().add(filmPref1L);

		final JLabel filmPref2L = new JLabel(this.filmPref2.toString());
		filmPref2L.setHorizontalAlignment(SwingConstants.CENTER);
		filmPref2L.setBounds(20, 404, 362, 25);
		filmPref2L.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));
		mainFrame.getContentPane().add(filmPref2L);

		final JLabel filmPref3L = new JLabel(this.filmPref3.toString());
		filmPref3L.setHorizontalAlignment(SwingConstants.CENTER);
		filmPref3L.setBounds(20, 434, 362, 25);
		filmPref3L.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.SMALL_SIZE));

		mainFrame.getContentPane().add(filmPref3L);

		JLabel bookPreferences = new JLabel("Preferenze genere libri:");
		bookPreferences.setBounds(20, 224, 362, 25);
		bookPreferences.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.SMALL_SIZE));
		mainFrame.getContentPane().add(bookPreferences);

		JLabel filmPreferences = new JLabel("Preferenze genere film:");
		filmPreferences.setBounds(20, 344, 362, 25);
		filmPreferences.setFont(new Font("Tahoma", Font.PLAIN,
				ViewImpl.SMALL_SIZE));
		mainFrame.getContentPane().add(filmPreferences);
		mainFrame.setVisible(true);

	}

	@Override
	public void setUserField(final String nameS, final String surnameS,
			final String usernameS, final String passwordS,
			final String birthDateS, final String emailS,
			final String telephoneS, final String bookPref1I,
			final String bookPref2I, final String bookPref3I,
			final String filmPref1I, final String filmPref2I,
			final String filmPref3I) {
		this.name = nameS;
		this.surname = surnameS;
		this.username = usernameS;
		this.password = passwordS;
		this.birthDate = birthDateS;
		this.email = emailS;
		this.telephone = telephoneS;
		this.bookPref1 = bookPref1I;
		this.bookPref2 = bookPref2I;
		this.bookPref3 = bookPref3I;
		this.filmPref1 = filmPref1I;
		this.filmPref2 = filmPref2I;
		this.filmPref3 = filmPref3I;

	}
}