package view.operatore;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.Persona;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import view.MainFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe che permette di interagire con la home dell'operatore
 * 
 * @author Ilaria Carloni
 * 
 */
public class MainFrameOperatore extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pannello;
	private JLabel utente;

	/**
	 * Apre il frame
	 */
	public MainFrameOperatore() {
		this.setResizable(false);
		this.setTitle("HOME OPERATORE");
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				utente.setText("OPERATORE: "
						+ Persona.getUtenteLoggato().getNome() + " "
						+ Persona.getUtenteLoggato().getCognome() + " ");
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 272, 320);
		pannello = new JPanel();
		pannello.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(pannello);
		pannello.setLayout(null);

		utente = new JLabel("Nome Operatore");
		utente.setHorizontalAlignment(SwingConstants.CENTER);
		utente.setBounds(10, 11, 240, 14);
		pannello.add(utente);

		JButton bottAggiungiSpett = new JButton("AGGIUNGI SPETTACOLO");
		bottAggiungiSpett.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chiudi();
				new AggiuntaSpettacolo().setVisible(true);
			}
		});
		bottAggiungiSpett.setBounds(15, 44, 237, 45);
		pannello.add(bottAggiungiSpett);

		JButton bottVediStorico = new JButton("VISUALIZZA STORICO SPETTACOLI");
		bottVediStorico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new VistaStoricoSpettacoli().setVisible(true);
				chiudi();
			}
		});
		bottVediStorico.setBounds(15, 105, 237, 45);
		pannello.add(bottVediStorico);

		JButton bottVediSpettacoli = new JButton(
				"VISUALIZZA SPETTACOLI CORRENTI");
		bottVediSpettacoli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chiudi();
				new VistaSpettCorrente().setVisible(true);
			}
		});
		bottVediSpettacoli.setBounds(15, 166, 237, 45);
		pannello.add(bottVediSpettacoli);

		JButton bottLogout = new JButton("LOGOUT");
		bottLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persona.setUtenteLoggato(null);
				;
				chiudi();
				new MainFrame().setVisible(true);
			}
		});
		bottLogout.setBounds(15, 225, 237, 45);
		pannello.add(bottLogout);
	}

	/**
	 * Chiude il frame
	 */
	public void chiudi() {
		setVisible(false);
		dispose();
	}

}
