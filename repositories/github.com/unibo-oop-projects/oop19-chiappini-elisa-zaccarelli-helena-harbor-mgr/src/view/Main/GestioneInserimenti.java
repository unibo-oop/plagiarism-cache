package view.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import view.Interfaces.AggiungiNave;
import view.Interfaces.AggiungiViaggio;
import view.Interfaces.VisualizzaMerce;
import view.Interfaces.VisualizzaNave;
import view.Interfaces.VisualizzaPorto;
import view.Interfaces.VisualizzaViaggio;
import view.Interfaces.AggiungiPorto;
import view.Interfaces.AggiungiMerce;
import view.Main.Main;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;



/**
 * 
 * @author Elisa Chiappini
 * Frame intermedio di scelta inserimenti e visualizzazioni
 */


public class GestioneInserimenti extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	

	

	/**
	 * Creazione del frame.
	 */
	public GestioneInserimenti() {
		this.setResizable(false);
		setTitle("GESTIONE INSERIMENTI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 334, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnNave = new JButton("NUOVA");
		btnNave.addActionListener((a) -> {
				chiudi();
				new AggiungiNave().setVisible(true);
		});
		btnNave.setBounds(217, 71, 79, 48);
		contentPane.add(btnNave);
		
		JButton btnPorto = new JButton("NUOVO");
		btnPorto.addActionListener((a) -> {
				chiudi();
				new AggiungiPorto().setVisible(true);
		});
		btnPorto.setBounds(217, 129, 79, 48);
		contentPane.add(btnPorto);
		
		JButton btnViaggio = new JButton("NUOVO");
		btnViaggio.addActionListener((a) -> {
				chiudi();
				new AggiungiViaggio().setVisible(true);
		});
		btnViaggio.setBounds(217, 188, 79, 48);
		contentPane.add(btnViaggio);
		
		JButton btnIndietro = new JButton("INDIETRO");
		btnIndietro.addActionListener((a) -> {
				new Main().setVisible(true);
				chiudi();
		});
		btnIndietro.setBounds(10, 19, 89, 23);
		contentPane.add(btnIndietro);
		
		JButton btnMerci = new JButton("NUOVA");
		btnMerci.addActionListener((a) -> {
				chiudi();
				new AggiungiMerce().setVisible(true);
		});
		btnMerci.setBounds(217, 251, 79, 48);
		contentPane.add(btnMerci);
		
		JButton btnVisualizzaNave = new JButton("VISUALIZZA");
		btnVisualizzaNave.addActionListener((a) -> {
				chiudi();
				new VisualizzaNave().setVisible(true);
		});
		btnVisualizzaNave.setBounds(100, 71, 107, 48);
		contentPane.add(btnVisualizzaNave);
		
		JLabel lblNewLabel = new JLabel("NAVE: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(26, 88, 64, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnVisualizzaPorto = new JButton("VISUALIZZA");
		btnVisualizzaPorto.addActionListener((a) -> {
				chiudi();
				new VisualizzaPorto().setVisible(true);
		});
		btnVisualizzaPorto.setBounds(100, 129, 107, 48);
		contentPane.add(btnVisualizzaPorto);
		
		JLabel lblPorto = new JLabel("PORTO: ");
		lblPorto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPorto.setBounds(26, 145, 64, 14);
		contentPane.add(lblPorto);
		
		JButton btnVisualizzaViaggio = new JButton("VISUALIZZA");
		btnVisualizzaViaggio.addActionListener((a) -> {
				chiudi();
				new VisualizzaViaggio().setVisible(true);
		});
		btnVisualizzaViaggio.setBounds(100, 188, 107, 48);
		contentPane.add(btnVisualizzaViaggio);
		
		JLabel lblViaggio = new JLabel("VIAGGIO: ");
		lblViaggio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblViaggio.setBounds(26, 204, 64, 14);
		contentPane.add(lblViaggio);
		
		JButton btnVisualizzaMerce = new JButton("VISUALIZZA");
		btnVisualizzaMerce.addActionListener ((a) -> {
				chiudi();
				new VisualizzaMerce().setVisible(true);
		});
		btnVisualizzaMerce.setBounds(100, 251, 107, 48);
		contentPane.add(btnVisualizzaMerce);
		
		JLabel lblMerce = new JLabel("MERCE: ");
		lblMerce.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMerce.setBounds(26, 267, 64, 14);
		contentPane.add(lblMerce);
	}
	
		/**
	     * Chiusura del frame
	     */
	    public void chiudi() {
	        setVisible(false);
	        dispose();
	    }
}
