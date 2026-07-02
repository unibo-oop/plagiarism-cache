package view.Interfaces;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.implementation.GestioneMerce;
import controller.implementation.GestionePorto;
import controller.implementation.GestionePrenotazione;
import controller.implementation.GestioneViaggio;
import exception.ExceptionNegativeQty;
import model.Implementations.ImplMerce;
import model.Implementations.ImplPorto;
import model.Implementations.ImplPrenotazione;
import model.Implementations.ImplViaggio;
import view.Main.Main;


import javax.swing.JButton;
import javax.swing.JComboBox;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 
 * @author Elisa Chiappini
 * View di inserimento nuove prenotazioni
 * 
 */


public class AggiungiPrenotazione extends JFrame {
	
	private static final long serialVersionUID = 1L;


	private static final String PESO = "";
	private static final String COLLI = "";

	private String destinazione;
	private String tipoMerce;
	private int pesoCarico;
	private int colliCarico;
	private int idViaggio;
	
	private JPanel contentPane;
	private JTable tabella;
	private JScrollPane scrollPane;
	private JLabel lblTipoCarico;
	private JComboBox<String> cmbMerce;
	private JLabel lblPesoCarico;
	private JLabel lblColli;
	private JLabel lblDestinazione;
	private JComboBox<String> cmbPorto;
	private JTextField jtfColli;
	private JTextField jtfPesoCarico;
	
	final JFrame f = this;


	


	/**
	 * Creazione del frame.
	 */
	public void editComboPorto() {
		GestionePorto gestionePorto = GestionePorto.creaIst();
		for(ImplPorto porto : gestionePorto.getList()) {
			cmbPorto.addItem(porto.getNome());
        }		
	}
	
	/**
	 * Creazione del frame.
	 */
	public void editComboMerce() {
		GestioneMerce gestioneMerce = GestioneMerce.creaIst();
		for(ImplMerce merce : gestioneMerce.getList()) {
			cmbMerce.addItem (merce.getNome());
		}
	}
	
	
	/**
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
        String intestazione[] = new String[] {"IDViaggio","Nome Nave", "Porto Provenienza", "Porto Destinazione","Tipo merce carico","Data Arrivo","Partito","Spazi disponibili","Capienza peso disponibile"};
        dtm.setColumnIdentifiers(intestazione);
        tabella.getTableHeader().setReorderingAllowed(false);
        tabella.setModel(dtm);  
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        for (ImplViaggio viaggio : lista) {
            dtm.addRow(new Object[] {viaggio.getId(),viaggio.getNome(), viaggio.getProvenienza(), viaggio.getDestinazione(),viaggio.getTipo(),format.format(viaggio.getData()),viaggio.getPartenza(),viaggio.getSpaziCarico(),viaggio.getCarico()});
            //System.out.println("Data " + viaggio.getData() + " Codice viaggio: " + viaggio.getId() + " Partito?: " + viaggio.getPartenza());
        }
	}
	
	/**
	 * Creazione del frame.
	 */
	public AggiungiPrenotazione() {
		setTitle("INSERIMENTO NUOVA PRENOTAZIONE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 115, 632, 222);
        contentPane.add(scrollPane);
        
        tabella = new JTable();
        scrollPane.setViewportView(tabella);
        tabella.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        lblTipoCarico = new JLabel("Tipo merce in carico: ");
        lblTipoCarico.setBounds(20, 51, 185, 14);
        contentPane.add(lblTipoCarico);
        
        cmbPorto = new JComboBox<String>();
        cmbPorto.setBounds(173, 82, 126, 22);
        contentPane.add(cmbPorto);
        editComboPorto();
        
        lblPesoCarico = new JLabel("Peso merce in carico: ");
        lblPesoCarico.setBounds(20, 392, 154, 14);
        contentPane.add(lblPesoCarico);
        
        jtfPesoCarico = new JTextField(PESO);
        jtfPesoCarico.setToolTipText("Inserire un valore intero (tonnellate)");
        jtfPesoCarico.setBounds(173, 389, 126, 20);
        contentPane.add(jtfPesoCarico);
        jtfPesoCarico.setColumns(10);
        
        lblColli = new JLabel("Numero colli in carico:");
        lblColli.setBounds(20, 364, 154, 14);
        contentPane.add(lblColli);
        
        jtfColli = new JTextField(COLLI);
        jtfColli.setToolTipText("Inserire un valore intero");
        jtfColli.setBounds(173, 361, 126, 20);
        contentPane.add(jtfColli);
        jtfColli.setColumns(10);

        
        lblDestinazione = new JLabel("Porto destinazione:");
        lblDestinazione.setBounds(20, 86, 165, 14);
        contentPane.add(lblDestinazione);
        
        
        cmbMerce = new JComboBox<String>();
        cmbMerce.setBounds(173, 47, 126, 22);
        contentPane.add(cmbMerce);
        editComboMerce();
	
		JButton btnCercaViaggiDisponibili = new JButton("CERCA VIAGGI DISPONIBILI");
		
		btnCercaViaggiDisponibili.addActionListener((a)-> {
			
					destinazione = cmbPorto.getSelectedItem().toString();
					tipoMerce = cmbMerce.getSelectedItem().toString(); 
					ArrayList<ImplViaggio> list = GestioneViaggio.creaIst().cercaDisponibili(destinazione,tipoMerce);
					if(list.isEmpty()) {
						JOptionPane.showMessageDialog(f, "Nessun Record.");
					}else {
						ricaricaTabella(list);
					}
		});
				
		btnCercaViaggiDisponibili.setBounds(345, 63, 251, 38);
		contentPane.add(btnCercaViaggiDisponibili);  
		
		JButton btnAggiungi = new JButton("CONFERMA PRENOTAZIONE");
		btnAggiungi.addActionListener((a) -> {
				try {
					pesoCarico = Integer.parseInt(jtfPesoCarico.getText());
				}catch (Exception exc) {
					JOptionPane.showMessageDialog(f, "Formato peso merce non valido! Inserire un numero intero.");
					return;
				}
				try {
					colliCarico = Integer.parseInt(jtfColli.getText());
				}catch (Exception exc) {
					JOptionPane.showMessageDialog(f, "Formato colli non valido! Inserire un numero intero.");
					return;
				}
				//System.out.println("peso da caricare: " + pesoCarico + "colli da caricare: " + colliCarico);
				 DefaultTableModel model = (DefaultTableModel) tabella.getModel();
                 if (tabella.getSelectedRow() >= 0) {
                      idViaggio = (int) model.getValueAt(tabella.getSelectedRow(), 0);
                      if((pesoCarico > 0) && (colliCarico > 0)) {
                    	  try {
                    		  GestionePrenotazione.creaIst().aggiungiPrenotazione(new ImplPrenotazione(idViaggio,tipoMerce,colliCarico,pesoCarico));
                    		  JOptionPane.showMessageDialog(f, "Inserimento avvenuto correttamente.");
                    		  new Main().setVisible(true);
              				  chiudi();
                    	  }catch (ExceptionNegativeQty e) {
                    		  JOptionPane.showMessageDialog(f, "I colli e il peso merce non posso superare la capienza disponibile.");
                    	  }
                      }else {
                    	  JOptionPane.showMessageDialog(f, "I campi peso e colli devono essere valori positivi, diversi da zero.");
                      }
                 }else {
                	 JOptionPane.showMessageDialog(f, "Selezionare un viaggio.");
                 }
		}         
		);
        btnAggiungi.setBounds(358, 373, 238, 38);
        contentPane.add(btnAggiungi);
		
		
		
        JButton btnIndietro = new JButton("INDIETRO");
        btnIndietro.addActionListener ((a) -> {
				new Main().setVisible(true);
				chiudi();
		});
        btnIndietro.setBounds(10, 11, 89, 23);
        contentPane.add(btnIndietro);
	}
        
        
	/**
     * Chiusura del frame
     */
    public void chiudi() {
    	setVisible(false);
    	dispose();
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
}
