package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.PlayerImpl;

/**
 * The Class GameOverView.
 */
public final class GameOverView extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	/** The title. */
	public static String TITLE ="GameOver";
	
	/** The Constant PROPORTION_M. */
	private static final int PROPORTION_M = 15;
	
	/** The Constant PROPORTION_T. */
	private static final int PROPORTION_T=30;
	
	/** The Constant ROWS. */
	private static final int ROWS = 5;
	
	/** The Constant COLS. */
	private static final int COLS = 6;
	
	/** The Constant FIRST_BUTTON. */
	private static final int FIRST_BUTTON=12;
	
	/** The Constant SECOND_BUTTOn. */
	private static final int SECOND_BUTTOn = 4;

    /** The back. */
    private final Background back = new Background(TITLE);
    
    /** The back to menu. */
    private final JButton backToMenu;
    
    /** The text. */
    private final JTextField text;
    
    /**
     * Instantiates a new game over view.
     *
     * @param player the that played this game
     * @param score stored score
     * @param view the view displayed on this screen
     */
    public GameOverView(PlayerImpl player,final int score,final View view) {
    	super();
    	this.backToMenu = new JButton("Back");
    	this.text = new JTextField("Score: "+ score);
    	this.backToMenu.addActionListener(e -> view.resetToMenu());
    	this.backToMenu.setBorder(BorderFactory.createEmptyBorder());
    	 this.backToMenu.setBackground(Color.YELLOW);
         this.backToMenu.setForeground(Color.WHITE);
         this.backToMenu.setOpaque(false);
         this.text.setBorder(BorderFactory.createEmptyBorder());
         this.text.setBackground(Color.YELLOW);
         this.text.setForeground(Color.RED);
         this.text.setOpaque(false);
         this.setLayout(new GridLayout(ROWS, COLS));
         IntStream.range(0, FIRST_BUTTON).forEach(i -> this.add(Box.createRigidArea(new Dimension(0, 0))));
         this.add(this.text);
         IntStream.range(0, SECOND_BUTTOn).forEach(i -> this.add(Box.createRigidArea(new Dimension(0, 0))));
         this.add(backToMenu);
         IntStream.range(0, FIRST_BUTTON).forEach(i -> this.add(Box.createRigidArea(new Dimension(0, 0))));
         this.repaint();
    }

	/**
	 * Paint component.
	 *
	 * @param g the g
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
  		this.backToMenu.setFont(new Font("Bauhaus 93",2,this.getHeight() / PROPORTION_M));
        this.text.setFont(new Font("Bauhaus 93", 2, this.getHeight() / PROPORTION_T));
        g.drawImage(back.loadImage(),0, 0, this.getWidth(), this.getHeight(), this);
	}


    
    
    
}
