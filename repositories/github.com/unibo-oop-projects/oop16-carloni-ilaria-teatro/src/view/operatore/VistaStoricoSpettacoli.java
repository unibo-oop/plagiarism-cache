package view.operatore;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Spettacolo;
import control.GestioneSpettacolo;

/**
 * Classe che permette di visualizzare gli spettacoli già andati in scena
 * 
 * @author Ilaria Carloni
 * 
 */
public class VistaStoricoSpettacoli extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel pannello;
	private JTextField cerca;
	private JTable tabella;

	/**
	 * Crea il frame.
	 */
	public VistaStoricoSpettacoli() {
		this.setResizable(false);
		this.setTitle("SPETTACOLI CONCLUSI");
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				aggiornaTabSpett(GestioneSpettacolo.istanzaSpettacolo()
						.getStoricoSpett());
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

		tabella = new JTable();
		scrollPane.setViewportView(tabella);
		tabella.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cerca = new JTextField();
		cerca.setBounds(10, 11, 632, 20);
		pannello.add(cerca);
		cerca.setColumns(10);

		JButton btnCerca = new JButton("Cerca");
		btnCerca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				aggiornaTabSpett(GestioneSpettacolo.istanzaSpettacolo().cerca(
						cerca.getText()));
			}
		});
		btnCerca.setBounds(662, 10, 120, 45);
		pannello.add(btnCerca);

		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new MainFrameOperatore().setVisible(true);
				chiudi();
			}
		});
		btnIndietro.setBounds(662, 134, 120, 45);
		pannello.add(btnIndietro);
	}

	/**
	 * Aggiorna la tabella degli spettacoli
	 * 
	 * @param lista
	 */
	public void aggiornaTabSpett(Collection<Spettacolo> lista) {
		DefaultTableModel modello = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			// L'utente non può selezionare le celle
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String intest[] = new String[] { "Codice", "Nome", "Compagnia", "Data",
				"Prezzo", "Posti Venduti" };
		modello.setColumnIdentifiers(intest); // aggiunge l'intestazione al
												// modello
		tabella.getTableHeader().setReorderingAllowed(false); // l'ordine delle
																// colonne è
																// fisso
		tabella.setModel(modello);
		for (Spettacolo sp : lista) {
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			String dataFormattata;
			dataFormattata = formatoData
					.format(new Date(sp.getData().getTime()));
			if (Calendar.getInstance().getTimeInMillis() > sp.getData()
					.getTime()) {

				modello.addRow(new Object[] { sp.getCodice(), sp.getNomeSp(),
						sp.getCompagnia(), dataFormattata, sp.getPrezzo(),
						sp.getPostiVenduti() });
			}
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