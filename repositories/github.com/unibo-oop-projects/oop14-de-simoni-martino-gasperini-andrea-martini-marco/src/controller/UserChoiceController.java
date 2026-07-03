package controller;

import fileManager.Giocatore;
import fileManager.UserDataManager;
import gui.MyFrame;
import gui.UserChoicePanel;

import java.util.HashSet;

/**
 * 
 * 
 * Controller del pannello di scelta utente. 
 * 
 *  @author Martino De Simoni
 */

/*
 * La classe implementa il pattern mvc e compone il controller.
 *
 * La classe è difficilmente riutilizzabile. Sarebbe più semplice ricominciare da capo e prendere a modello gli altri controller.
 *
 */

public class UserChoiceController extends InsertionPanelController<String, UserChoicePanel> {

	private final UserDataManager dataManager;
	//Stringhe di notifica del controller
	private final String slaveTermination;
	private final String remove = "remove"; //Uso interno, il Master non c'entra
	private final String addByDialog = "addByDialog"; //Uso interno, il Master non c'entra
	
	public UserChoiceController( final UserDataManager _dataManager , final MyFrame frame, final String terminationString, 
																							final MasterPanelController master){
		
		this.master = master;
		this.slaveTermination = terminationString;
		this.frame = frame;
		dataManager = _dataManager;
		
		//Trasformare un HashSet<Giocatore> in un HashSet<String> con i nomi dei giocatori
		this.set = new HashSet<>();
		HashSet<Giocatore> giocatori = dataManager.leggiDatiGiocatori();
				
		for(Giocatore g: giocatori){
				this.set.add( Utility.tokenToName(g.nome) );
			}
		//Fine stralcio
		
		controlledPanel = new UserChoicePanel( gui.Utility.sfondo, this.set, this,slaveTermination,remove,addByDialog, frame.getSize() ); //Il pannello deve stare qui, altrimenti: o l'oggetto di questa classe viene mandato come thread, o viene chiesto l'inserimento da JDialog di un nome utente prima che il pannello faccia parte del frame associato.
		
	}

	/*
	 *  L'overload mi serve per programmare la GUI: il MultipleChoicePanel non sa neanche da dove bisogna togliere la stringa. 
	 *  Mi sembra più corretto nell'ottica di incapsulamento e MVC, ma non è affatto un must. Più una finezza.
	 * 
	 */
	
	/**
	 * 
	 *  playerNames, in delete e insert, è l'insieme di stringhe che la gui deve visualizzare, non le etichette dei giocatori.
	
	 *  
	 */

	/**
	 * @param playerName Nome del giocatore in forma di stringa
	 */
	
    public void delete(final String playerName) {
		
		super.delete( playerName );
		dataManager.cancellaDatiGiocatore( playerName );

	}

    /**
	 * @param playerName Nome del giocatore in forma di stringa
	 */
    
	public void insert(final String playerName) {

		super.insert( Utility.tokenToName( playerName ) );
		dataManager.appendiDatiGiocatore( new Giocatore ( playerName ) );

	}
	

	@Override
	public void slaveHasTerminated() {
			
		this.controlledPanel.setVisible(false);// questo pannello resta in memoria
			
	}

	/*
	 * Questo metodo non va usato dalla view.
	 * 
	 * Vedo la view come un insieme "stupido" di elementi grafici. Trovo perciò inopportuno lasciar passare da view degli elementi
	 * utili all'elaborazione, ma farli prelevare dal controller sull'oggetto controllato.
	 * 
	 */
	/**
	 * Notifica al controller quale azione intraprendere sull'oggetto controllato.
	 * 
	 * @param msg 
	 * 				ID del pulsante passato dal controller al pannello	 * 
	 * 
	 */
	
	@Override
	public void notifyController(final String msg) {
		
		if (msg==this.addByDialog) {
			//Per migliorare a facilità di scrittura su file, non sempre viene stampato quello che viene inserito. 
			//Per esempio: Mario_rossi viene salvato come Mario rossi. L'input viene ripulito dei suoi aspetti da token e reso un
			//nome come si è preferito stampare durante la progettazione.
		    //TODO Andrebbe un'interfaccia a parte con il metodo inputByDialog, o comunque un metodo di input, per la riutilizzabilità.
			String newPlayer = Utility.tokenToName( ( (UserChoicePanel) controlledPanel) .inputByDialog(gui.Utility.title,
																								gui.Utility.addUserQuestion) );
			
			if(newPlayer!=null){ //Se è stato inserito qualcosa
				if (this.controlledPanel.getChoices().contains(newPlayer)) { //e quel qualcosa è già stato inserito in precedenza
					this.controlledPanel.messageByDialog( gui.Utility.userAlreadyExistError ); //manda un messaggio di errore
					}
				else insert ( newPlayer ); //Altrimenti inseriscilo.
			}
			
			this.controlledPanel.update( controlledPanel.getGraphics() );//Aggiorna la GUI.

		}
			
		else if (msg==this.remove) {
			
			delete( this.controlledPanel.getList().getSelectedValue());
			this.controlledPanel.update( controlledPanel.getGraphics() );

		}
		
		else if (msg==this.slaveTermination) {
			
			if( !controlledPanel.getList().isSelectionEmpty() ){ // se qualcosa è selezionato
				//primo argomento: identificativo. Secondo argomento: giocatore selezionato.
				master.notifyMaster( slaveTermination, dataManager.cercaGiocatoreInFile( controlledPanel.getList().getSelectedValue() ));	
			}
			
		}
	}

/*
 * Lascio notare, che il set di Giocatore è vuoto solo in alcuni casi all'avvio. Altrimenti, per essere andati oltre
 * il pannello di inserimento utente, si deve aver creato un utente, quindi si ritorna senza l'inputDialog.
 * 
 * Questo risolve l'eventuale problema di una JDialog di input che compaia prima che il FrameController abbia selezionato 
 * il controlledPanel come mainPanel del frame associato. In alternativa, per dare sicurezza al codice, si potrebbe mandare
 * in esecuzione l'UserChoiceController come Thread, che è facile e veloce. Non mi sento di raccomandare l'utilizzo di thread
 * per questo gioco, che anche nella versione originale viene criticato per l'utilizzo grossolano delle prestazioni.
 * 
 */
	
public void run(){
		
		

		this.controlledPanel.setVisible(true);
		
		if(this.set.isEmpty()) this.notifyController(this.addByDialog);

	}


	
}
