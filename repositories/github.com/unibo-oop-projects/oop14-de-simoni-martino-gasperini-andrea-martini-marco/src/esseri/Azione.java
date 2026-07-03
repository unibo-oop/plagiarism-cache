package esseri;

/**
 * 
 * @author Martino De Simoni
 *
 * Le azioni definiscono il modo tramite il quale gli enti del gioco effettivo comunicano con il controller.
 *
 */


public abstract class Azione {
	
	int animazione; // Metto un int, tanto le animazioni non le sappiamo fare
	
	Azione inCasoDiFallimento; //Se la prima azione fallisce, si esegue questo campo. 
	//Per le dinamiche del gioco, non dovrebbe esserci un richiamo a cascata, quindi di fatto non sarŕ mai usato come decoratore. Preferisco lasciare quest'evenienza sia per semplicitŕ di programmazione 
	
}
