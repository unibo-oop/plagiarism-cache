package view.Interfaces;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;

import controller.implementation.GestioneNave;
import controller.implementation.GestionePorto;
import controller.implementation.GestioneViaggio;
import exception.ExceptionEqualRecord;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


import view.Main.GestioneInserimenti;
import model.Implementations.ImplNave;
import model.Implementations.ImplPorto;
import model.Implementations.ImplViaggio;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;


 /** @author Elisa Chiappini
 * Frame di inserimento viaggio
 */



public class AggiungiViaggio extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private String nomeNave;
	private int portata;
	private int spazi;
	private String bandiera;
	private String tipo;
	private String portoProvenienza;
	private String portoDestinazione;
	private int pesoMerceSbarco;
	private int pesoMerceTransito;
	private int spaziMerceTransito;
	private Date dataArrivo;
	private int durataViaggio;
	private boolean opzioneCarico;
	private final static String INIT = "-";

	private JPanel contentPane;
	private JTextField jtfMerceSbarco;
	private JTextField jtfPesoTransito;
	private JTextField jtfSpaziTransito;
	private JTextField jtfDurataViaggio;
	private JFormattedTextField jtfDataArrivo;
	private JComboBox<String> cmbNave;
	private JComboBox<String> cmbPortoIn;
	private JComboBox<String> cmbPortoOut;
	private JCheckBox chckbxPrevistoCarico;
	
	final JFrame f = this;
	

	/**
	 * Metodo privato per la popolazione delle JComboBox dei porti provenienza e destinazione
	 * @author Elisa Chiappini
	 */

	private void editComboPorto() {
		GestionePorto gestionePorto = GestionePorto.creaIst();
		cmbPortoIn.addItem(INIT);
		cmbPortoOut.addItem(INIT);
		for(ImplPorto porto : gestionePorto.getList()) {
			cmbPortoIn.addItem(porto.getNome());
			cmbPortoOut.addItem(porto.getNome());
		}
	}
	
	/**
	 * Metodo privato per la popolazione della JComboBox della nave
	 * @author Elisa Chiappini
	 */
	private void editComboNave() {
		GestioneNave gestioneNave = GestioneNave.creaIst();
		for(ImplNave nave : gestioneNave.getList()) {
			cmbNave.addItem(nave.getNome());
		}
	}
	
	/**
	 * Creazione del frame.
	 */
	public AggiungiViaggio() {
		setTitle("INSERIMENTO VIAGGIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel title = new JLabel("AGGIUNGI VIAGGIO");
		title.setFont(new Font("Tahoma", Font.PLAIN, 16));
		title.setBounds(174, 11, 173, 35);
		contentPane.add(title);
		
		JLabel lblNomeNave = new JLabel("Nome Nave:");
		lblNomeNave.setBounds(10, 70, 76, 14);
		contentPane.add(lblNomeNave);
		
		JLabel lblPortoProvenienza = new JLabel("Porto Provenienza:");
		lblPortoProvenienza.setBounds(10, 105, 167, 14);
		contentPane.add(lblPortoProvenienza);
		
		JLabel lblPortoDestinazione = new JLabel("Porto Destinazione:");
		lblPortoDestinazione.setBounds(10, 140, 167, 14);
		contentPane.add(lblPortoDestinazione);
		
		JLabel lblSbarco = new JLabel("Peso merce in sbarco:");
		lblSbarco.setBounds(10, 186, 167, 14);
		contentPane.add(lblSbarco);
		
		JLabel lblDataArrivo = new JLabel("Data Arrivo:");
		lblDataArrivo.setBounds(10, 344, 76, 14);
		contentPane.add(lblDataArrivo);
		
		JLabel lblDurataViaggio = new JLabel("Durata viaggio:");
		lblDurataViaggio.setBounds(10, 369, 86, 14);
		contentPane.add(lblDurataViaggio);
		
		JLabel lblNumeroSpaziMerce = new JLabel("Numero spazi merce in transito:");
		lblNumeroSpaziMerce.setBounds(10, 272, 247, 14);
		contentPane.add(lblNumeroSpaziMerce);
		
		jtfMerceSbarco = new JTextField();
		jtfMerceSbarco.setToolTipText("Peso espresso in tonnellate (numero intero)");
		jtfMerceSbarco.setBounds(329, 183, 94, 20);
		contentPane.add(jtfMerceSbarco);
		jtfMerceSbarco.setColumns(10);
		
		jtfDurataViaggio = new JTextField();
		jtfDurataViaggio.setToolTipText("Inserire la durata del viaggio espressa in giorni");
		jtfDurataViaggio.setBounds(142, 366, 86, 20);
		contentPane.add(jtfDurataViaggio);
		jtfDurataViaggio.setColumns(10);
		
		jtfSpaziTransito = new JTextField();
		jtfSpaziTransito.setToolTipText("Numero spazi che restano occupati");
		jtfSpaziTransito.setBounds(329, 269, 94, 20);
		contentPane.add(jtfSpaziTransito);
		jtfSpaziTransito.setColumns(10);
		
		chckbxPrevistoCarico = new JCheckBox("Previsto carico");
		chckbxPrevistoCarico.setToolTipText("Spuntare se si tratta di uno scarico/carico");
		chckbxPrevistoCarico.setBounds(396, 365, 149, 23);
		contentPane.add(chckbxPrevistoCarico);
		
		cmbNave = new JComboBox<String>();
		cmbNave.setBounds(319, 66, 104, 22);
		contentPane.add(cmbNave);
		editComboNave();
		
		cmbPortoIn = new JComboBox<String>();
		cmbPortoIn.setBounds(319, 101, 104, 22);
		contentPane.add(cmbPortoIn);
		
		cmbPortoOut = new JComboBox<String>();
		cmbPortoOut.setBounds(319, 136, 104, 22);
		contentPane.add(cmbPortoOut);
		editComboPorto();
		
		JLabel lblPesoTransito = new JLabel("Peso merce in transito:");
		lblPesoTransito.setBounds(10, 228, 218, 14);
		contentPane.add(lblPesoTransito);
		
		jtfPesoTransito = new JTextField();
		jtfPesoTransito.setToolTipText("Valore espresso in tonnellate (valore intero)");
		jtfPesoTransito.setBounds(329, 225, 94, 20);
		contentPane.add(jtfPesoTransito);
		jtfPesoTransito.setColumns(10);
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		DateFormatter df = new DateFormatter(format);
		jtfDataArrivo = new JFormattedTextField(df);
		jtfDataArrivo.setToolTipText("inserire una data in formato(dd/MM/yyyy)");
		jtfDataArrivo.setBounds(142, 341, 86, 20);
		contentPane.add(jtfDataArrivo);
		
				
		JButton btnAggiungi = new JButton("AGGIUNGI");
		btnAggiungi.addActionListener ((a) -> {
            	try {
                	nomeNave = (String) cmbNave.getSelectedItem();
            		GestioneNave gestioneNave = GestioneNave.creaIst();
            		
            		//Recupero da nave i parametri che mi serviranno per creare il record ImplViaggio
            		for(ImplNave nave : gestioneNave.getList()) {
            			if(nave.getNome().equals(nomeNave)) {
            				portata = nave.getPortata();
            				spazi = nave.getSpazi();
            				bandiera = nave.getBandiera();
            				tipo = nave.getTipo();	
            			}
            		}
            	}catch (Exception exc) {
            		JOptionPane.showMessageDialog(AggiungiViaggio.this, "Selezionare una nave.");
            		return;
            	}
            	portoProvenienza = cmbPortoIn.getSelectedItem().toString();
            	portoDestinazione = cmbPortoOut.getSelectedItem().toString();
            	if (portoProvenienza.matches(INIT)||portoDestinazione.matches(INIT)) {
            		JOptionPane.showMessageDialog(AggiungiViaggio.this, "I Campi porto sono obbligatori.");
            	}
            	
            	try {
            		pesoMerceTransito = Integer.parseInt(jtfPesoTransito.getText());
               }catch (Exception exc) {
               	JOptionPane.showMessageDialog(f, "Formato peso merce in transito non valido! Inserire un numero intero.");
               	return;
               }
            	
                try {
                	 pesoMerceSbarco = Integer.parseInt(jtfMerceSbarco.getText());
                }catch (Exception exc) {
                	JOptionPane.showMessageDialog(f, "Formato peso merce sbarco non valido! Inserire un numero intero.");
                	return;
                }
                try {
                	dataArrivo = (Date)jtfDataArrivo.getValue();
                	System.out.println(dataArrivo);
                }catch(Exception e1) {
                	JOptionPane.showMessageDialog(f, "Inserire una data di arrivo valida.");
                	return;
                }
                
                try {
                	durataViaggio = Integer.parseInt(jtfDurataViaggio.getText());
                }catch (Exception exc) {
                	JOptionPane.showMessageDialog(f, "Formato durata viaggio non valido! Inserire un numero intero.");
                	return;
                }
                
                try {
                	spaziMerceTransito = Integer.parseInt(jtfSpaziTransito.getText());
                	if(spaziMerceTransito > spazi) {
                		JOptionPane.showMessageDialog(f, "Il valore non può essere maggiore degli spazi disponibili nella nave!");
                	}
                }catch (Exception exc) {
                	JOptionPane.showMessageDialog(f, "Formato spazi in transito non valido. Richiesto un numero intero");
                }
                
                opzioneCarico = chckbxPrevistoCarico.isEnabled();
               
                //Controlli dei dati prima di effettuare l'inserimento
                if (portoDestinazione == portoProvenienza) {
            		JOptionPane.showMessageDialog(f, "Il porto destinazione deve essere diverso da quello di provenienza.");
            		return;
                }else if (dataArrivo.before(new Date())) {
                	JOptionPane.showMessageDialog(f, "La data di arrivo deve essere successiva a quella odierna.");
                	return;
                }else if(pesoMerceSbarco == 0||durataViaggio == 0) {
                	JOptionPane.showMessageDialog(f, "Non tutti i campi sono stati compilati.");
                	return;
                }else 
                	try {
                			try {
                				GestioneViaggio.creaIst().aggiungiViaggio(new ImplViaggio(nomeNave,portata,spazi,bandiera,tipo,portoProvenienza,portoDestinazione,pesoMerceSbarco,pesoMerceTransito, spaziMerceTransito,dataArrivo,durataViaggio,opzioneCarico));	
                			}catch (ExceptionEqualRecord e1) {
                				JOptionPane.showMessageDialog(f, "Viaggio già presente in archivio!");
                				return;
							}
                			JOptionPane.showMessageDialog(f, "Inserimento avvenuto correttamente.");
                            new GestioneInserimenti().setVisible(true);
                            chiudi();
                            
                	}catch(HeadlessException ex2) {
            			ex2.printStackTrace();
                	}
        });
		btnAggiungi.setBounds(36, 436, 208, 52);
		contentPane.add(btnAggiungi);
		
		JButton btnIndietro = new JButton("INDIETRO");
		btnIndietro.addActionListener ((a) -> {
				new GestioneInserimenti().setVisible(true);
				chiudi();
		});
		btnIndietro.setBounds(337, 436, 208, 52);
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
