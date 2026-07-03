package fileManager;

/**
 * 
 * Validissima alternativa che ha risparmiato nullPointException fastidiose fin dalle prime fasi di debug. Da passare di default 
 * al posto di null nelle variabili non final.
 *
 * @author Martino De Simoni
 */

public class NessunGiocatore extends Giocatore {

	
	public final static Giocatore nessunGiocatore = new NessunGiocatore(); //pattern Singleton

	
	private NessunGiocatore(){
			
			super("NessunGiocatore");
		
		}
	
}



