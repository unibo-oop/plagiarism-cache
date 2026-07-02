package view.Interfaces;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.implementation.GestioneMerce;
import controller.implementation.GestioneNave;
import exception.ExceptionEqualName;
import model.Implementations.ImplMerce;
import model.Implementations.ImplNave;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import view.Main.GestioneInserimenti;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;





/**
 * 
 * @author Elisa Chiappini
 * Frame di inserimento navi
 */

public class AggiungiNave extends JFrame {

	
	
	private static final long serialVersionUID = 1L;
    
	private String nomeNave;
    private int portataNave;
    private String bandieraNave;
    private String tipoNave;
    private int spaziNave;
    
    private static final String NOME = "";
    private static final String PORTATA = "";
    private static final String BANDIERA = "";
    private static final String SPAZI ="";

    private JPanel contentPane;
    private JLabel title;
    private JLabel nome;
    private JLabel tipo;
    private JLabel bandiera;
    private JLabel portata;
    private JLabel spazi;
    private JTextField jtfNome;
    private JTextField jtfPortata;
    private JTextField jtfSpazi;
    private JTextField jtfBandiera;
    private JComboBox<String> cmbMerce;
    
    final JFrame f = this;
	
    
        
    /**
     * Metodo privato per popolare la JComboBox merce
     * @author Elisa Chiappini
     */
    private void editComboMerce() {
		GestioneMerce gestioneMerce = GestioneMerce.creaIst();
		for(ImplMerce merce : gestioneMerce.getList()) {
			cmbMerce.addItem(merce.getNome());
        }		
	}
     
    /**
	 * Creazione del frame. 
	 */
	public AggiungiNave() {
		this.setResizable(false);
		setTitle("AGGIUNGI NAVE ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		this.setContentPane(contentPane);
		
		title = new JLabel("AGGIUNGI NAVE");
		title.setFont(new Font("Tahoma", Font.PLAIN, 16));
		title.setBounds(160, 11, 130, 34);
		contentPane.add(title);
		
		nome = new JLabel("Nome Nave:");
		nome.setBounds(10, 68, 78, 14);
		contentPane.add(nome);
		
		portata = new JLabel("Portata: ");
		portata.setBounds(10, 112, 103, 14);
		contentPane.add(portata);
		
		spazi = new JLabel("Spazi: ");
		spazi.setBounds(10, 168, 78, 14);
		contentPane.add(spazi);
		
		bandiera = new JLabel("Bandiera: ");
		bandiera.setBounds(10, 221, 78, 14);
		contentPane.add(bandiera);
		
		
		tipo = new JLabel("Tipo di carico merce: ");
		tipo.setBounds(10, 272, 123, 14);
		contentPane.add(tipo);
		
		jtfNome = new JTextField(NOME);
		jtfNome.setToolTipText("Nome della nave");
		jtfNome.setBounds(274, 65, 166, 20);
		contentPane.add(jtfNome);
		
		jtfPortata = new JTextField(PORTATA);
		jtfPortata.setToolTipText("Portata in tonnellate");
		jtfPortata.setBounds(274, 109, 166, 20);
		contentPane.add(jtfPortata);
		
		jtfSpazi = new JTextField(SPAZI);
		jtfSpazi.setToolTipText("Spazi totali carico merce");
		jtfSpazi.setBounds(274, 165, 166, 20);
		contentPane.add(jtfSpazi);
		
		jtfBandiera = new JTextField(BANDIERA);
		jtfBandiera.setToolTipText("Nome bandiera");
		jtfBandiera.setBounds(274, 218, 166, 20);
		contentPane.add(jtfBandiera);
		
		cmbMerce = new JComboBox<String>();
		cmbMerce.setBounds(274, 268, 166, 22);
		contentPane.add(cmbMerce);
		editComboMerce();
	
		
		JButton btnAggiungi = new JButton("AGGIUNGI");
        btnAggiungi.addActionListener((a)-> {
            
                nomeNave = jtfNome.getText().toLowerCase();
                bandieraNave = jtfBandiera.getText().toLowerCase();
                
                try {
                	spaziNave = Integer.parseInt(jtfSpazi.getText());
                }catch (Exception exc) {
                	JOptionPane.showMessageDialog(f, "Formato spazi nave non valido! Inserire un numero intero.");
                	return;
                }
                try {
                	portataNave = Integer.parseInt(jtfPortata.getText());
                }catch (Exception exc) {
                	JOptionPane.showMessageDialog(f,"Formato portata nave non valido! Inserire un numero intero.");
                	return;
                }
                try {
                	tipoNave = (String) cmbMerce.getSelectedItem();
                }catch (Exception exc) {
                	JOptionPane.showMessageDialog(f, "Selezionare una voce per il tipo di nave.");
                }
                if (nomeNave.equals("")||portataNave == 0||spaziNave == 0 || tipoNave.equals("")) {
                	JOptionPane.showMessageDialog(f, "Non tutti i campi sono stati compilati.");
                }else
                	try {
                        
                            try {
                                GestioneNave.creaIst().aggiungiNave(new ImplNave(nomeNave, portataNave, spaziNave, bandieraNave, tipoNave));
                            } catch (ExceptionEqualName ex) {
                                JOptionPane.showMessageDialog(f, "Nave gia'  presente nel sistema!");
                                return;
                            }
                            JOptionPane.showMessageDialog(f, "Inserimento avvenuto correttamente.");
                            new GestioneInserimenti().setVisible(true);
                            chiudi();
                        
                    }catch (HeadlessException ex2) {
                        ex2.printStackTrace();	
                    }

        });
        btnAggiungi.setBounds(352, 337, 116, 23);
        contentPane.add(btnAggiungi);

		
		JButton btnIndietro = new JButton("INDIETRO");
		btnIndietro.addActionListener((a)-> {

				new GestioneInserimenti().setVisible(true);
				chiudi();
			
		});
		btnIndietro.setBounds(10, 19, 89, 23);
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
