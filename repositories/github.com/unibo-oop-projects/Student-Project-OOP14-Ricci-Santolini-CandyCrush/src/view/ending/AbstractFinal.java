package view.ending;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.AbstractMenuPanelAndButton;
import view.level.ChoiceLevelMenu;
import view.Question;

/**
 * Classe astratta che contiene l'implementazione dei metodi che si occupano della creazione dei frame 
 * che gestiscono i messaggi per la terminazione del gioco (vittoria, sconfitta).
 * 
 * @author Beatrice Ricci
 */

public abstract class AbstractFinal extends AbstractMenuPanelAndButton implements IFinal {

	private static final long serialVersionUID = 6998965214419663725L;
	
	private static final int WIDTH_DIMENSION = 2;
	private static final int HEIGHT_DIMENSION = 10;
	
	//queste due costanti devono essere viste anche dalla classi che estendono
	/**
	 * Costante per impostare la dimensione del testo dei JButton a 20.
	 */
	protected static final int DIMENSION_FONT_20 = 20;
	/**
	 * Costante per impostare il testo dei JButton.
	 */
	protected static final String TYPE_FONT = "Arial";
	
	@Override
	public final void setDimension() {
		Dimension size;
		size = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int) size.getWidth() / WIDTH_DIMENSION, (int) size.getHeight() - (int) size.getHeight() / HEIGHT_DIMENSION);
		this.setResizable(false);
	}
	
	@Override
	public final JPanel setJButtonsInPanel(final Color c) {
		final JButton play = new JButton(" Play again ! ");
		play.setFont(new Font(TYPE_FONT, Font.BOLD, DIMENSION_FONT_20));
		
		final JButton exit = new JButton(" EXIT ");
		exit.setFont(new Font(TYPE_FONT, Font.BOLD, DIMENSION_FONT_20));
		
		this.lookButton(play);
		this.lookButton(exit);

		this.setJButtonBehaviour(play, exit);
		
		final JPanel app = new JPanel(new FlowLayout());
		this.lookPanel(app);
		
		app.add(play);
		app.add(exit);
		
		return app;
	}
	
	/**
	 * Metodo privato per gestire il comportamento dei JButton una volta premuti.
	 * 
	 * @param p JButton play again
	 * @param e JButton exit
	 */
	private void setJButtonBehaviour(final JButton p, final JButton e) {
		p.addActionListener(new ActionListener() {
			
			public void actionPerformed(final ActionEvent e) {
				closePage();
				new ChoiceLevelMenu();
			}
		});
		e.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				new Question();
			}
		});
	}
}
