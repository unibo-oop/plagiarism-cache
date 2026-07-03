package view.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import view.MainFrame;
import view.operatore.VistaSpettCorrente;
import model.Persona;

/**
 * Classe che permette di interagire con la home del cliente
 * 
 * @author Ilaria Carloni
 * 
 */
public class MainFrameCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pannello;
	private JLabel titolo;

	/**
	 * Creazione frame
	 */
	public MainFrameCliente() {
		this.setResizable(false);
		this.setTitle("HOME CLIENTE");
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) { // setta il nome
														// dell'utente
														// all'apertura della
														// finestra
				titolo.setText("CLIENTE: "
						+ Persona.getUtenteLoggato().getNome() + " "
						+ Persona.getUtenteLoggato().getCognome() + " ");
			}
		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 272, 260);
		pannello = new JPanel();
		pannello.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(pannello);
		pannello.setLayout(null);

		titolo = new JLabel("Nome Cliente");
		titolo.setHorizontalAlignment(SwingConstants.CENTER);
		titolo.setBounds(10, 11, 240, 14);
		pannello.add(titolo);

		JButton bottVediSpett = new JButton("VISUALIZZA SPETTACOLI");
		bottVediSpett.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new VistaSpettCorrente().setVisible(true);
				chiudi();
			}
		});
		bottVediSpett.setBounds(40, 44, 180, 45);
		pannello.add(bottVediSpett);

		JButton bottPrenotazioni = new JButton("LE MIE PRENOTAZIONI");
		bottPrenotazioni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chiudi();
				new VisualizzaPrenotazioni().setVisible(true);
			}
		});
		bottPrenotazioni.setBounds(40, 105, 180, 45);
		pannello.add(bottPrenotazioni);

		JButton bottLogout = new JButton("ESCI");
		bottLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persona.setUtenteLoggato(null);
				chiudi();
				new MainFrame().setVisible(true);
			}
		});
		bottLogout.setBounds(40, 166, 180, 45);
		pannello.add(bottLogout);
	}

	/**
	 * Metodo che chiude la finestra
	 */
	public void chiudi() {
		setVisible(false);
		dispose();
	}
}