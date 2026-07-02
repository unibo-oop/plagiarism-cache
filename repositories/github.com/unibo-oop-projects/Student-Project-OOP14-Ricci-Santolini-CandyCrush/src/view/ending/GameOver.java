package view.ending;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.utility.ViewUtility;

/**
 * Classe che comunica all'utente che ha perso la partita e gli permette di giocare nuovamente o di abbandonare il gioco.
 * 
 * @author Beatrice Ricci
 * 
 */
public class GameOver extends AbstractFinal {

	private static final long serialVersionUID = -7876870538465198338L;

	private final JLabel label = createLabel("  GAME OVER :(  ", ViewUtility.ICON_LOSE, JLabel.CENTER, JLabel.BOTTOM);
	
	/**
	 * Costruttore.
	 */
	public GameOver() {
		this.setTitle("    GAME LOST :(    ");
		
		this.setDimension();
		
		final JPanel panel = new JPanel(new BorderLayout());
		
		this.lookPanel(panel);
		
		panel.add(this.label, BorderLayout.CENTER);
		panel.add(setJButtonsInPanel(Color.YELLOW), BorderLayout.SOUTH);

		this.getContentPane().add(panel);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@Override
	public void lookPanel(final JPanel p) {
		p.setOpaque(true);
		p.setBackground(Color.YELLOW);
	}
	
	@Override
	public void lookButton(final JButton b) {
		b.setOpaque(true);
		b.setBackground(Color.YELLOW);
		b.setBorderPainted(false);
		b.setFont(new Font(TYPE_FONT, Font.BOLD, DIMENSION_FONT_20));
	}
}
