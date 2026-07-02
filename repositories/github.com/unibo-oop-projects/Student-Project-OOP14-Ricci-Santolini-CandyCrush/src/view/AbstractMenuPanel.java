package view;

import javax.swing.JPanel;
/**
 * Classe astratta per la gestione dell'aspetto dei JPanel. 
 * Dato che non tutti i menù del gioco necessitano di JButton, estendendo questa classe è possibile gestire 
 * unicamente le caratteristiche dei vari pannelli della schermata.
 * 
 * @author Beatrice Ricci
 *
 */
public abstract class AbstractMenuPanel extends AbstractMenu implements IMenuPanel {

	private static final long serialVersionUID = 1350003953896402903L;

	@Override
	public abstract void lookPanel(final JPanel p);

}
