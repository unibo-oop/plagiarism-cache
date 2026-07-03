package view.cliente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import control.GestionePrenotazione;
import control.GestioneSpettacolo;
import model.Cliente;
import model.Persona;
import model.Prenotazione;

/**
 * Classe che permette di visualizzare le prenotazioni di un singolo cliente
 * 
 * @author Ilaria Carloni
 * 
 */
public class VisualizzaPrenotazioni extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel pannello;
	private JTable tabella;

	/**
	 * Crea il frame
	 */
	public VisualizzaPrenotazioni() {
		this.setResizable(false);
		this.setTitle("I MIEI SPETTACOLI");
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Cliente cl = (Cliente) Persona.getUtenteLoggato();
				aggiornaTabella(GestionePrenotazione.istanzaPrenotazione()
						.getListaSpettCliente(cl.getCodice()));
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 904, 356);
		pannello = new JPanel();
		pannello.setBorder(new EmptyBorder(7, 7, 7, 7));
		this.setContentPane(pannello);
		pannello.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 725, 301);
		pannello.add(scrollPane);

		tabella = new JTable();
		scrollPane.setViewportView(tabella);
		tabella.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JButton bottIndietro = new JButton("Indietro");
		bottIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainFrameCliente().setVisible(true);
				chiudi();
			}
		});
		bottIndietro.setBounds(752, 47, 120, 45);
		pannello.add(bottIndietro);
	}

	/**
	 * Metodo che aggiorna la tabella delle prenotazioni
	 * 
	 * @param lista
	 */
	public void aggiornaTabella(Collection<Prenotazione> lista) {
		DefaultTableModel model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String intest[] = new String[] { "Codice Prenotazione",
				"Nome Spettacolo", "Compagnia Attori", "Data", "Prezzo",
				"Posti Prenotati" };
		model.setColumnIdentifiers(intest); // setta l'intestazione al modello
		tabella.getTableHeader().setReorderingAllowed(false); // ordine colonne
																// fisso
		tabella.setModel(model);
		for (Prenotazione pren : lista) {
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			String dataFormattata = null;
			dataFormattata = formatoData.format((new Date(GestioneSpettacolo
					.istanzaSpettacolo().get(pren.getCodSpett()).getData()
					.getTime())));
			model.addRow(new Object[] {
					pren.getCodPrenot(),
					GestioneSpettacolo.istanzaSpettacolo()
							.get(pren.getCodSpett()).getNomeSp(),
					GestioneSpettacolo.istanzaSpettacolo()
							.get(pren.getCodSpett()).getCompagnia(),
					dataFormattata,
					GestioneSpettacolo.istanzaSpettacolo()
							.get(pren.getCodSpett()).getPrezzo(),
					GestionePrenotazione.istanzaPrenotazione()
							.get(pren.getCodPrenot()).getPostiPrenotati() });
		}
	}

	/**
	 * Chiude il frame
	 */
	public void chiudi() {
		setVisible(false);
		dispose();
	}
}