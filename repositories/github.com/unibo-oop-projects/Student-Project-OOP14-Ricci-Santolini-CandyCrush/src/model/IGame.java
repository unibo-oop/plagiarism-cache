package model;

/**
 * Interfaccia di "facciata", che espone un numero ridotto di funzionalità per semplificare l'utilizzo da parte del client.
 * Si tratta principalemente di funzonalità richieste dal Board.
 * 
 * @author Nicola Santolini
 */
public interface IGame {
	
	/**
	 * Metodo che semplifica e "nasconde" le varie operazioni necessarie al loop di gioco.
	 * Al suo interno vengono gestiti le operzioni di controllo sulla matrice, applicazione dell'adeguato
	 * behaviour e assegnazione del punteggio corrispondente.
	 * 
	 * @param mat matrice degli elementi
	 * @return il punteggio relativo all'ultima mossa eseguita dal loop
	 */
	int gameLoop(final ICandy[][] mat);
	
	/**
	 * Metodo che verifica se esiste almeno una combinazione di tre elementi. In questo caso non è rilavante se la 
	 * combinazione è verticale o orizzontale e di quanti elementi è composta.
	 * 
	 * @param mat matrice degli elementi
	 * @return true se esiste almeno una combinazione di tre elementi
	 */
	boolean checkTris(final ICandy[][] mat);
	
	/**
	 * Metodo che verifica la possibilità, con una mossa, di generare almeno un tris con l'attuale matrice di gioco,
	 * e quindi di poter continuare a giocare senza effetuare mescolamenti della matrice.
	 *
	 * @param mat matrice degli elementi
	 * @return true se è possibile una nuova mossa
	 */
	boolean checkNextMove(final ICandy[][] mat);

	/**
	 * Metodo che modella il comportamento da utilizzare quando si attiva una caramella SPECIAL.
	 * 
	 * @param mat matrice degli elementi
	 * @param c colore della caramella con cui è stata abbinata la SPECIAL
	 * @return il numero di caramelle dello stesso colore di quella passata come parametro,
	 * per calcore i punti bonus da attribuire
	 */
	int doFive(final ICandy[][] mat, final int c);
	
}
