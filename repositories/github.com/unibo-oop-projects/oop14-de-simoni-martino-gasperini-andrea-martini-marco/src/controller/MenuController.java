package controller;

import fileManager.Giocatore;
import gui.MenuPanel;

/**
 * 
 * 
 * Controller del pannello del menù.
 * 
 * @author Martino De Simoni
 */
/*
 * 
 * La classe implementa il pattern mvc e compone la view.
 * La presente è una classe totalmente "stupida", che definisce la sola grafica relegando al controller quasi qualsiasi azione 
 * di logica.
 *
 * Nonostante sia stata disegnata ad hoc, la classe è in buona parte riutilizzabile.
 *
 */

public class MenuController extends PanelController<MenuPanel> {
		
	private final Giocatore g;
	
	
	//Stringhe per farsi notificare dal controlledPanel
	private final String gameMessage;
	private final String optionsMessage;
	private final String userChoiceMessage;
	private final String exitMessage;
	//Stringhe per notificare il master
	private final String slaveTermination;
	
	/**
	 * Inizializza lo stato del controller.
	 * 
	 * @param _g Il giocatore di cui vengono visualizzati i dati
	 * @param termination La stringa da dare come argomento a notifyMaster() per notificare la propria terminazione.
	 * @param game Stringa che il pannello associato ritornerà a notifyController(). Il controller notificherà al master.
	 * @param options Stringa che il pannello associato ritornerà a notifyController(). Il controller notificherà al master.
	 * @param userChoice Stringa che il pannello associato ritornerà a notifyController(). Il controller notificherà al master.
	 * @param exit Stringa che il pannello associato ritornerà a notifyController(). Il controller notificherà al master.
	 * @param _master Il master (consiglio: se si usa il costruttore da master, usare "this")
	 */
	
	public MenuController(final Giocatore _g,final String termination,final String game,final String options,
											final String userChoice,final String exit, final MasterPanelController _master ){
		
		g = _g;
		gameMessage = game;
		optionsMessage = options;
		userChoiceMessage = userChoice;
		slaveTermination = termination;
		exitMessage = exit;
		
		master = _master;
		
		controlledPanel = new MenuPanel( game, options, userChoice, exit, this, g, gui.Utility.sfondo );

	}

	
	@Override
	public void slaveHasTerminated() {
		
		this.controlledPanel.setVisible(false); // questo pannello resta in memoria
		
	}

	@Override
	public void notifyController(String msg) {
		
		if( msg == gameMessage){
			
			master.notifyMaster(slaveTermination, gameMessage );
			
		}
		else if( msg == optionsMessage){
			
			master.notifyMaster(slaveTermination, optionsMessage );

		}
		else if( msg == userChoiceMessage){
			
			master.notifyMaster(slaveTermination, userChoiceMessage );

			
		}
		
		else if(msg == exitMessage){
			
			master.notifyMaster(slaveTermination, exitMessage );
		}
		
	}

	@Override
	public void run() {
		this.controlledPanel.setVisible(true);
	}
	
}
