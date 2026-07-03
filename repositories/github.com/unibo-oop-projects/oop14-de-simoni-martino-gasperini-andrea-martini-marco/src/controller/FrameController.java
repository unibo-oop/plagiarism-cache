package controller;

import fileManager.Giocatore;

import fileManager.NessunGiocatore;
import fileManager.UserDataManager;
import gui.MainFrame;
import gui.Utility;

import java.awt.EventQueue;


/**
 * 
 * 
 * Controller del frame principale (e unico in questo progetto), e master dei vari panelController.
 * 
 * @author Martino De Simoni
 */  
/*
 * Classe disegnata ad hoc e difficilmente riutilizzabile.
 * 
 */



public class FrameController extends MasterPanelController implements Runnable {

	//Stringhe-ID che il frameController passa ai PanelController per riconoscerli. Non devono essere nè visualizzate nè tradotte. A dir la verità conta solo l'indirizzo, non il valore. Scelta a mio giudizio migliore delle enum.
	
	private final String menuMessage = "menù";
	private final String userChoiceMessage = "userChoice";
	private final String plantChoiceMessage = "scelta";
	private final String trueGameMessage = "game";
	private final String optionsMessage = "options";
	private final String exitMessage = "exit";
	
	//Oggetti per operare su file
	
	private Giocatore g = NessunGiocatore.nessunGiocatore; //può cambiare a seconda della scelta utente. Importante non mettere questo campo a final
	private final UserDataManager dataManager;
	
	/**
	 * Inizializza il frame, il dataManager e i livelli.
	 */
	
	public FrameController(){
		
		frame = new MainFrame(gui.Utility.title,Utility.logo );
		dataManager = new UserDataManager( controller.Utility.fileDatiUtenti ); //deve davvero stare qui?

	}
	/**
	 * Alias di PlantsVsZombies
	 */
	
	@Override
	public void run() {
			plantsVsZombies();	
	}
	
	/**
	 * Inizio del gioco Piante contro Zombie.
	 * Ha il ruolo del metodo run nelle altre classi: raccoglie le istruzioni da eseguire all'avvio del controller. 
	 * Questo metodo è anche un alias di run.
	 */
	
	public void plantsVsZombies() {
		
		UserChoiceController userChoice = new UserChoiceController( dataManager , frame, userChoiceMessage,this);
		slaves.put(userChoiceMessage,userChoice);
		
		frame.setMainPanel(slaves.get(userChoiceMessage).getControlledPanel(), true);
		frame.setVisible(true);
		slaves.get(userChoiceMessage).run();
		
	}	

	/*
	 * Ogni blocco che comincia con un if descrive la logica relativa al controller definito nella clausola.
	 * Per esempio, nel blocco if (msg==MenuMessage){} si definisce il comportamento del Pannello del menù.
	 * 
	 * Lascio notare che le stringhe non devono solo fare match, ma devono proprio essere la stessa.
	 */
	/**
	 * Gli slave notificano al master la loro terminazione, tramite l'ID che il master consegna loro, dai costruttori.
	 * 
	 *  @param msg 
	 *  			ID, passato dal master, del PanelController-slave che richiama il metodo.
	 *  @param args
	 *  		 	argomenti utili all'esecuzione.
	 */
	@Override
	public void notifyMaster(String msg, Object args) {
		
		if (msg==menuMessage){ 
			
			//Il secondo argomento è l'id del PanelController a cui si vuole passare
			if( (String) args == userChoiceMessage) {
				
				//cambio di utente: togli tutto
				slaves.clear(); 
				frame.removeAll();
				this.frame.setVisible(false);
				//ricomincia da capo 
				new FrameController().plantsVsZombies(); 
				
			}
				
			else if ( (String) args == optionsMessage) {} //Opzioni non implementate
			
			else if ( (String) args == plantChoiceMessage) {
				
				slaves.get(msg).slaveHasTerminated();
				PlantChoiceController p = new PlantChoiceController(plantChoiceMessage, trueGameMessage, g, this, frame.getSize());
				frame.setMainPanel(p.controlledPanel, true);
				p.run(); //Alla fine del metodo, p viene preso dal garbage collector
			}
		
			else if ( (String) args == exitMessage) {
				
				dataManager.salvaGiocatore(g); //Dovrebbe davvero stare qui? Il dataManager deve stare nella classe di chi assegna il frame? O i controller possono inizializzare una parte del loro stato?
				System.exit(0);
				
			}
			
		} else if(msg==userChoiceMessage){ 
			//può andare solo al menu, il secondo argomento è il giocatore selezionato
			g = (Giocatore) args;
			
			MenuController menu = new MenuController( g, menuMessage,plantChoiceMessage, optionsMessage,userChoiceMessage,exitMessage,this);
			slaves.put(menuMessage, menu);
			
			slaves.get(userChoiceMessage).slaveHasTerminated();
			
			frame.setMainPanel( slaves.get(menuMessage).getControlledPanel(), true);
			slaves.get(menuMessage).run();
			
		} else if(msg==plantChoiceMessage){
			
		} else if(msg==optionsMessage){
			
		}
		
	}

	
	//	 Per ora, il frame non ha bisogno di comunicare nulla.

	@Override
	public void notifyController(String msg) {
		
	}

	public static void main(String args[]){
		
		 EventQueue.invokeLater(new Runnable(){
		     public void run(){
		         try{

		        	 new FrameController().plantsVsZombies();
		
		}catch(Exception e){
		             e.printStackTrace();
		         }

		     }});
		
	}
	
}
