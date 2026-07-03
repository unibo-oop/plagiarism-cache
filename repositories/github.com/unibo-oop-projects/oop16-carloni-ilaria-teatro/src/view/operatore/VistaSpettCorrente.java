package view.operatore;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Persona;
import model.Spettacolo;
import model.Cliente;
import view.operatore.FramePrenotazione;
import javax.swing.JScrollPane;
import control.GestioneSpettacolo;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe che permette di gestire gli spettacoli che devono ancora andare uin
 * scena
 * 
 * @author Ilaria Carloni
 * 
 */
public class VistaSpettCorrente extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel pannello;
	private JTextField cerca;
	private JTable spettacoli;
	public static final int MAX_POSTI = 100;

	/**
	 * Crea il frame.
	 */
	public VistaSpettCorrente() {
		this.setResizable(false);
		this.setTitle("SPETTACOLI ATTUALI");
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				aggTabSpett(GestioneSpettacolo.istanzaSpettacolo()
						.getSpettCorr());
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 814, 372);
		pannello = new JPanel();
		pannello.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(pannello);
		pannello.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 632, 270);
		pannello.add(scrollPane);

		spettacoli = new JTable();
		scrollPane.setViewportView(spettacoli);
		spettacoli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // permette
																			// di
																			// selezionare
																			// solo
																			// uno
																			// spettacolo
																			// alla
																			// volta
		cerca = new JTextField();
		cerca.setBounds(10, 11, 632, 20);
		pannello.add(cerca);
		cerca.setColumns(10);

		JButton btnCerca = new JButton("Cerca");
		btnCerca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				aggTabSpett(GestioneSpettacolo.istanzaSpettacolo().cerca(
						cerca.getText()));
			}
		});
		btnCerca.setBounds(662, 10, 120, 45);
		pannello.add(btnCerca);

		JButton bottPrenota = new JButton("Prenota");
		bottPrenota.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Persona.getClienteLoggato()) { // Se l'utente loggato è un
													// cliente prenota subito
					DefaultTableModel model = (DefaultTableModel) spettacoli
							.getModel();
					if (spettacoli.getSelectedRow() >= 0) {
						Cliente cl = (Cliente) Persona.getUtenteLoggato();
						int codCliente = cl.getCodice();
						int codiceSpettacolo = (int) model.getValueAt(
								spettacoli.getSelectedRow(), 0);
						new FrameNumBiglietti(codiceSpettacolo, -1, codCliente)
								.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(VistaSpettCorrente.this,
								"Selezionare uno spettacolo");
					}
				} else { // Se l'utente loggato è un operatore apri il frame
							// apposito
					new FramePrenotazione().setVisible(true);
					chiudi();
				}
			}

		});
		bottPrenota.setBounds(662, 134, 120, 45);
		pannello.add(bottPrenota);

		JButton bottAggiorna = new JButton("Aggiorna");
		bottAggiorna.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cerca.setText("");
				aggTabSpett(GestioneSpettacolo.istanzaSpettacolo().getLista());
			}
		});
		bottAggiorna.setBounds(662, 191, 120, 45);
		pannello.add(bottAggiorna);

		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (Persona.getClienteLoggato()) {
					new view.cliente.MainFrameCliente().setVisible(true);
					chiudi();
				} else {
					new MainFrameOperatore().setVisible(true);
					chiudi();
				}
			}
		});
		btnIndietro.setBounds(662, 248, 120, 45);
		pannello.add(btnIndietro);
	}

	/**
	 * Aggiorna la tabella degli spettacoli
	 * 
	 * @param lista
	 */
	public void aggTabSpett(Collection<Spettacolo> lista) {
		DefaultTableModel modello = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			// L'utente non può selezionare le celle
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String intest[] = new String[] { "Codice", "Nome", "Compagnia", "Data",
				"Prezzo", "Posti Disponibili" };
		modello.setColumnIdentifiers(intest); // aggiunge l'intetazione al
												// modello
		spettacoli.getTableHeader().setReorderingAllowed(false); // ordine delle
																	// colonne
																	// fisso
		spettacoli.setModel(modello);
		for (Spettacolo sp : lista) {
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			String dataFormattata;
			dataFormattata = formatoData
					.format(new Date(sp.getData().getTime()));
			if (Calendar.getInstance().getTimeInMillis() <= sp.getData()
					.getTime()) {

				modello.addRow(new Object[] { sp.getCodice(), sp.getNomeSp(),
						sp.getCompagnia(), dataFormattata, sp.getPrezzo(),
						MAX_POSTI - sp.getPostiVenduti() });
			}
		}
	}

	/**
	 * Chiude del frame
	 */
	public void chiudi() {
		setVisible(false);
		dispose();
	}
}