package view;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * Classe astratta per gestire l'aspetto dei JButtone dei JPanel. 
 * Dato che alcune classi del programma hanno necessità di gestire l'aspetto sia di JButton che di JPanel
 * questa classe permette di implementare in ogni altra classe che ne necessita, i metodi a seconda dell'aspetto desiderato.
 * 
 * @author Beatrice Ricci
 *
 */
public abstract class AbstractMenuPanelAndButton extends AbstractMenu implements IMenuPanel, IMenuButton {
	
	private static final long serialVersionUID = 917878188586609756L;

	@Override
	public abstract void lookPanel(final JPanel p);

	@Override
	public abstract void lookButton(final JButton b);

}
