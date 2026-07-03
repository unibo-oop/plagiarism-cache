package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.PanelController;
import fileManager.Giocatore;

/**
 * 
 *
 * Da questo pannello si decide a quale altro dei pannelli passare, come in qualsiasi menù di gioco.
 *
 * @author Martino De Simoni 
 */
/*
 * La classe implementa il pattern mvc e compone la view.
 * La presente è una classe totalmente "stupida", che definisce la sola grafica relegando al controller quasi qualsiasi azione 
 * di logica.
 *
 * Eccezion fatta per i bottoni, la classe è totalmente riutilizzabile.
 * 
 */

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final Giocatore g;
	private final BufferedImage background;
	//Bottoni
	private final JButton campaign;
	private final JButton options = new JButton (Utility.options);
	private final JButton backToUserChoice = new JButton (Utility.backToUserChoice);
	private final JButton exit = new JButton (Utility.saveAndExit);
	private JLabel dati = new JLabel(); //NON deve essere final, i dati cambiano nel tempo
	//Stringhe per notificare il controller
	private final String gameMessage;
	private final String optionsMessage;
	private final String userChoiceMessage;
	private final String exitMessage;
	//Controller
	private final PanelController<MenuPanel> controller;
	
public void update(final Graphics j){
		
		dati = new JLabel(Utility.name + ": " + g.nome + "  " 
							  + Utility.money + ": " + g.soldi);
		
		
		dati.setHorizontalAlignment(this.getWidth()/2);
		
		this.add(dati,BorderLayout.CENTER);
		
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

	public MenuPanel(final String _game, final String _options, final String _userChoice, final String _exit,
						final PanelController<MenuPanel> _controller, final Giocatore _g, final BufferedImage _background){
		
		//Inizializzazione campi
		gameMessage = _game;
		optionsMessage = _options;
		userChoiceMessage = _userChoice;
		exitMessage = _exit;
		
		controller = _controller;
		
		g=_g;
		
		background = _background;
		
		this.setLayout( new BorderLayout() );
		
		JPanel buttonsPanel = new JPanel( new FlowLayout() );
		buttonsPanel.setOpaque(false);
		//Inizializzazione bottoni
		
		
		campaign = new JButton (Utility.playCampaign+" Livello "+this.g.livello);
		
		buttonsPanel.add(campaign);
		buttonsPanel.add(options);
		buttonsPanel.add(backToUserChoice);
		buttonsPanel.add(exit);
		
		this.add(buttonsPanel,BorderLayout.NORTH);
		
		campaign.addActionListener(e-> {
		       this.controller.notifyController( gameMessage );
		      });
			
		options.addActionListener(e-> {
			   this.controller.notifyController( optionsMessage );
		});
			
		backToUserChoice.addActionListener(e-> {	
			   this.controller.notifyController( userChoiceMessage );
		});
	
		exit.addActionListener(e-> {	
			   this.controller.notifyController( exitMessage );
		});
		
		this.setPreferredSize( getMaximumSize() );
		
		update(this.getGraphics());
	}
	
}
