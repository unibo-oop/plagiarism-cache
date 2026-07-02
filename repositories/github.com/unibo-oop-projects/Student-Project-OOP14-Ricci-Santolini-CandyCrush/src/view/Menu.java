package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.level.ChoiceLevelMenu;
import view.utility.ViewUtility;

/**
 * Classe che contiene il menù iniziale.
 * E' possibile scegliere di iniziare subito a giocare, di leggere le istruzioni di gioco o di chiudere l'applicazione.
 * 
 * @author Beatrice Ricci
 *
 */
public class Menu extends AbstractMenuButton {

	private static final long serialVersionUID = -8627423879053598019L;
	
	private static final int WIDTH_DIMENSION = 2;
	private static final int HEIGHT_DIMENSION = 10;
	
	private final JButton start = new JButton();
	private final JButton instructions = new JButton();
	private final JButton exit = new JButton();
	
	/**
	 * Costruttore.
	 */
	public Menu() {
		this.setTitle("W E L C O M E");
		
		Dimension size;
		size = Toolkit.getDefaultToolkit().getScreenSize();
		final int dimension = (int) size.getWidth() / WIDTH_DIMENSION;
		this.setSize(dimension, dimension - ((int) size.getHeight() / HEIGHT_DIMENSION));
		this.setResizable(false);
		
		final JPanel panel = new MyPanel(ViewUtility.CARAMELLE);
		panel.setLayout(new FlowLayout());
		
		this.start.setIcon(ViewUtility.IC_START);
		this.lookButton(this.start);
		
		this.instructions.setIcon(ViewUtility.IC_INSTR);
		this.lookButton(this.instructions);
		
		this.exit.setIcon(ViewUtility.IC_EXIT);
		this.lookButton(this.exit);
		
		this.start.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				new ChoiceLevelMenu();
				closePage();
			}
		});
		
		this.instructions.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				new Instructions();
			}
		});
		
		this.exit.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				closeGame();
			}
		});
		
		panel.add(this.start);
		panel.add(this.instructions);
		panel.add(this.exit);
		
		this.getContentPane().add(panel);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@Override
	public void lookButton(final JButton b) {
		b.setOpaque(true);
		b.setBackground(Color.PINK);
		b.setBorderPainted(false);
	}
}
