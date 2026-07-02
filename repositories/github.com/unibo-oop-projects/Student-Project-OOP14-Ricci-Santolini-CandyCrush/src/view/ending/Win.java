package view.ending;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.utility.ViewUtility;

/**
 * Classe che comunica all'utente che ha vinto la partita e gli permette di giocare nuovamente o di abbandonare il gioco.
 * 
 * @author Beatrice Ricci
 */
public class Win extends AbstractFinal {

	private static final long serialVersionUID = 5227740951252505323L;
	
	private final JLabel label = createLabel("  YOU WIN !!!  ", ViewUtility.ICON_WIN, JLabel.CENTER, JLabel.BOTTOM);
	/**
	 * Costruttore.
	 */
	public Win() {
		this.setTitle("    WIN !!    ");
		
		this.setDimension();
		
		final JPanel panel = new JPanel(new BorderLayout());
		this.lookPanel(panel);
		panel.add(label, BorderLayout.CENTER);
		panel.add(setJButtonsInPanel(Color.cyan), BorderLayout.SOUTH);
		
		this.getContentPane().add(panel);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	@Override
	public void lookPanel(final JPanel p) {
		p.setOpaque(true);
		p.setBackground(Color.CYAN);
	}
	
	@Override
	public void lookButton(final JButton b) {
		b.setOpaque(true);
		b.setBackground(Color.CYAN);
		b.setBorderPainted(false);
		b.setFont(new Font(TYPE_FONT, Font.BOLD, DIMENSION_FONT_20));
	}
}

