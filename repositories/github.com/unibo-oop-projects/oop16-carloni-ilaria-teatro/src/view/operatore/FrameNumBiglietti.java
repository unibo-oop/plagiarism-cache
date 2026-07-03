package view.operatore;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import model.Persona;
import model.Prenotazione;
import control.GestioneCliente;
import control.GestionePrenotazione;
import control.GestioneSpettacolo;
import exception.ExceptionNumNegativo;
import exception.ExceptionPostiNonDisponibili;

/**
 * Classe che permette di impostare il numero di posti prenotabili
 * 
 * @author Ilaria Carloni
 * 
 */
public class FrameNumBiglietti extends JFrame {

	int codiceSp;
	int codiceOp;
	int codiceCl;
	private JLabel titolo;
	int quantita;

	private static final long serialVersionUID = 1L;

	/**
	 * Crea il frame
	 * 
	 * @param codiceSp
	 * @param codiceOp
	 * @param codiceCl
	 */
	public FrameNumBiglietti(final int codiceSp, final int codiceOp,
			final int codiceCl) {
		this.codiceSp = codiceSp;
		this.codiceOp = codiceOp;
		this.codiceCl = codiceCl;

		this.setResizable(false);
		this.setTitle("COMPRA BIGLIETTI");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 400, 300);
		JPanel pannello = new JPanel();
		pannello.setBorder(new EmptyBorder(7, 7, 7, 7));
		this.setContentPane(pannello);
		pannello.setLayout(null);

		titolo = new JLabel("Selezionare il numero di biglietti da comprare: ");
		titolo.setBounds(80, 30, 300, 45);
		pannello.add(titolo);

		final JSpinner spinner = new JSpinner();
		spinner.setValue(new Integer(1));
		spinner.setBounds(180, 100, 50, 45);
		pannello.add(spinner);

		JButton bottOk = new JButton("OK");
		bottOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					quantita = (int) spinner.getValue();
					GestioneSpettacolo.istanzaSpettacolo().get(codiceSp)
							.aggiungiPosto(quantita);
				} catch (ExceptionPostiNonDisponibili ecc) {
					JOptionPane.showMessageDialog(FrameNumBiglietti.this,
							"Non ci sono abbastanza posti");
					return;
				} catch (ExceptionNumNegativo exc) {
					JOptionPane.showMessageDialog(FrameNumBiglietti.this,
							"Inserire un numero positivo!");
					return;
				}

				GestionePrenotazione.istanzaPrenotazione()
						.aggiungiPrenotazione(
								new Prenotazione(codiceCl, codiceOp, codiceSp,
										quantita));
				GestioneSpettacolo.istanzaSpettacolo().salva();
				GestioneCliente.istanzaCliente().salva();
				GestionePrenotazione.istanzaPrenotazione().salva();
				JOptionPane.showMessageDialog(FrameNumBiglietti.this,
						"Spettacolo prenotato correttamente!");
				chiudi();
			}
		});
		bottOk.setBounds(80, 210, 120, 45);
		pannello.add(bottOk);

		JButton bottIndietro = new JButton("Indietro");
		bottIndietro.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (Persona.getClienteLoggato()) {
					new VistaSpettCorrente();
					chiudi();
				} else {
					new FramePrenotazione();
					chiudi();
				}
			}
		});
		bottIndietro.setBounds(220, 210, 120, 45);
		pannello.add(bottIndietro);
	}

	/**
	 * Chiude il frame
	 */
	public void chiudi() {
		setVisible(false);
		dispose();
	}
}