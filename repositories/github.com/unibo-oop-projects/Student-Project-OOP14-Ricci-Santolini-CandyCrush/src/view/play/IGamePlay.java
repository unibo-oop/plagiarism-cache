package view.play;

import view.IMenu;

/**
 * Intefaccia che contiene i metodi della schermata di gioco.
 * 
 * @author Beatrice Ricci
 *
 */
public interface IGamePlay extends IMenu {
	/**
	 * Metodo che permette alla schermata di gioco di aggiornarci grazie alla classe Update.
	 * @param moves numero mosse da aggiornare
	 * @param score punteggio da aggiornare
	 */
	void update(final int moves, final int score);
	/**
	 * Setter dell'obiettivo da raggiungere per superare il livello.
	 * @param n obiettivo da raggiungere
	 */
	void updateTarget(int n);
	
	/**
	 * Setter della difficoltà del livello.
	 * @param s tipologia di difficoltà del livello
	 */
	void setDiff(String s);

	/**
	 * Metodo per aggiornare le mosse rimaste in base al modello.
	 * @param n numero di mosse da aggiornare
	 */
	void updateMoves(int n);
	
	/**
	 * Metodo per aggiornare il punteggio ottunuto in base ai dati del modello.
	 * @param n punteggio da aggiornare
	 */
	void updateScore(int n);
	
	/**
	 * Metodo che restituisce il Butt desiderato in base alle coordinate passate come parametro.
	 * @param i coordinata x
	 * @param j coordinata y
	 * @return Butt di coordinate x, y
	 */
	Butt getAMatrixButt(int i, int j);
	
	/**
	 * Metodo con cui la view dice all'update di aggiornare la matrice di gioco in base ai dati passati
	 * dal controller.
	 * @param color colore della caramella in posizione i-j
	 * @param type tipo della caramella in posizione i-j
	 * @param i coordinata i
	 * @param j coordinata j
	 */
	void draw(final int color, final int type, final int i, final int j);
}
