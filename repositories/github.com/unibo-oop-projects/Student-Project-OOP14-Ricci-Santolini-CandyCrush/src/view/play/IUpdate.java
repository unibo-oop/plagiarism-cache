package view.play;
/**
 * Interfaccia per la classe Update che contiene il metodo principale della classe.
 * @author Beatrice Ricci
 *
 */
public interface IUpdate {
	
	/**
	 * Metodo che aggiorna la matrice della view in base a quella del modello.
	 * @param color colore della caramella in posizione i-j
	 * @param type tipo della caramella in posizione i-j
	 * @param i coordinata i
	 * @param j coordinata j
	 */
	void draw(final int color, final int type, final int i, final int j);
	
	/**
	 * Metodo per aggiornare il totale delle mosse e del punteggio ottenuto.
	 * @param moves mosse da aggiornare
	 * @param score punti da aggiornare
	 */
	void updateScoreAndMoves(int moves, int score);
}
