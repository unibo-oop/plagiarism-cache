package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Interfaccia che contiene tutti i metodi comuni ai vari menù/schermate di gioco.
 * 
 * @author Beatrice Ricci
 *
 */
public interface IMenu {
	/**
	 * Metodo per terminare l'applicazione.
	 */
	void closeGame();
	
	/**
	 * Medoto per chiudere una schermata quando non serve più.
	 */
	void closePage();
		
	/**
	 * Metodo per inserire un'immagine in una JLabel.
	 * 
	 * @param text testo da inserire nella JLabel 
	 * @param icon immagine da inserire nella JLabel
	 * @param hPos allineamento orizzontale dell'immagine 
	 * @param vPos allineamento verticale dell'immagine
	 * @return Jlabel con immagine e testo
	 */
	JLabel createLabel(String text, ImageIcon icon, int hPos, int vPos);
}
