package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.PanelController;
import esseri.Posizione2D;

/**
 * 
 * Da questo pannello si gioca alla partita di campagna.
 *
 * @author Martino De Simoni 
 */
/*
 * La classe implementa il pattern mvc e compone la view.
 * La presente è una classe totalmente "stupida", che definisce la sola grafica relegando al controller quasi qualsiasi azione 
 * di logica.
 *
 * 
 * 
 */

//TODO la classe dovrebbe in realtà estendere un'astratta o un'interfaccia GamePanel solo per il polimorfismo, e poi gli elementi del model 
//richiedere nel costruttore un InsertionPanelController<Azione, ? extends GamePanel>

public class CampaignPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final PlantButton[] plantButtons;
	private int maxPlant = 6; //sarebbe da passare al costruttore e da contenere in un oggetto Giocatore. Per adesso va bene così
	private int suns = 0;
	private final Integer COLS;
	private final Integer ROWS;
	private final JPanel griglia;
	private final String shovelMessage;
	private final PanelController<CampaignPanel> controller;
	private HashMap<Posizione2D,BufferedImage> immagini = new HashMap<>(); //Per non lasciare a null. 
	
	private void setImmagini(HashMap<Posizione2D,BufferedImage> nuoveImmagini){
		
		immagini = nuoveImmagini;
		
	}
	
	/*
	 * L'erba viene fatta tutta di un colore per mancanza di immagini. Commento il codice come davvero dovrebbe essere e 
	 * implemento un surrogato.
	 */
	
	private void colorPvZGrid(final JPanel gridLayoutPanel, Integer rows, Integer cols ){
		
		int r=0,c=0;
		for (r=0;r<ROWS;r++)
			for(c=0;c<COLS;c++){
		//		if( (r+c) % 2  == 0) griglia.add(new JLabel(Utility.greenGrass));
		//		else if (r%2== 0 ) griglia.add(new JLabel(Utility.darkGreenGrass));
		//		else griglia.add(new JLabel(Utility.lightGreenGrass));
				BufferedImage essere = immagini.get( new Posizione2D(c,r) );
				if(essere == null)
					gridLayoutPanel.add(new JLabel( (Icon) Utility.erba));
				else
					gridLayoutPanel.add(new JLabel( (Icon) (Utility.mergeImage(Utility.erba,essere) ) ) );
			}
		
	}
	
	public void updatePiante (HashMap<Posizione2D,BufferedImage> nuoveImmagini){
		
		super.update(this.getGraphics());
		
		setImmagini(nuoveImmagini);
		colorPvZGrid(griglia,ROWS,COLS);
		
	}
	
	public CampaignPanel ( final PlantButton[] _plantButtons, int _cols, int _rows,
			final PanelController<CampaignPanel> _controller, final String _shovelMessage) {
		
		shovelMessage = _shovelMessage;
		controller = _controller;
		COLS = _cols;
		ROWS = _rows;
		
		plantButtons = _plantButtons;
		
		this.setLayout(new BorderLayout() );
		//Inizializzo il pannello in alto. Per chiarimenti, guardare una schermata del gioco originale
		JPanel pannelloInAlto = new JPanel(); 
			//Inizializzo il robo dei soli
		JLabel picLabel = new JLabel(Integer.toString(suns), 
				new ImageIcon(Utility.sole),JLabel.HORIZONTAL); //riga presa da http://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
		pannelloInAlto.add(picLabel);
		
			//Inizializzo i bottoni delle piante
		int i=1;
		int maxThings=maxPlant+1+1; //Nel pannello ci stanno nell'ordine: il sole, i bottoni delle piante, e il bottone della pala 
		for(PlantButton p: plantButtons){
		
			p.addActionListener(
					e-> {
						this.controller.notifyController(p.getID());
					});
			
			p.setBounds(i*pannelloInAlto.getWidth()/maxThings, 0, 
				pannelloInAlto.getWidth()/maxThings, pannelloInAlto.getHeight());
			pannelloInAlto.add(p);
			i++;
		}
			//Inizializzo il bottone della pala
		JButton pala = new JButton();
		pala.setIcon( (Icon) Utility.pala);
		pala.setVerticalAlignment(SwingConstants.BOTTOM);
		pala.setBackground(Color.orange);
		
		pala.addActionListener(
				e-> {
					
					this.controller.notifyController(shovelMessage);
				});
		
		pannelloInAlto.add(pala);
			//Add del pannello
		this.add(pannelloInAlto,BorderLayout.NORTH);		
		
		//Griglia di sotto da aggiungere a BorderLayout.CENTER. Poi basta, si va avanti cogli actionListener
		griglia = new JPanel( new GridLayout(ROWS,COLS));
		colorPvZGrid(griglia,ROWS,COLS);
		
		this.add(griglia, BorderLayout.CENTER);
		
		/*
		 * Ora sembrerà che stia violentando il pattern mvc mettendo un pizzico di logica nella view:
         *  se viene cliccato un bottone, notifica il controller (già implementato)
		 * 	se viene cliccata la griglia, notifica il controller
		 *  non fare nulla altrimenti.
		 *  
		 *  Per capire che è stata la griglia ad essere cliccata, devo mettere dei controlli. Questo non danneggia per davvero
		 *  il pattern mvc, si rende solo la griglia un sistema di input come i bottoni.
		 * 
		 */
		
		//Notifica il controller se viene cliccata la griglia
		 this.addMouseListener(new MouseAdapter() {
		     @Override
		     public void mouseClicked(MouseEvent e) {
		    	 
		    	 
		        Point mouse = getMousePosition();
		       
		        //Se si è fuori dai limiti, non fare niente.
		        if(mouse!= null && (mouse.x<griglia.getX() || mouse.y<griglia.getY() || mouse.x>griglia.getX()+griglia.getWidth()
		        		|| mouse.y>griglia.getY()+griglia.getHeight() ) )
		        	return; 
		        else {
		        	
		        	//Il codice che segue sembra complicato, ma un disegno può aiutare molto.
		        	controller.notifyController( new String
		        			( Integer.toString( ( mouse.x-griglia.getX() ) / (griglia.getWidth()/COLS) ).concat(" "
		        			+ Integer.toString( ( mouse.y-griglia.getY() ) / (griglia.getHeight()/ROWS) )
		        					) ) );
		        }
		     }
		  });
		
	}
	
	
	
}
