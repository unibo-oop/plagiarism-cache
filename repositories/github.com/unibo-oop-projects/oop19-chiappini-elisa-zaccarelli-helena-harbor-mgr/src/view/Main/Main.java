package view.Main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import view.Interfaces.AggiungiPrenotazione;
import view.Interfaces.Statistiche;
import view.Interfaces.VisualizzaPrenotazione;


/**
 * 
 * @author Elisa Chiappini
 * Classe che fa partire l'applicazione
 */

public class Main extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JButton sceltaInserimento;
    private JButton sceltaPrenotazione;
    private JButton sceltaVisualizzaPrenotazioni;
    private JButton sceltaStatistiche;
    private JButton chiudi;
    
    
    
    
	 public static void main(String[] args) {
		 
	        Main frame = new Main();
	        frame.setVisible(true);
	    }
	
	 /*Creazione Frame*/
	 
	public Main(){
		this.setTitle("MENU PRINCIPALE");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(450, 150, 328, 385);
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
	
		sceltaInserimento = new JButton("INSERIMENTO/VISUALIZZA DATI");
		sceltaInserimento.setBounds(30, 15, 235, 52);
		contentPane.add(sceltaInserimento);
	
		sceltaPrenotazione = new JButton("NUOVA PRENOTAZIONE");
		sceltaPrenotazione.setBounds(30, 78, 235, 74);
		contentPane.add(sceltaPrenotazione);
		
		sceltaVisualizzaPrenotazioni = new JButton("VISUALIZZA PRENOTAZIONI");
		sceltaVisualizzaPrenotazioni.setBounds(30, 163, 235, 52);
		contentPane.add(sceltaVisualizzaPrenotazioni);
		
		sceltaStatistiche = new JButton("STATISTICHE VIAGGI");
		sceltaStatistiche.setBounds(30, 226, 235, 52);
		contentPane.add(sceltaStatistiche);
	
		chiudi = new JButton ("CHIUDI PROGRAMMA");
		chiudi.setBounds(30, 294, 235, 41);
		contentPane.add(chiudi);
		
		
		sceltaInserimento.addActionListener((a) -> {
				new GestioneInserimenti().setVisible(true);
				chiudi();
		});
			
	 	sceltaPrenotazione.addActionListener((a) -> {
	 			new AggiungiPrenotazione().setVisible(true);
	 			chiudi();
	 	});
	 	
	 	sceltaVisualizzaPrenotazioni.addActionListener((a) -> {
	 			new VisualizzaPrenotazione().setVisible(true);
	 			chiudi();
	 	});
	 	
	 	sceltaStatistiche.addActionListener((a) -> {
	 		new Statistiche().setVisible(true);
	 		chiudi();
	 	});
	
		chiudi.addActionListener((a) -> {
				chiudi();
		});
	}
	
	/**
	 * Chiusura frame
	 */
	
		private void chiudi() {
			setVisible(false);
			dispose();
		}
	}

	
