package view;

import java.awt.Graphics;

/**
 * Interfaccia per la classe MyPanel che va a disegnare pannelli con immagini come sfondo.
 * @author Beatrice Ricci
 *
 */
public interface IMyPanel {
	/**
	 * Disegno l'immagine sul componente partendo dalle coordinate 0-0.
	 * @param g componente su cui disegnare l'immagine
	 */
	void paintComponent(final Graphics g);
}
