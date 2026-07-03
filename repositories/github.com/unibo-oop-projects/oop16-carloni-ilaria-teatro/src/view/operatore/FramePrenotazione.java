package view.operatore;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.GestioneCliente;
import control.GestioneSpettacolo;
import model.Cliente;
import model.Operatore;
import model.Persona;
import model.Spettacolo;

/**
 * Classe che gestisce la prenotazione attraverso un operatore
 * 
 * @author Ilaria Carloni
 * 
 */
public class FramePrenotazione extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel pannello;
	private JTable clienti;
	private JTable spettacoli;
	private JTextField cercaClienti;
	private JTextField cercaSpett;
	public static final int MAX_POSTI = 100;

	/**
	 * Crea il frame
	 */
	public FramePrenotazione() {
		this.setResizable(false);
		this.setTitle("Nuova prenotazione");
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				aggiornaTabellaClienti(GestioneCliente.istanzaCliente()
						.getLista());
				aggiornaTabellaSpett(GestioneSpettacolo.istanzaSpettacolo()
						.getLista());
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1088, 325);
		pannello = new JPanel();
		pannello.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(pannello);
		pannello.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 318, 246);
		pannello.add(scrollPane);

		clienti = new JTable();
		scrollPane.setViewportView(clienti);
		clienti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permette
																		// di
																		// selzionare
																		// solo
																		// un
																		// cliente
																		// alla
																		// volta
		JButton bottAggiungi = new JButton("Aggiungi");
		bottAggiungi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel modelClienti = (DefaultTableModel) clienti
						.getModel();
				DefaultTableModel modelSpettacoli = (DefaultTableModel) spettacoli
						.getModel();
				if (clienti.getSelectedRow() >= 0
						&& spettacoli.getSelectedRow() >= 0) { // se sono
																// selezionati
																// un cliente e
																// uno
																// spettacolo
					int codiceCliente = (int) modelClienti.getValueAt(
							clienti.getSelectedRow(), 0);
					int codiceSpettacolo = (int) modelSpettacoli.getValueAt(
							spettacoli.getSelectedRow(), 0);
					Operatore oper = (Operatore) Persona.getUtenteLoggato();
					int codiceOp = oper.getCodiceOp();
					new FrameNumBiglietti(codiceSpettacolo, codiceOp,
							codiceCliente).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(FramePrenotazione.this,
							"Selezionare un utente e uno spettacolo");
				}
			}
		});
		bottAggiungi.setBounds(338, 57, 120, 45);
		pannello.add(bottAggiungi);

		JButton bottAggiorna = new JButton("Aggiorna");
		bottAggiorna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cercaSpett.setText("");
				cercaClienti.setText("");
				aggiornaTabellaClienti(GestioneCliente.istanzaCliente()
						.getLista());
				aggiornaTabellaSpett(GestioneSpettacolo.istanzaSpettacolo()
						.getLista());
			}
		});
		bottAggiorna.setBounds(338, 114, 120, 45);
		pannello.add(bottAggiorna);

		JButton bottIndietro = new JButton("Indietro");
		bottIndietro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new VistaSpettCorrente().setVisible(true);
				chiudi();
			}
		});
		bottIndietro.setBounds(338, 228, 120, 45);
		pannello.add(bottIndietro);

		JScrollPane scorri1 = new JScrollPane();
		scorri1.setBounds(494, 43, 578, 246);
		pannello.add(scorri1);

		spettacoli = new JTable();
		scorri1.setViewportView(spettacoli);
		spettacoli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		cercaClienti = new JTextField();
		cercaClienti.setBounds(10, 12, 318, 20);
		pannello.add(cercaClienti);
		cercaClienti.setColumns(10);

		JButton bottCerca = new JButton("Cerca");
		bottCerca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				aggiornaTabellaClienti(GestioneCliente.istanzaCliente().cerca(
						cercaClienti.getText()));
				aggiornaTabellaSpett(GestioneSpettacolo.istanzaSpettacolo()
						.getLista());
			}
		});
		bottCerca.setBounds(338, 171, 120, 45);
		pannello.add(bottCerca);

		cercaSpett = new JTextField();
		cercaSpett.setBounds(494, 11, 578, 23);
		pannello.add(cercaSpett);
		cercaSpett.setColumns(10);
	}

	public void aggiornaTabellaClienti(Collection<Cliente> lista) {
		DefaultTableModel modello = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {

				return false;
			}
		};

		String intest[] = new String[] { "Codice", "Nome", "Cognome" };
		modello.setColumnIdentifiers(intest); // aggiunge l'intestazione al
												// modello
		clienti.getTableHeader().setReorderingAllowed(false); // ordine colonne
																// fisso
		clienti.setModel(modello); // assegno alla tabella il modello
		for (Cliente cl : lista) {
			modello.addRow(new Object[] { cl.getCodice(), cl.getNome(),
					cl.getCognome() });
		}
	}

	/**
	 * Metodo che aggiorna la tabella degli spettacoli
	 * 
	 * @param lista
	 */
	public void aggiornaTabellaSpett(Collection<Spettacolo> lista) {
		DefaultTableModel modello = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String intest[] = new String[] { "Codice", "Nome", "Compagnia", "Data",
				"Prezzo", "Posti Disponibili" };
		modello.setColumnIdentifiers(intest);
		spettacoli.getTableHeader().setReorderingAllowed(false);
		spettacoli.setModel(modello);
		for (Spettacolo spett : lista) {
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			String dataFormattata = null;
			dataFormattata = formatoData.format((new Date(spett.getData()
					.getTime())));
			modello.addRow(new Object[] { spett.getCodice(), spett.getNomeSp(),
					spett.getCompagnia(), dataFormattata, spett.getPrezzo(),
					MAX_POSTI - spett.getPostiVenduti() });
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