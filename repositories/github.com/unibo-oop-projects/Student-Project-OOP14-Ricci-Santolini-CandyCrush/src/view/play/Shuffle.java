package view.play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import view.AbstractMenuPanel;
import view.utility.ViewUtility;
/**
 * Classe il cui compito è far apparire per qualche secondo il messaggio di shuffle degli elementi della matrice principale
 * di gioco quando non ci sono più mosse possibili.
 * 
 * @author Beatrice Ricci
 *
 */
public class Shuffle extends AbstractMenuPanel {

	private static final long serialVersionUID = -1183169779251815974L;
	
	private static final int TIME = 800;
	
	private final JLabel label = createLabel(" ", ViewUtility.SHUFFLE, JLabel.CENTER, JLabel.TOP);
	
	/**
	 * Metodo per far apparire e scomparire il  messaggio di shuffle in modo che sia chiaro all'utente.
	 * @throws InterruptedException 
	 */
	public Shuffle() {
		this.setTitle("Shuffle!!");
		Dimension size;
		size = Toolkit.getDefaultToolkit().getScreenSize();
		final int taglia = (int) size.getWidth() / 3;
		this.setSize(taglia, taglia / 2);
		
		final JPanel panel = new JPanel(new BorderLayout());
		panel.add(label, BorderLayout.CENTER);
		this.lookPanel(panel);
		this.getContentPane().add(panel);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Thread.sleep(TIME);
				} catch (InterruptedException e) {
					closePage();
				}
				closePage();
			}
		});
	}
	
	@Override
	public void lookPanel(final JPanel p) {
		p.setBackground(Color.WHITE);
	}
}
