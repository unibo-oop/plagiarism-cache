package view.play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import view.AbstractMenuPanel;
import view.Question;
import view.utility.ViewUtility;

/**
 * Questa classe ha lo scopo di disegnare la configurazione iniziale dei vari pannelli che vanno a formare la schermata di 
 * gioco, con tutti i loro elementi.
 * 
 * @author Beatrice Ricci
 *
 */
public class SetPlayPanels extends AbstractMenuPanel {
	
	private static final long serialVersionUID = -3912612789521119749L;
	
	/**
	 * Caratteristiche delle JLabel.
	 */
	private static final int DIMENSION_FONT_20 = 20;
	private static final int DIMENSION_FONT_24 = 24;
	private static final int DIMENSION_FONT_18 = 18;
	private static final String TYPE_FONT = "Arial";
	
	private final Border border = LineBorder.createBlackLineBorder();
	
	/**
	 * Metodo per aggiungere tutti i pannelli "minori", nella parte centrale viene inserita la matrice di gioco.
	 * @param panel pannello principale in cui aggiungere tutti i pannelli creati in questa classe
	 * @param matrixPanel pannello contenente la matrice di gioco
	 * @param l JLabel in cui verrà indicato il livello di difficoltà a cui si sta giocando
	 * @param s JLabel in cui verrà indicato il numero di mosse iniziali
	 * @param t JLabel in cui verrà indicato l'obiettivo da raggiungere per vincere il livello
	 * @param tot JLabel in cui verrà indicato il totale punti ottenuti (inizialmente 0)
	 */
	public void updatePanel(final JPanel panel, final JPanel matrixPanel, final JLabel l, final JLabel s, final JLabel t, final JLabel tot) {
		panel.add(matrixPanel, BorderLayout.CENTER);
		panel.add(this.setNorthPanel(l), BorderLayout.NORTH);
		panel.add(this.setWestPanel(s, t, tot), BorderLayout.WEST);
		panel.add(this.setSouthPanel(), BorderLayout.SOUTH);
		panel.add(this.setEastPanel(), BorderLayout.EAST);
	}
	
	/**
	 * Metodo per disegnare il pannello "nord".
	 */
	private JPanel setNorthPanel(final JLabel level) {
		final JPanel pNorth = new JPanel(new BorderLayout());
		level.setFont(new Font(TYPE_FONT, Font.BOLD, DIMENSION_FONT_18));
		level.setForeground(Color.MAGENTA);
		level.setOpaque(true);
		level.setBackground(Color.WHITE);
		level.setBorder(border);
		
		pNorth.add(level, BorderLayout.EAST);
		this.lookPanel(pNorth);
		return pNorth;
	}
	
	/**
	 * Metodo per disegnare il pannello a sinistra
	 */
	private JPanel setWestPanel(final JLabel step, final JLabel target, final JLabel tot) {
		final JPanel pWest = new JPanel(new BorderLayout());
		final JPanel internalWestPanel = new JPanel(new BorderLayout());
		step.setFont(new Font(TYPE_FONT, Font.BOLD, DIMENSION_FONT_24));
		
		target.setFont(new Font(TYPE_FONT, Font.BOLD, DIMENSION_FONT_20));
		target.setForeground(Color.MAGENTA);
		target.setOpaque(true);
		target.setBackground(Color.WHITE);
		target.setBorder(border);
		
		tot.setFont(new Font(TYPE_FONT, Font.BOLD, DIMENSION_FONT_20));
		
		internalWestPanel.add(target, BorderLayout.NORTH);
		internalWestPanel.add(tot, BorderLayout.CENTER);
		
		pWest.add(step, BorderLayout.NORTH);
		pWest.add(internalWestPanel,  BorderLayout.CENTER);
		
		this.lookPanel(pWest);
		this.lookPanel(internalWestPanel);
		return pWest;
	}
	
	/**
	 * Metodo per disegnare il pannello a sud.
	 */
	private JPanel setSouthPanel() {
		final JPanel pSouth = new JPanel(new BorderLayout());
		final JButton close = new JButton();
		close.setIcon(ViewUtility.CLOSE_IM);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				new Question();
			}
		});
		pSouth.add(close, BorderLayout.WEST);
		this.lookPanel(pSouth);
		return pSouth;
	}
	/**
	 * Metodo per disegnare il pannello ad est.
	 */
	private JPanel setEastPanel() {
		final JPanel pEast = new JPanel(new BorderLayout());
		final JLabel gap = new JLabel("     ");
		pEast.add(gap);
		this.lookPanel(pEast);
		return pEast;
	}
	
	@Override
	public void lookPanel(final JPanel p) {
		p.setOpaque(true);
		p.setBackground(Color.CYAN);
	}
}
