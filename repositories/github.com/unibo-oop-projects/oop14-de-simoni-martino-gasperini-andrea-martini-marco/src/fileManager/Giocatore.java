package fileManager;

import java.util.HashSet;

import controller.Utility;

/**
 *
 * Classe per i dati di ogni utente.
 *
 * @author Martino De Simoni
 */

public class Giocatore {
	
	public String nome;
	public int soldi=0;
	public String livello="1"; //Per il progetto non sono previsti i livelli
	public HashSet<String> pianteSbloccate = new HashSet<>();
	
	/**
	 * 
	 * @param Nome Nome del giocatore
	 */
	
	public Giocatore(String Nome){
		
		this.nome=Nome;
		
		}
	
	//Una mano santa in fase di debug
	/**
	 * @return Rappresentazione del giocatore come stringa.
	 */
	
	public String toString(){
		
		return "Nome:\t"+ this.nome+"\tLivello:\t"+this.livello+"\tSoldi:\t"+this.soldi+"\tPiante:\t"+ Utility.stringArrayToString(pianteSbloccate.toArray(new String[0]));
		
	}
	
}
