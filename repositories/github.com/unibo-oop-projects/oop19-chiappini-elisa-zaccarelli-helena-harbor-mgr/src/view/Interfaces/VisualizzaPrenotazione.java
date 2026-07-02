package view.Interfaces;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import controller.implementation.GestionePrenotazione;
import model.Implementations.ImplPrenotazione;
import view.Main.Main;


/**
 * 
 * @author Elisa Chiappini
 * Frame di visualizzazione e cancellazione istanze di Prenotazione
 */

public class VisualizzaPrenotazione extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable tabella;
	
	/**
	 * Metodo privato che gestisce la cancellazione di un record all'interno della tabella
	 * che consente di visualizzare la finestra di conferma
	 * @author Elisa Chiappini
	 */
	private void cancellaRecordSelezionato() {
		DefaultTableModel model = (DefaultTableModel) tabella.getModel();
        int codice = (int) model.getValueAt(tabella.getSelectedRow(), 0);
        new Thread(() -> {
        	GestionePrenotazione.creaIst().rimuoviPrenotazione(codice);
        	Collection<ImplPrenotazione> lista = GestionePrenotazione.creaIst().getList();
        	SwingUtilities.invokeLater(() -> ricaricaTabella(lista));                       
        }).start();
	}
	
	
	/**
	 * Metodo che aggiorna il contenuto della tabella
	 * @author Elisa Chiappini
	 */
	public void ricaricaTabella(Collection<ImplPrenotazione> lista) {
        DefaultTableModel dtm = new DefaultTableModel() {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String intestazione[] = new String[] { "Codice Prenotazione", "Colli carico", "Peso carico", "Tipo Merce carico","IdViaggio"};
        dtm.setColumnIdentifiers(intestazione);
        tabella.getTableHeader().setReorderingAllowed(false);
        tabella.setModel(dtm);
        for (ImplPrenotazione prenotazione : lista) {
            dtm.addRow(new Object[] {prenotazione.getCodice(),prenotazione.getColli(),prenotazione.getPeso(),prenotazione.getTipo(),prenotazione.getId()});
        }
	}
	
	public VisualizzaPrenotazione() {
		this.setResizable(false);
		this.setTitle("VISUALIZZA PRENOTAZIONI ATTIVE");
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				ricaricaTabella(GestionePrenotazione.creaIst().getList());
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 814, 431);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(170, 42, 632, 270);
        contentPane.add(scrollPane);

        tabella = new JTable();
        scrollPane.setViewportView(tabella);
        tabella.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JButton btnCancella = new JButton("CANCELLA");
        btnCancella.setToolTipText("Cancella l'elemento selezionato dall'archivio");
        final JFrame f = this;
        btnCancella.addActionListener(a -> {
        	if (tabella.getSelectedRow() >= 0) {
            	int opt = JOptionPane.showConfirmDialog(f, "Eliminare il record selezionato?", "Cancella", JOptionPane.YES_NO_OPTION);
        		if (opt == JOptionPane.YES_OPTION) {
        			cancellaRecordSelezionato();
        		}
        	}
        });   
        btnCancella.setBounds(38, 42, 115, 38);
        contentPane.add(btnCancella);
        
        JButton btnIndietro = new JButton("INDIETRO");
        btnIndietro.addActionListener ((a) -> {
				new Main().setVisible(true);
				chiudi();
		});
        btnIndietro.setBounds(38, 339, 115, 38);
        contentPane.add(btnIndietro);
        
       }    
      
    /**
     * Chiusura del frame
     */
    public void chiudi() {
        setVisible(false);
        dispose();
    }	
}


