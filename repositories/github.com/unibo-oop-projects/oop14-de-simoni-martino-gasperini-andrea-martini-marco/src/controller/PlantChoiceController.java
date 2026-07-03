package controller;

import java.awt.Dimension;
import java.util.HashSet;

import javax.swing.ImageIcon;

import fileManager.Giocatore;
import gui.PlantButton;
import gui.PlantChoicePanel;

/**
 * 
 * 
 * Controller del pannello della scelta delle piante da utilizzare.
 * 
 * @author Martino De Simoni
 * 
 */
/*
 * La classe implementa il pattern mvc e il controller.
 *
 * La classe è difficilmente riutilizzabile. Sarebbe più semplice ricominciare da capo e prendere a modello gli altri controller.
 *
 */
//BUGGY riga 49 circa: bottoni inizializzati per debug
public class PlantChoiceController extends PanelController<PlantChoicePanel>{

	final private Giocatore g;
	final private String gameMessage; 
	final private String terminationString;
	final private MasterPanelController master;
	final private int maxPlant=6; //TODO a dir la verità dovrebbe andare nei dati del giocatore. 
	
	/**
	 * 
	 * @param _terminationString Stringa da mettere come argomento di notifyMaster per notificargli una terminazione.
	 * @param _gameMessage       Stringa che il pannello associato ritornerà a notifyController(). Il controller notificherà al master.
	 * @param _g				 Il giocatore che sceglie le proprie piante.
	 * @param _master			 Il master di questo controller.
	 * @param maxSize			 Dimensione massima del frame.
	 */
	
	public PlantChoiceController( final String _terminationString, final String _gameMessage, final Giocatore _g,
			final MasterPanelController _master, final Dimension maxSize){
	
		g = _g;
		gameMessage = _gameMessage;
		terminationString = _terminationString;
		master = _master;
		
		
		//Inizializzare i plantbuttons e il panel
		HashSet<PlantButton> bottoni = new HashSet<>();
	
		for (String p :  g.pianteSbloccate) { //TODO Dovrebbe essere una pianta, ma le piante non sono ancora arrivate al porto di bitbucket
			bottoni.add( new PlantButton( new ImageIcon ( gui.Utility.logo),49,p) );
		}
		controlledPanel = new PlantChoicePanel(gameMessage, bottoni, gui.Utility.sfondo, this, maxPlant, maxSize );
		
	}

	@Override
	public void slaveHasTerminated() {
		
		this.controlledPanel.setVisible(false);
		
	}

	@Override
	public void notifyController(String msg) {

		if(msg == gameMessage){
			if(controlledPanel.getChosenPlantSize()==this.maxPlant){
				
				this.master.notifyMaster(terminationString, controlledPanel.getChosenPlants());
			}
		}
	}

	@Override
	public void run() {

		this.controlledPanel.setVisible(true);

	}
	
}
