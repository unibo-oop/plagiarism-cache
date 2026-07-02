package view;

import javax.swing.JButton;

/**
 * Intefaccia per la gestione dell'aspetto dei JButton contenuti nei vari menù del gioco.
 * 
 * @author Beatrice Ricci
 *
 */
public interface IMenuButton extends IMenu {
	/**
	 * Metodo per cambiare le caratteristiche principali dell'aspetto di un JButton.
	 * 
	 * @param b JButton da modificare
	 */
	void lookButton(JButton b);
}
