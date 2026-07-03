package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import control.GestioneCliente;
import control.GestioneOperatore;
import model.Persona;
import view.operatore.MainFrameOperatore;
import view.cliente.MainFrameCliente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe che permette l'avvio dell'applicazione
 * 
 * @author Ilaria Carloni
 * 
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pannello;
	private JTextField username;
	private JPasswordField password;
	private final ButtonGroup seleziona = new ButtonGroup();
	private JRadioButton radioO;
	private JRadioButton radioC;

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}

	/**
	 * Metodo che crea il frame
	 */
	public MainFrame() {
		this.setTitle("ACCESSO");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false); // impedisce il ridimensionamento
		this.setBounds(100, 100, 350, 270); // specifica la posizione
		pannello = new JPanel();
		pannello.setBorder(new EmptyBorder(7, 7, 7, 7));
		this.setContentPane(pannello);
		pannello.setLayout(null);

		JLabel labUser = new JLabel("Inserire Username : ");
		labUser.setBounds(10, 29, 120, 20);
		pannello.add(labUser);

		username = new JTextField();
		username.setBounds(130, 30, 186, 20);
		pannello.add(username);
		username.setColumns(12);

		JLabel labPassword = new JLabel("Inserire Password : ");
		labPassword.setBounds(10, 60, 130, 20);
		pannello.add(labPassword);

		password = new JPasswordField();
		password.setBounds(130, 61, 186, 20);
		pannello.add(password);
		password.setColumns(15);

		radioC = new JRadioButton("Cliente");
		radioC.setSelected(true);
		seleziona.add(radioC);
		radioC.setBounds(120, 90, 109, 23);
		pannello.add(radioC);

		radioO = new JRadioButton("Operatore");
		seleziona.add(radioO);
		radioO.setBounds(120, 115, 109, 23);
		pannello.add(radioO);

		JButton bottRegistrati = new JButton("REGISTRATI");
		bottRegistrati.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegistrazioneFrame.getFrame();
			}
		});
		bottRegistrati.setBounds(10, 180, 110, 20);
		pannello.add(bottRegistrati);

		JButton bottLogin = new JButton("LOGIN");
		bottLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user;
				String pass;
				user = username.getText();
				pass = new String(password.getPassword()); // casting esplicito
															// per avere la
															// stringa e non i
															// caratteri singoli
				if (user.equals("") || pass.equals("")) {
					JOptionPane.showMessageDialog(MainFrame.this,
							"Riempire Username e Password");
				} else {
					Persona persona = null;
					if (radioC.isSelected()) {
						persona = GestioneCliente.istanzaCliente()
								.controlloLoginCl(user, pass);
					} else {
						persona = GestioneOperatore.istanzaOperatore()
								.controlloLoginOp(user, pass);
					}
					if (persona == null) {
						JOptionPane.showMessageDialog(MainFrame.this,
								"(Username e/o Password errati");
					} else {
						Persona.setUtenteLoggato(persona);
						Persona.setClienteLoggato(radioC.isSelected());
						if (radioC.isSelected()) {
							new MainFrameCliente().setVisible(true);
						} else {
							new MainFrameOperatore().setVisible(true);
						}
						chiudi();
					}
				}
			}
		});
		bottLogin.setBounds(132, 180, 90, 20);
		pannello.add(bottLogin);

		JButton bottAnnulla = new JButton("ESCI");
		bottAnnulla.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				chiudi();
			}
		});

		bottAnnulla.setBounds(235, 180, 90, 20);
		pannello.add(bottAnnulla);
	}

	/**
	 * Metodo che chiude la finestra
	 */
	public void chiudi() {
		setVisible(false);
		dispose();
	}
}
