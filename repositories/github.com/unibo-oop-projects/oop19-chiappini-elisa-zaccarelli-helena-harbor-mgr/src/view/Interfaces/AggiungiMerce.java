package view.Interfaces;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.implementation.GestioneMerce;
import exception.ExceptionEqualName;
import model.Implementations.ImplMerce;

import javax.swing.JTextField;
import javax.swing.JButton;


import view.Main.GestioneInserimenti;

/**
 * 
 * @author Elisa Chiappini
 * Frame di inserimento tipologie di merce
 */

public class AggiungiMerce extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private String nomeMerce;
	
	private static final String NOME = "";

	private JPanel contentPane;
	private JTextField jtfNome;
	
	final JFrame f = this;

	

	/**
	 * Creazione del frame.
	 */
	public AggiungiMerce() {
		this.setResizable(false);
		setTitle("INSERIMENTO MERCE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 306, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel title = new JLabel("AGGIUNGI MERCE");
		title.setFont(new Font("Tahoma", Font.PLAIN, 16));
		title.setBounds(70, 11, 158, 34);
		contentPane.add(title);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 80, 46, 14);
		contentPane.add(lblNome);
		
		jtfNome = new JTextField(NOME);
		jtfNome.setToolTipText("Inserire un nome che descriva il tipo di merce");
		jtfNome.setBounds(86, 77, 142, 20);
		contentPane.add(jtfNome);
		jtfNome.setColumns(10);
		
		JButton btnIndietro = new JButton("INDIETRO");
		btnIndietro.addActionListener((a) -> {
				new GestioneInserimenti().setVisible(true);
				chiudi();
		});
		btnIndietro.setBounds(24, 155, 89, 23);
		contentPane.add(btnIndietro);
		
		JButton btnAggiungi = new JButton("AGGIUNGI");
		
		btnAggiungi.addActionListener((a) -> {
				nomeMerce = jtfNome.getText().toLowerCase();
				
				if (nomeMerce.equals("")) {
					JOptionPane.showMessageDialog(f,
                            "Per aggiungere una merce, E' necessario inserire tutti i campi!");
                } else
                	try {
                            try {
                                GestioneMerce.creaIst().aggiungiMerce(new ImplMerce(nomeMerce));
                            } catch (ExceptionEqualName ex) {
                                JOptionPane.showMessageDialog(f, "Merce gia'  presente nel sistema!");
                                return;
                            }
                            JOptionPane.showMessageDialog(f, "Inserimento avvenuto correttamente.");
                            new GestioneInserimenti().setVisible(true);
                            chiudi();
                    }catch (HeadlessException ex2) {
                        ex2.printStackTrace();	
                    }    
		});
		btnAggiungi.setBounds(161, 155, 89, 23);
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
