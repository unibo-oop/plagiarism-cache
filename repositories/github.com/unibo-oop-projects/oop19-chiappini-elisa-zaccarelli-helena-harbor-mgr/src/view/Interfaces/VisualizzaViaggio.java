package view.Interfaces;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;


import controller.implementation.GestioneViaggio;
import exception.ExceptionNotPossibleOp;
import model.Implementations.ImplViaggio;
import view.Main.GestioneInserimenti;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

/**
 * 
 * @author Elisa Chiappini
 * Frame di visualizzazione e cancellazione istanze di Viaggio
 * + settaggio di un viaggio come partito
 */


public class VisualizzaViaggio extends JFrame {
private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable tabella;
	private JFormattedTextField ftfRicercaViaggio;
	
	private Date dataRicerca;
	private Date data = new Date(0);
	
	final JFrame f = this;
	
	
	
	/**
	 * 
	 * @author Elisa Chiappini
	 * Metodo privato che gestisce la cancellazione di un record all'interno della tabella
	 * @throws ParseException 
	 */
	private void cancellaRecordSelezionato() throws ParseException {
		DefaultTableModel model = (DefaultTableModel) tabella.getModel();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        int codice = (int) model.getValueAt(tabella.getSelectedRow(), 0);
        //System.out.println("Codice record che si tenta di cancellare: " + codice);
        data = (Date) format.parse((String)(model.getValueAt(tabella.getSelectedRow(),5)));
        new Thread(() -> {
        	try {
				GestioneViaggio.creaIst().rimuoviViaggio(codice);
			} catch (ExceptionNotPossibleOp e1) {
				JOptionPane.showMessageDialog(f, "Viaggio gia' partito! Non è possibile cancellare il record.");
			}
        	Collection<ImplViaggio> lista = GestioneViaggio.creaIst().getList();
        	SwingUtilities.invokeLater(() -> ricaricaTabella(lista));                       
        }).start();
	}
	
	/**
	 * 
	 * @author Elisa Chiappini
	 * Metodo privato che gestisce il settaggio del campo partenza a TRUE
	 * @throws ParseException 
	 */
	private void modificaCampoPartenza() throws ParseException {
		DefaultTableModel model = (DefaultTableModel) tabella.getModel();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        int codice = (int) model.getValueAt(tabella.getSelectedRow(), 0);
        //System.out.println("codice: " + codice);
        data = (Date) format.parse((String)(model.getValueAt(tabella.getSelectedRow(),5)));
        //System.out.println("Data arrivo viaggio: " + data);
        boolean partito = (boolean) model.getValueAt(tabella.getSelectedRow(), 6);
        //System.out.println("Partito? " + partito);
        new Thread(() -> {
        	try {
        		if (data.before(new Date())) {
        			if (partito) {
        				JOptionPane.showMessageDialog(f, "Viaggio gia' partito, impossibile modificare il record nuovamente.");
        				return;
        			}else {
        				GestioneViaggio.creaIst().partenzaViaggio(codice);
        				JOptionPane.showMessageDialog(f, "Partenza aggiunta con successo!");
        			}
        		}else {
        			JOptionPane.showMessageDialog(f, "Impossibile impostare il viaggio come partito. Data arrivo successiva a quella odierna.");
        			return;
        		}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(f, "Viaggio gia' partito!");
			}
        	Collection<ImplViaggio> lista = GestioneViaggio.creaIst().getList();
        	SwingUtilities.invokeLater(() -> ricaricaTabella(lista));                       
        }).start();
	}
	
	
	
	/**
	 * 
	 * @author Elisa Chiappini
	 * Metodo che aggiorna il contenuto della tabella
	 */
	public void ricaricaTabella(Collection<ImplViaggio> lista) {
		DefaultTableModel dtm = new DefaultTableModel() {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String intestazione[] = new String[] {"IDViaggio","Nome Nave", "Porto Provenienza", "Porto Destinazione","Tipo merce carico","Data Arrivo","Partito"};
        dtm.setColumnIdentifiers(intestazione);
        tabella.getTableHeader().setReorderingAllowed(false);
        tabella.setModel(dtm);  
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        for (ImplViaggio viaggio : lista) {
            dtm.addRow(new Object[] {viaggio.getId(),viaggio.getNome(), viaggio.getProvenienza(), viaggio.getDestinazione(),viaggio.getTipo(),format.format(viaggio.getData()),viaggio.getPartenza()});
            System.out.println("Data " + viaggio.getData() + " Codice viaggio: " + viaggio.getId() + " Partito?: " + viaggio.getPartenza());
        }
	}
        public VisualizzaViaggio() {
    		this.setResizable(false);
    		this.setTitle("VISUALIZZAZIONE VIAGGI IN ARCHIVIO");
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
            
            JLabel lblCerca = new JLabel("Ricerca per data: ");
            lblCerca.setBounds(20, 11, 140, 20);
            contentPane.add(lblCerca);
            
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    		DateFormatter df = new DateFormatter(format);
            ftfRicercaViaggio = new JFormattedTextField(df);
            ftfRicercaViaggio.setBounds(169, 11, 151, 20);
            contentPane.add(ftfRicercaViaggio);
            
            
            JButton btnCerca = new JButton("CERCA");
            btnCerca.setToolTipText("Cerca i viaggi disponibili dalla data inserita a quella odierna.");
            btnCerca.addActionListener((a) -> {
            	if (ftfRicercaViaggio.getValue() != null) {
            		try {
            			try {
            				dataRicerca = (Date)ftfRicercaViaggio.getValue();
            				//System.out.println("Data ricerca " + dataRicerca);
            			}catch(Exception e1) {
            				JOptionPane.showMessageDialog(f, "Inserire una data di ricerca valida.");
            				return;
            			} 
            			ricaricaTabella(GestioneViaggio.creaIst().cercaPerData(dataRicerca));
            			if((GestioneViaggio.creaIst().cercaPerData(dataRicerca)).isEmpty()) {
            				JOptionPane.showMessageDialog(f, "Nessun record.");
            			}
            		}catch(Exception ex) {
            			ex.printStackTrace();	
            		}
            	}else {
            		JOptionPane.showMessageDialog(f,"Niente da visualizzare.");
            	}
            });
            btnCerca.setBounds(330, 8, 89, 23);
            contentPane.add(btnCerca);
            
            JButton btnCancella = new JButton("CANCELLA");
            btnCancella.setToolTipText("Cancella l'elemento selezionato dall'archivio");
            btnCancella.addActionListener ((a) -> {
            	if (tabella.getSelectedRow() >= 0) {
                    int opt = JOptionPane.showConfirmDialog(f, "Eliminare il record selezionato? Verranno eliminate anche le prenotazioni correlate.", "CANCELLA", JOptionPane.YES_NO_OPTION);
                    if(opt == JOptionPane.YES_OPTION) {
                    	try {
							cancellaRecordSelezionato();
						} catch (ParseException e) {
							JOptionPane.showMessageDialog(f,"Formato data non corretto.");
						}
                    }
                }         
            });
            btnCancella.setBounds(38, 42, 115, 38);
            contentPane.add(btnCancella);
            
            JButton btnPartito = new JButton("PARTENZA");
            btnPartito.setToolTipText("Imposta un viaggio selezionato come partito");
            btnPartito.addActionListener((a) -> {
            	if (tabella.getSelectedRow() >= 0) {
            		int opt = JOptionPane.showConfirmDialog(f, "Impostare il viaggio selezionato come partito?", "CONFERMA MODIFICA", JOptionPane.YES_NO_OPTION);
            		if(opt == JOptionPane.YES_OPTION) {
                    	try {
							modificaCampoPartenza();
						} catch (ParseException e) {
							JOptionPane.showMessageDialog(f,"Formato data non corretto.");
						}
                    }
                }         
               
            });
            btnPartito.setBounds(38, 91, 115, 38);
            contentPane.add(btnPartito);
            
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