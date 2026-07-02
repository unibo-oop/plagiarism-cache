package view.Interfaces;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exception.ExceptionEqualName;
import view.Main.GestioneInserimenti;
import model.Implementations.ImplPorto;
import controller.implementation.GestionePorto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;


/**
 * 
 * @author Elisa Chiappini
 * Frame di inserimento porti
 */

public class AggiungiPorto extends JFrame {
	
	
private static final long serialVersionUID = 1L;
    
	private String nomePorto;
    private String nazione;
    
    private static final String NOME="";
    private static final String NAZIONE="";

	private JPanel contentPane;
	private JTextField jtfNomePorto;
	private JTextField jtfNazione;

	final JFrame f = this;

	/**
	 * Creazione del frame.
	 */
	public AggiungiPorto() {
		this.setResizable(false);
		setTitle("INSERIMENTO PORTO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 307, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel title = new JLabel("AGGIUNGI PORTO");
		title.setFont(new Font("Tahoma", Font.PLAIN, 16));
		title.setBounds(64, 11, 158, 34);
		contentPane.add(title);
		
		JLabel lblnomePorto = new JLabel("Nome porto:");
		lblnomePorto.setBounds(10, 66, 106, 14);
		contentPane.add(lblnomePorto);
		
		jtfNomePorto = new JTextField(NOME);
		jtfNomePorto.setToolTipText("Nome del porto");
		jtfNomePorto.setText("");
		jtfNomePorto.setBounds(126, 63, 86, 20);
		contentPane.add(jtfNomePorto);
		jtfNomePorto.setColumns(10);
		
		JLabel nazionePorto = new JLabel("Nazione:");
		nazionePorto.setBounds(10, 123, 86, 14);
		contentPane.add(nazionePorto);
		
		jtfNazione = new JTextField(NAZIONE);
		jtfNazione.setToolTipText("Nazione del porto");
		jtfNazione.setBounds(126, 120, 86, 20);
		contentPane.add(jtfNazione);
		jtfNazione.setColumns(10);
		
		JButton btnIndietro = new JButton("INDIETRO");
		btnIndietro.addActionListener ((a) -> {
				new GestioneInserimenti().setVisible(true);
				chiudi();
		});
		btnIndietro.setBounds(0, 189, 89, 23);
		contentPane.add(btnIndietro);
		
		JButton btnAggiungi = new JButton("AGGIUNGI");
        btnAggiungi.addActionListener ((a) -> {
                nomePorto = jtfNomePorto.getText().toLowerCase();
                nazione = jtfNazione.getText().toLowerCase();
                
                if (nomePorto.equals("")||nazione.equals("")) {
                	JOptionPane.showMessageDialog(f, "Inserire tutti i campi richiesti.");
                }else {
                	try {
                        try {
                            GestionePorto.creaIst().aggiungiPorto(new ImplPorto(nomePorto, nazione));
                            } catch (ExceptionEqualName ex) {
                                JOptionPane.showMessageDialog(f, "Porto già presente nel sistema!");
                                return;
                            }
                            JOptionPane.showMessageDialog(f, "Inserimento avvenuto correttamente.");
                            new GestioneInserimenti().setVisible(true);
                            chiudi();
                    }catch (HeadlessException ex2) {
                        ex2.printStackTrace();	
                    }
                }
          });
          btnAggiungi.setBounds(141, 189, 116, 23);
          contentPane.add(btnAggiungi);
		}
                
        /**
         * Chiusura del frame
         */
        public void chiudi() {
        	setVisible(false);
        	dispose();
    	}
	}
            

