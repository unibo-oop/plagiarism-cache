package fileManager;

import java.util.Vector;

import controller.Utility;

/**
 * 
 * I dati di un livello.
 * 
 * @author Martino De Simoni
 * 
 */
/* Andrebbe tutto final, ma la lettura/scrittura su file la lascio il più possibile C-like. 
 * Inoltre in caso ci siano più livelli di difficoltà, il controller del gioco potrebbe decidere di cambiare i dati dei livelli.
 *  */
public class Livello {

	public String nome;
	public Vector<EntrataZombie> zombie = new Vector<>();
	public int ricompensa_in_denaro; 
	//Tecnicamente nelle ricompense ci possono essere anche altre piante, e forse anche altre cose ma non ricordo il gioco. Mi fermo qui per non sforare ancora di più il tempo di programmazione stabilito, ma lascio una porta aperta ad altri campi.
	
	
	//Una mano santa in fase di debug
	/**
	* @return Rappresentazione del giocatore come stringa.
	*/
	public String toString(){
		
		return "Nome:\t"+ this.nome+"\tRicompensa in denaro:\t"+this.ricompensa_in_denaro+"\tZombie:\t"+ Utility.stringArrayToString(zombie.toArray(new String[0]));
		
	}
	
}
