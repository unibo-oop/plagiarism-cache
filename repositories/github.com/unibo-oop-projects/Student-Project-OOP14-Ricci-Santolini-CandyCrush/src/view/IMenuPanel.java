package view;

import javax.swing.JPanel;
/**
 * Questa intefaccia va ad estendere quella principale per i menù, gestendo così la possibilità di utilizzare un metodo per
 * modificare l'aspetto dei vari JPanel contenuti nei menù di gioco.
 * 
 * @author Beatrice Ricci
 *
 */
public interface IMenuPanel extends IMenu {
	/**
	 * Metodo per cambiare l'aspetto di un JPanel.
	 * 
	 * @param p pannello da modificare
	 */
	void lookPanel(JPanel p);
}
