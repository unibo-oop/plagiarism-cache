package model;

/**
 * Interfaccia per la gestione dei controlli sulle combinazioni nella matrice di gioco.
 * 
 * @author Nicola Santolini
 *
 */
public interface IChecks {
	
	/**
	 * Metodo che verifica se due caramelle sono adiacenti tramite le loro coordinate.
	 * 
	 * @param x1 indice di riga della prima caramella
	 * @param y1 indice di colonna della prima caramelle
	 * @param x2 indice di riga della seconda caramella
	 * @param y2 indice di colonna della seconda caramella
	 * @return true se le due caramelle sono adiacenti
	 */
	boolean checkExchange(final int x1, final int y1, final int x2, final int y2);
	
	/**
	 * Metodo che verifica la presenza di un tris verticale o orizzontale.
	 * 
	 * @param mat matrice degli elementi
	 * @return true se è presente almeno un tris
	 */
	boolean checkTris(final ICandy[][] mat);
	
	/**
	 * Metodo che verifica la presenza di tris verticali.
	 * 
	 * @param mat matrice degli elementi
	 * @return true se è presente almeno un tris verticale
	 */
	boolean checkTrisVertical(final ICandy[][] mat);
	
	/**
	 * Metodo che verifica la presenza di tris orizzontali.
	 * 
	 * @param mat matrice degli elementi
	 * @return true se è presente almeno un tris orizzontale
	 */
	boolean checkTrisHorizontal(final ICandy[][] mat);
	
	/**
	 * Metodo che verifica la presenza di una combinazione da quattro verticale o orizzontale.
	 * 
	 * @param mat matrice degli elementi
	 * @return true se è presente almeno una combinazione da quattro
	 */
	boolean checkPoker(final ICandy[][] mat);
	
	/**
	 * Metodo che verifica la presenza di combinazioni di quattro caramelle in verticale.
	 * 
	 * @param mat matrice degli elementi
	 * @return true se è presente almeno una combinazione di quattro caramelle in verticale
	 */
	boolean checkPokerVertical(final ICandy[][] mat);
	
	/**
	 * Metodo che verifica la presenza di combinazioni di quattro caramelle in orizzontale.
	 * 
	 * @param mat matrice degli elementi
	 * @return true se è presente almeno una combinazione di quattro caramelle in orizzontale
	 */
	boolean checkPokerHorizontal(final ICandy[][] mat);
	
	/**
	 * Metodo che verifica la presenza di una combinazione da cinque verticale o orizzontale.
	 * 
	 * @param mat matrice degli elementi
	 * @return true se è presente almeno una combinazione da cinque
	 */
	boolean checkFive(final ICandy[][] mat);
	
	/**
	 * Metodo che verifica la presenza di combinazioni di cinque caramelle in verticale.
	 * 
	 * @param mat matrice degli elementi
	 * @return true se è presente almeno una combinazione di cinque caramelle in verticale
	 */
	boolean checkFiveHorizontal(final ICandy[][] mat);
	
	/**
	 * Metodo che verifica la presenza di combinazioni di cinque caramelle in orizzontale.
	 * 
	 * @param mat matrice degli elementi
	 * @return true se è presente almeno una combinazione di cinque caramelle in orizzontale
	 */
	boolean checkFiveVertical(final ICandy[][] mat);
	
	/**
	 * Metodo che verifica la presenza di combinazioni di due tris con una caramella
	 * in comune.
	 * 
	 * @param mat matrice degli elementi
	 * @return true se è presente almeno una combinazione di due tris con una
	 * caramella in comune
	 */
	boolean checkWrapped(final ICandy[][] mat);
	
	/**
	 * Metodo che verifica la possibilità, con una mossa, di generare almeno un tris con l'attuale matrice di gioco,
	 * e quindi di poter continuare a giocare senza effetuare mescolamenti della matrice.
	 * @param mat matrice degli elementi
	 * @return true se è possibile una nuova mossa
	 */
	boolean checkNextMove(final ICandy[][] mat);
}
