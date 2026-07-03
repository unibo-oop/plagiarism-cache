package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.PanelController;


/**
 *
 * Da questo pannello si decide quali piante utilizzare nel livello associato.
 *
 *
 * 
 * @author Martino De Simoni 
 * 
 */
/*
 * 
 * BUGGY BUGGY BUGGY
 * 
 * SO GIA' che questa classe è buggata. Premendo alcune combinazioni di tasti due bottoni potrebbero sovrapporsi. Questo perchè
 * nel gioco originale ogni bottone ha una sua posizione specifica nel pannello di scelta. Mentre sto scrivendo, non so se questo
 * aspetto verrà implementato. Nel caso, sarebbe davvero un attimo, anche più semplice di ora, programmare la visualizzazione dei
 * pulsanti sul pannello in basso.
 * 
 * La classe implementa il pattern mvc e compone la view.
 * La presente è una classe totalmente "stupida", che definisce la sola grafica relegando al controller quasi qualsiasi azione 
 * di logica.
 * 
 * La classe non presenta elementi riutilizzabili.
 *
 */

public class PlantChoicePanel extends JPanel {

	static final long serialVersionUID = 1L;
		
		private final BufferedImage background;
		private int iChosenPlant = 0;
		private int maxPlant; //aumenta solo la leggibilità
		private Vector<PlantButton> chosenButtons = new Vector<>();
		private Vector<PlantButton> choosableButtonsVector = new Vector<>();
		//GUI
		private JPanel chosenButtonsPanel = new JPanel( );
		//Bottoni
		private final JButton game = new JButton (Utility.play);
		//Stringhe per notificare il controller
		private final String gameMessage;
		//Controller
		private final PanelController<PlantChoicePanel> controller;

		
		private void setButtonsInPanel( Vector<PlantButton> buttonsToAdd, JPanel panel, int maxButtons){ //Tecnicamente dovrebbe essere solo per il pannello di sopra, ma ancora ogni pianta non ha una sua localizzazione precisa, quindi si usa anche per il pannello di sotto.
			
			panel.removeAll();
			
			int i=0;
			for(PlantButton p: buttonsToAdd){
			
			p.setBounds(i*panel.getWidth()/maxButtons, 0, 
					panel.getWidth()/maxButtons, panel.getHeight());
			panel.add(p);
			i++;
			}
			
		}
		
		public void update(final Graphics j){
			
			super.update(j);
			
		}
		
		
	/**
	 * 
	 * Metodo preso da http://www.simplesoft.it/background_image_per_componenti_java_swing.html
	 * 
	 */

		protected void paintComponent(final Graphics g) {

		    super.paintComponent(g);
		    
		    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

	 	}

	 //In realtà maxPlant dovrebbe passarlo il MasterController attraverso il PanelController, ma per questo progetto non ha senso. Non dovrebbe essere un algoritmo complicato.
		public PlantChoicePanel(final String _game, final HashSet<PlantButton> buttons, final BufferedImage _background,
				final PanelController<PlantChoicePanel> _controller, final int _maxPlant, final Dimension maxSize){
			
			//Inizializzazione campi
			controller =  _controller;
			gameMessage = _game;
			maxPlant = _maxPlant;
			
			chosenButtonsPanel = new JPanel( new GridLayout(1,maxPlant) );
			background = _background;
			
			this.setLayout( new BorderLayout() );
			
			//Creazione del pannello inferiore
			/* nel gioco originale c'è un pannello che raggruppa tre elementi:
			 * un titolo (scegli le tue piante, tipo), 
			 * un insieme di piante da scegliere 
			 * e un pulsante per confermare la scelta.*/
			
			JPanel choice = new JPanel( new GridLayout(3,1) );
			
			JLabel title = new JLabel( Utility.choose);
		
			JPanel choosableButtonsPanel = new JPanel( new FlowLayout()  );
			
			for (PlantButton p : buttons) {
				
				
				p.addActionListener(
						e-> {
							
							if(p.getParent()==choosableButtonsPanel){ //se è nei pulsanti da scegliere
							
								if(iChosenPlant<maxPlant){ //e in quelli scelti c'è posto
									
									choosableButtonsVector.remove(p);
									setButtonsInPanel( choosableButtonsVector, choosableButtonsPanel, buttons.size() );
									
									chosenButtons.add(p);
									setButtonsInPanel( chosenButtons, chosenButtonsPanel, maxPlant);
									
									this.iChosenPlant++;
									}
								
						    	} else if(p.getParent()==chosenButtonsPanel){ //Se non è nei pulsanti da scegliere, è in quelli scelti. La clausola apre la classe ad aggiunte.
						    		
						    		chosenButtons.remove(p);
									setButtonsInPanel( chosenButtons, chosenButtonsPanel, maxPlant);
						    		
						    		choosableButtonsVector.add(p);
									setButtonsInPanel( choosableButtonsVector, choosableButtonsPanel, buttons.size() );

						    		
						    		this.iChosenPlant--;
						    	}
							update(this.getGraphics());

							}

						);
				
				choosableButtonsVector.add(p);
			
			}
			
			setButtonsInPanel( choosableButtonsVector, choosableButtonsPanel, buttons.size() ); //Alla fine dell'inizializzazione, aggiungi i bottoni al pannello
			
			game.addActionListener( 
				
					e-> {
						
						this.controller.notifyController(gameMessage);
						
					}
		
					
					);
			//Add(s) di choice
			title.setHorizontalAlignment(JLabel.CENTER);
			choice.add(title);
			
			choice.add(choosableButtonsPanel);
			choice.add(game);
			//Fine dell'iniziazione del pannello
			this.setPreferredSize(maxSize);
			
			maxSize.height /= 3; //Due pannelli e uno spazio vuoto
			
			chosenButtonsPanel.setPreferredSize(maxSize);
			
			chosenButtonsPanel.setOpaque(false);

			//Add(s)
			
			this.add(chosenButtonsPanel, BorderLayout.NORTH);
			this.add(choice, BorderLayout.SOUTH);
			
			//Estetica generale
			this.setPreferredSize( getMaximumSize() );
			
		}

		public int getChosenPlantSize() {
			return iChosenPlant;
		}
		
		public PlantButton[] getChosenPlants(){
			
			return (PlantButton[]) chosenButtons.toArray();
			
		}
		
		
	}

	
