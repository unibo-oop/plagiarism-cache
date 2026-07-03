package fileManager;

/**
 * Descrizione dell'entrata di uno zombie nell'interfaccia di gioco.
 * 
 * @author Martino De Simoni
 *
 */

public class EntrataZombie {

	public int tempoInMs; //a prova di idiota
	public String nomeZombie;
	public int laneId; //intuitivamente, la lane 1 sarà la prima lane a partire dall'alto.
	/**
	 * 
	 * @param _t tempo di entrata in ms
	 * @param _n nome della classe dello zombie
	 * @param _l nome della lane in cui lo zombie comparirà
	 */
	public EntrataZombie(int _t, String _n, int _l){
		
		this.tempoInMs = _t;
		this.nomeZombie = _n;
		this.laneId = _l;
		
	}
	
}
