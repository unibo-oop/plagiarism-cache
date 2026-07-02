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

import controller.implementation.GestionePorto;
import model.Implementations.ImplPorto;
import view.Main.GestioneInserimenti;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;



/**
 * 
 * @author Elisa Chiappini
 * Frame di visualizzazione e cancellazione istanze di Porto
 */

public class VisualizzaPorto extends JFrame {
private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField cerca;
	private JTable tabella;
	
	
	
	/**
	 * 
	 * @author Elisa Chiappini
	 * Metodo privato che gestisce la cancellazione di un record all'interno della tabella
	 */
	private void cancellaRecordSelezionato() {
		DefaultTableModel model = (DefaultTableModel) tabella.getModel();
        int codice = (int) model.getValueAt(tabella.getSelectedRow(), 0);
        new Thread(() -> {
        	GestionePorto.creaIst().rimuoviPorto(codice);
        	Collection<ImplPorto> lista = GestionePorto.creaIst().getList();
        	SwingUtilities.invokeLater(() -> ricaricaTabella(lista));                       
        }).start();
	}
	
	/**
	 * 
	 * @author Elisa Chiappini
	 * Metodo che aggiorna il contenuto della tabella
	 */
	public void ricaricaTabella(Collection<ImplPorto> lista) {
		DefaultTableModel dtm = new DefaultTableModel() {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String intestazione[] = new String[] { "Codice", "Nome Porto", "Nazione"};
        dtm.setColumnIdentifiers(intestazione);
        tabella.getTableHeader().setReorderingAllowed(false);
        tabella.setModel(dtm);
        for (ImplPorto porto : lista) {
            dtm.addRow(new Object[] {porto.getCod(), porto.getNome(), porto.getNazione()});
        }
	}
	/**
	 * Creazione del frame
	 */
    public VisualizzaPorto() {
    	this.setResizable(false);
    	this.setTitle("VISUALIZZAZIONE PORTI IN ARCHIVIO");
    	this.addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowOpened(WindowEvent e) {
    			ricaricaTabella(GestionePorto.creaIst().getList());
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
            
    	contentPane.add(lblCerca);
            
    	JButton btnIndietro = new JButton("INDIETRO");
    	btnIndietro.addActionListener((a) -> {
    		new GestioneInserimenti().setVisible(true);
    		chiudi();
    	});
    	btnIndietro.setBounds(38, 339, 115, 38);
    	contentPane.add(btnIndietro);
            
    	JButton btnCerca = new JButton("CERCA");
    	btnCerca.addActionListener ((a) -> {
    		ricaricaTabella(GestionePorto.creaIst().cerca(cerca.getText()));
    	});
    	btnCerca.setBounds(542, 10, 89, 23);
    	contentPane.add(btnCerca);
                   
    }
    /**
	* Chiusura del frame
	*/
	public void chiudi() {
	    setVisible(false);
	    dispose();
	}
        
}
