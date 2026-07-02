
package model;

/**
 * Intefaccia che modella la matrice di gioco.
 * 
 * @author Nicola Santolini
 */
public interface IBoard {

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
	 * Getter del colore di un elemento della matrice di gioco
	 * date le sue coordinate.
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
	 * Metodo setter del colore di un elemento della matrice, date le sue coordinate.
	 * 
	 * @param x indice di riga
	 * @param y indice di colonna
	 * @param c colore da settare
	 */
	void setColor(final int x, final int y, final int c);
	
	/**
	 * Metodo setter del tipo di un elemento della matrice, date le sue coordinate.
	 *
	 * @param x indice di riga
	 * @param y indice di colonna
	 * @param t colore da settare
	 */
	void setType(final int x, final int y, final int t);
	
	/**
	 * Metodo che verifica se esiste almeno una combinazione di tre elementi. In questo caso non è rilavante se la 
	 * combinazione è verticale o orizzontale e di quanti elementi è comosta.
	 * 
	 * @return true se esiste almeno una combinazione di tre elementi
	 */
	boolean checkTris();
	
	/**
	 * Metodo che verifica la possibilità di completare almeno una mossa 
	 * col set di caramelle attuale.
	 * 
	 * @return true se è possibile realizzare almeno una combinazione in verticale e/o
	 * orizzontale in una sola mossa
	 */
	boolean checkNextMove();
	
	/**
	 * Metodo che modella il comportamento da utilizzare quando si attiva una caramella SPECIAL.
	 * Questo behaviour elimina tutte le caramelle di un certo colore (quello della caramella che è stata accoppiata
	 * con la SPECIAL) e per ognuna di esse attribuisce punti bonus.
	 * 
	 * @param c colore della caramella con cui è stata abbinata la SPECIAL
	 * @return il numero di caramelle dello stesso colore di quella passata come parametro,
	 * per calcore i punti bonus da attribuire
	 */
	int doFive(final int c);
	
	/**
	 * Metodo che viene invocato finchè sono in atto combinazioni sulla matrice di gioco, e ad ogni
	 * esecuzione ritorna il punteggio relativo all'ultima combinazione per incrementare lo score attuale.
	 * 
	 * @return il punteggio relativo all'ultima mossa eseguita dal loop
	 */
	int gameLoop();
	
	/**
	 * Metodo che invoca la funzionalità di shuffle della matrice di gioco.
	 */
	void shuffle();
	
}
