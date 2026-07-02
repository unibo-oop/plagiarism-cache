package view;

import javax.swing.JOptionPane;
/**
 * Classe che si compone di un JOptionPane per fare in modo che l'utente possa riflettere più attentamente sulla decisione
 * di chiudere o meno l'applicazione.
 * 
 * @author Beatrice Ricci
 *
 */
public class Question extends AbstractMenu {

	private static final long serialVersionUID = 8197909384969100034L;
	
	private final Object[] options = {"Yes", "No"};
	
	/**
	 * Costruttore.
	 */
	public Question() {
		final int n = JOptionPane.showOptionDialog(this,
		    "Are you sure ?!",
		    "     !!!!     ",
		    JOptionPane.YES_NO_OPTION,
		    JOptionPane.QUESTION_MESSAGE,
		    null,
		    options,
		    options[1]);
		
		if (n == JOptionPane.YES_OPTION) {
			this.closeGame();
		} else {
			this.closePage();
		}
	}
}
