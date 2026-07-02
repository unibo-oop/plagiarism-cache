package controller;
/**
 * Intefaccia del Controller.
 * 
 * @author Beatrice Ricci, Nicola Santolini
 *
 */
public interface IController {
	
	/**
	 * Metodo getter del numero contenuto in una certa posizione della matrice del modello.
	 * @param i prima coordinata
	 * @param j seconda coordinata 
	 * @return valore numerico che indica il colore dell'elemento
	 */
	int getModelNum(final int i, final int j);
	
	/**
	 * Metodo getter che restituisce il tipo di un elemento contenuto in una certa posizione della matrice del modello.
	 * @param i prima coordinata
	 * @param j seconda coordinata
	 * @return valore numerico che indica il tipo di elemento
	 */
	int getModelType(final int i, final int j);
	
	/**
	 * Metodo getter del numero di mosse contenute nel modello.
	 * @return numero di mosse disponibili
	 */
	int getModelMoves();
	
	/**
	 * Metodo getter del numero di punti accumulati nel modello.
	 * @return punti
	 */
	int getModelScore();

	/**
	 * Metodo per inizializzare i dati del model in modo che sappia le mosse e l'obiettivo del livello
	 * scelto dall'utente.
	 * @param moves numero mosse da inizializzare
	 * @param targetScore obiettivo da inizializzare
	 */
	void setInitialConditions(final int moves, final int targetScore);
	/**
	 * Getter del listener della GUI.
	 * @return listener
	 */
	Listener getObserver();
	
	/**
	 * Metodo che quando vengono selezionate due caramelle sulla matrice di gioco notifica la loro posizione 
	 * per poi eseguire i necessari controlli per convalidare o meno la mossa che si deve fare.
	 * 
	 * @param x1 coordinata di riga del primo elemento
	 * @param y1 coordinata di colonna del secondo elemento
	 * @param x2 coordinata di riga del primo elemento
	 * @param y2 coordinata di colonna del secondo elemento
	 */
	void makeMove(final int x1, final int y1, final int x2, final int y2);
}
