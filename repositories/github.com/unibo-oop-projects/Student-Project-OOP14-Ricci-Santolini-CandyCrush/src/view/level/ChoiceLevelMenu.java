package view.level;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.AbstractMenuButton;
import view.MyPanel;
import view.utility.ViewUtility;
/**
 * Classe che disegna il menù in cui l'utente può scegliere il livello di difficoltà.
 * Una volta scelto verrà creata una nuova schermata di gioco con le caratteristiche del livello scelto.
 * 
 * @author Beatrice Ricci
 *
 */
public class ChoiceLevelMenu extends AbstractMenuButton {

	private static final long serialVersionUID = 2385788298938761843L;
	
	private static final int WIDTH_DIMENSION = 3;
	private static final int HEIGHT_DIMENSION = 5;
	private static final int DIMENSION_FONT_18 = 18;
	private static final String TYPE_FONT = "Arial";

	private EasyLevel easy = new EasyLevel();
	private MediumLevel medium = new MediumLevel();
	private DifficultLevel difficult = new DifficultLevel();
	
	private final JButton rb1 = new JButton("EASY");
	private final JButton rb2 = new JButton("MEDIUM");
	private final JButton rb3 = new JButton("DIFFICULT");
	
	/**
	 * Costruttore del menù di scelta della difficoltà di gioco.
	 */
	public ChoiceLevelMenu() {
		this.setTitle("Choose level");
		
		Dimension size;
		size = Toolkit.getDefaultToolkit().getScreenSize();
		final int dimension = (int) size.getWidth() / WIDTH_DIMENSION;
		this.setSize(dimension, dimension / HEIGHT_DIMENSION);
		this.setResizable(false);
		
		final JPanel panel = new MyPanel(ViewUtility.POIS);
		panel.setLayout(new FlowLayout());
		this.add(panel);
		
		this.lookButton(rb1);
		this.lookButton(rb2);
		this.lookButton(rb3);
		
		panel.add(rb1);
		panel.add(rb2);
		panel.add(rb3);
		
		this.rb1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				easy.setLevel();
				closePage();
			}
		});
		this.rb2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				medium.setLevel();
				closePage();
			}
		});
		this.rb3.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				difficult.setLevel();
				closePage();
			}
		});
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@Override
	public void lookButton(final JButton b) {
		b.setOpaque(true);
		b.setBackground(Color.WHITE);
		b.setBorderPainted(true);
		b.setFont(new Font(TYPE_FONT, Font.BOLD, DIMENSION_FONT_18));
	}
}
