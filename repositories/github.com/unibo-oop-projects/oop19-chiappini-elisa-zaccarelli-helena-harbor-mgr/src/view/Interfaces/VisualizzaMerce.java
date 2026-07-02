package view.Interfaces;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.implementation.GestioneMerce;
import model.Implementations.ImplMerce;
import view.Main.GestioneInserimenti;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

/**
 * 
 * @author Elisa Chiappini
 * Frame di visualizzazione e cancellazione istanze di Merce
 */


public class VisualizzaMerce extends JFrame {
private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField cerca;
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
        	GestioneMerce.creaIst().rimuoviMerce(codice);
        	Collection<ImplMerce> lista = GestioneMerce.creaIst().getList();
        	SwingUtilities.invokeLater(() -> ricaricaTabella(lista));                       
        }).start();
	}
	
	
	/**
	 * Metodo che aggiorna il contenuto della tabella
	 * @author Elisa Chiappini
	 */
	public void ricaricaTabella(Collection<ImplMerce> lista) {
        DefaultTableModel dtm = new DefaultTableModel() {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String intestazione[] = new String[] { "Codice", "Merce"};
        dtm.setColumnIdentifiers(intestazione);
        tabella.getTableHeader().setReorderingAllowed(false);
        tabella.setModel(dtm);
        for (ImplMerce merce : lista) {
            dtm.addRow(new Object[] {merce.getCod(), merce.getNome()});
        }
	}
	
	
    public VisualizzaMerce() {
    	this.setResizable(false);
    	this.setTitle("VISUALIZZAZIONE TIPO MERCE IN ARCHIVIO.");
    	this.addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowOpened(WindowEvent e) {
    			ricaricaTabella(GestioneMerce.creaIst().getList());
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
            
            cerca = new JTextField();
            cerca.setBounds(170,11,362,20);
            contentPane.add(cerca);
            cerca.setColumns(10);
            
            JLabel lblCerca = new JLabel("Ricerca per nome: ");
            lblCerca.setBounds(20, 11, 140, 20);
            contentPane.add(lblCerca);
            
            JButton btnCerca = new JButton("CERCA");
            btnCerca.addActionListener ((a) -> {
            		ricaricaTabella(GestioneMerce.creaIst().cerca(cerca.getText()));
            });
            btnCerca.setBounds(535, 10, 89, 23);
            contentPane.add(btnCerca);
            
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
    				new GestioneInserimenti().setVisible(true);
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