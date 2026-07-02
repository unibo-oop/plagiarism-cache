package model;

/**
 * Interfaccia del modello.
 * 
 * @author Nicola Santolini
 *
 */
public interface IModel {
	
	/**
	 * Metodo per decrementare le mosse rimanenti.
	 */
	void decMoves();
	
	/**
	 * Getter del punteggio corrente.
	 * 
	 * @return il punteggio attuale
	 */
	int getScore();
	
	/**
	 * Getter delle mosse rimanenti.
	 * 
	 * @return il numero di mosse rimanenti
	 */
	int getMoves();
	
	/**
	 * Getter dell'obiettivo di punti da raggiungere.
	 * 
	 * @return il punteggio da raggiungere
	 */
	int getTarget();
		
	/**
	 * Getter del colore di un elemento della matrice di gioco.
	 * date le sue coordinate
	 * 
	 * @param i indice di riga
	 * @param j indice di colonna
	 * @return il colore dell'elemento richiesto
	 */
	int getColor(final int i, final int j);
	
	/**
	 * Getter del tipo di un elemento della matrice di gioco date le sue coordinate.
	 * 
	 * @param i indice di riga
	 * @param j indice di colonna
	 * @return il tipo dell'elemento richiesto
	 */
	int getTypeEl(final int i, final int j);
	
	/**
	 * Setter delle mosse di inizio livello, in base alla difficoltà.
	 * 
	 * @param num punteggio da settare
	 */
	void setMoves(final int num);
	
	/**
	 * Metodo che incrementa il punteggio.
	 * 
	 * @param num numero di punti da aggiungere a quelli attuali.
	 */
	void incScore(final int num);
	
	/**
	 * Metodo che inizializza il punteggio da raggiungere in base alla difficootà selezionata.
	 * 
	 * @param num punteggio da settare
	 */
	void setTarget(final int num);
	
	/**
	 * Metodo che verifica se due coordinate sono compatibili per uno scambio,
	 * cioè sono adiacenti ma non poste in diagonale.
	 * 
	 * @param x1 indice di riga della prima coordinata
	 * @param y1 indice di colonna della prima coordinata
	 * @param x2 indice di riga della seconda coordinata
	 * @param y2 indice di colonna della seconda coordinata
	 * @return true se lo scambio può essere fatto
	 */
	boolean checkExchange(final int x1, final int y1, final int x2, final int y2);
	
	/**
	 * Metodo che esegue uno scambio tra due caramelle della matrice di gioco.
	 * 
	 * @param x1 indice di riga della prima coordinata
	 * @param y1 indice di colonna della prima coordinata
	 * @param x2 indice di riga della seconda coordinata
	 * @param y2 indice di colonna della seconda coordinata
	 */
	void doExchange(final int x1, final int y1, final int x2, final int y2);
	
	/**
	 * Metodo che verifica se nella matrice di gioco attuale è in atto una combinazione
	 * ed è necessario continuare ad eliminare caramelle e aggiornare la matrice. Quando
	 * la matrice si trova in uno stato senza più combinazioni in atto, se ne notifica il
	 * controller.
	 * 
	 *  @return true se bisogna continuare con il game loop
	 */
	boolean goOn();
	
	/**
	 * Metodo che verifica la possibilità di completare almeno una mossa 
	 * col set di caramelle attuale.
	 * 
	 * @return true se è possibile realizzare almeno una combinazione in verticale e/o
	 * orizzontale in una sola mossa
	 */
	boolean checkNextMove();
	
	/**
	 * Metodo invocato finchè sulla matrice esiste almeno un combinazione, verifica di quale 
	 * tipo di combinazione si tratta e attiva il behaviour corrispondente, finchè la matrice non 
	 * viene a trovarsi in una situazione senza combinazioni in atto.
	 */
	void gameLoop();
	
	/**
	 * Metodo che controlla se si sta facendo una mossa che coinvolge una caramella SPECIAL, il cui behaviour
	 * deve essere in ogni caso attivato, indipendentemente dal fatto che la mossa generi una combinazione o meno.
	 * 
	 * @param x1 indice di riga della prima coordinata
	 * @param y1 indice di colonna della prima coordinata
	 * @param x2 indice di riga della seconda coordinata
	 * @param y2 indice di colonna della seconda coordinata
	 * @return true se si sta attivando una caramella SPECIAL
	 */
	boolean isUsingSpecial(final int x1, final int y1, final int x2, final int y2);
	
	/**
	 * Metodo da invocare qualora isUsingSpecial sia vero, che attiva il behaviour speciale della caramella SPECIAL.
	 * 
	 * @param x1 indice di riga della prima coordinata
	 * @param y1 indice di colonna della prima coordinata
	 * @param x2 indice di riga della seconda coordinata
	 * @param y2 indice di colonna della seconda coordinata
	*/
	void makeSpecial(final int x1, final int y1, final int x2, final int y2);
	
	/**
	 * Metodo che invoca la funzionalità di shuffle della matrice di gioco.
	 */
	void shuffle();
}
