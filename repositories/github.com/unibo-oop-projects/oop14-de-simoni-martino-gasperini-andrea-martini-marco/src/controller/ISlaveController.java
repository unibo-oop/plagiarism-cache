package controller;

/**
 *  
 *
 * Interfaccia per gli SlaveController, che devono sempre avere un metodo per cominciare (run) e per terminare (slaveHasTerminated).
 * Con questa coppia di metodi si delega tutto al lavoro allo slave lasciando il master completamente all'oscuro di ciò che accade,
 * relegandolo alla sola funzione di "custode" della sua risorsa. 
 *
 *
 *  @author Martino De Simoni
 *
 */
//Nel caso del FrameController, la risorsa è il frame.
//Anche IController estende Runnable, definire anche qui l'estensione dà robustezza al codice e facilità alla lettura.

public interface ISlaveController extends IController, Runnable {
	
	/**
	 * Metodo attraverso il quale lo slave termina le proprie funzioni. L'opposto del
	 * metodo run() con cui lo slave comincia.
	 * 		 
	 */
	
	public void slaveHasTerminated(); //Lo slave in questione è l'oggetto su cui è richiamato il metodo.
	
}
