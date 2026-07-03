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
import exception.ExceptionNomeEsistente;
import model.Persona;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe che visualizza il frame per registrarsi
 * 
 * @author Ilaria Carloni
 * 
 */
public class RegistrazioneFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pannello;
	private JTextField username;
	private JTextField nome;
	private JTextField cognome;
	private JPasswordField password;
	private final ButtonGroup seleziona = new ButtonGroup();
	private JRadioButton radioC;
	private JRadioButton radioO;
	private static RegistrazioneFrame finestraReg = null;

	/**
	 * Crea il frame
	 */
	private RegistrazioneFrame() {
		this.setTitle("REGISTRAZIONE");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(100, 100, 350, 270);
		pannello = new JPanel();
		pannello.setBorder(new EmptyBorder(7, 7, 7, 7));
		this.setContentPane(pannello);
		pannello.setLayout(null);

		JLabel labNome = new JLabel("Inserire Nome : ");
		labNome.setBounds(10, 91, 120, 14);
		pannello.add(labNome);

		nome = new JTextField();
		nome.setBounds(130, 91, 136, 20);
		pannello.add(nome);
		nome.setColumns(12);

		JLabel labCognome = new JLabel("Inserire Cognome : ");
		labCognome.setBounds(10, 124, 120, 14);
		pannello.add(labCognome);

		cognome = new JTextField();
		cognome.setBounds(130, 121, 136, 20);
		pannello.add(cognome);
		cognome.setColumns(15);

		JLabel labUser = new JLabel("Inserire Username : ");
		labUser.setBounds(10, 33, 120, 14);
		pannello.add(labUser);

		username = new JTextField();
		username.setBounds(130, 30, 136, 20);
		pannello.add(username);
		username.setColumns(12);

		JLabel labPassword = new JLabel("Inserire Password : ");
		labPassword.setBounds(10, 64, 120, 14);
		pannello.add(labPassword);

		password = new JPasswordField();
		password.setBounds(130, 61, 136, 20);
		pannello.add(password);
		password.setColumns(15);

		radioC = new JRadioButton("Cliente");
		radioC.setSelected(true);
		seleziona.add(radioC);
		radioC.setBounds(130, 151, 136, 20);
		pannello.add(radioC);

		radioO = new JRadioButton("Operatore");
		seleziona.add(radioO);
		radioO.setBounds(130, 171, 136, 20);
		pannello.add(radioO);

		JButton bottOk = new JButton("OK");
		bottOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nom;
				String cognom;
				String user;
				String pass;
				user = username.getText();
				pass = new String(password.getPassword());
				nom = nome.getText();
				cognom = cognome.getText();

				if (nom.equals("") || cognom.equals("") || user.equals("")
						|| pass.equals("")) {
					JOptionPane.showMessageDialog(RegistrazioneFrame.this,
							"Riempire tutti i campi");
				} else {
					Persona persona = new Persona(nom, cognom, user, pass);

					if (radioC.isSelected()) {
						try {
							GestioneCliente.istanzaCliente().aggiungiCliente(
									persona);
						} catch (ExceptionNomeEsistente ex) {
							JOptionPane.showMessageDialog(
									RegistrazioneFrame.this,
									"Scegliere un altro Username");
							return;
						}
					} else {
						try {
							GestioneOperatore.istanzaOperatore()
									.aggiungiOperatore(persona);
						} catch (ExceptionNomeEsistente ex) {
							JOptionPane.showMessageDialog(
									RegistrazioneFrame.this,
									"Scegliere un altro Username");
							return;
						}
					}
					JOptionPane.showMessageDialog(RegistrazioneFrame.this,
							"Utente registrato con successo");
					chiudi();
				}
			}
		});
		bottOk.setBounds(235, 200, 89, 23);
		pannello.add(bottOk);

		JButton bottIndietro = new JButton("INDIETRO");
		bottIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chiudi();
				new MainFrame().setVisible(true);
			}
		});
		bottIndietro.setBounds(136, 200, 89, 23);
		pannello.add(bottIndietro);
	}

	/**
	 * Metodo che chiude la finestra
	 */
	private void chiudi() {
		RegistrazioneFrame.finestraReg = null;
		setVisible(false);
		dispose(); // distrugge la finestra (l'oggetto JFrame)
	}

	/**
	 * Metodo che permette di ritornare un'unico frame alla volta, secondo il
	 * pattern singleton
	 * 
	 * @return
	 */
	public static RegistrazioneFrame getFrame() {
		if (RegistrazioneFrame.finestraReg == null) {
			RegistrazioneFrame.finestraReg = new RegistrazioneFrame();
			RegistrazioneFrame.finestraReg.setVisible(true);
		}
		return RegistrazioneFrame.finestraReg;
	}
}
