package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import model.PlayerImpl;

/**
 * The Class playerView print life, score and player level on screen
 */
public class playerView extends headUpDisplay{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The life bar. */
	private final JProgressBar lifeBar;
	
	/** The score. */
	private final JTextField score;
	
	/** The level. */
	private final JTextField level;
	
	/** The scoretxt. */
	final JTextField scoretxt = new JTextField(10);
	
	/** The leveltxt. */
	final JTextField leveltxt = new JTextField(10);
	
	/**
	 * Instantiates a new player view.
	 */
	public playerView() {
		super();
		final GroupLayout g = new GroupLayout(this);
		this.setLayout(g);
		this.setOpaque(false);
		this.lifeBar = new JProgressBar(0,100);
		this.lifeBar.setValue(100);
		this.lifeBar.setBackground(Color.BLACK);
		this.lifeBar.setForeground(Color.YELLOW);
		scoretxt.setFont(new Font("Bauhaus 93", 2, ViewImpl.SCREEN_HEIGHT / headUpDisplay.FONT_P));
		leveltxt.setFont(new Font("Bauhaus 93", 2, ViewImpl.SCREEN_HEIGHT / headUpDisplay.FONT_P));
		scoretxt.setEditable(false);
		leveltxt.setEditable(false);
		scoretxt.setBorder(BorderFactory.createEmptyBorder());
		leveltxt.setBorder(BorderFactory.createEmptyBorder());
		scoretxt.setOpaque(false);
		leveltxt.setOpaque(false);
		scoretxt.setForeground(Color.WHITE);
		leveltxt.setForeground(Color.WHITE);
		scoretxt.setHorizontalAlignment(SwingConstants.CENTER);
		leveltxt.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.score = scoretxt;
		this.level = leveltxt;
		g.setAutoCreateGaps(true);
		g.setAutoCreateContainerGaps(true);
		
		g.setHorizontalGroup(g.createSequentialGroup()
				.addContainerGap()
				.addComponent(this.lifeBar, 0, GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
				.addGroup(g.createParallelGroup(Alignment.TRAILING))
				.addComponent(this.score, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
		        .addComponent(this.level, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
		        );
		
		g.setVerticalGroup(g.createSequentialGroup()
				.addGroup(g.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lifeBar, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(this.score, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(this.level, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
				);		
	}

	/**
	 * Paint component.
	 *
	 * @param g the g
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.score.setFont(new Font("Bauhaus 93", 2, this.getParent().getHeight() / headUpDisplay.FONT_P));
	    this.level.setFont(new Font("Bauhaus 93", 2, this.getParent().getHeight() / headUpDisplay.FONT_P));
	}

	/**
	 * Render.
	 *
	 * @param player the player
	 * @param score the score
	 * @param level the level
	 */
	@Override
	public void render(PlayerImpl player, int score,int level) {
		this.level.setText("Level: "+ score);
		this.score.setText("score: "+ level);
		if(player.getShield() > 0) {
			this.lifeBar.setValue(player.getShield());
			this.lifeBar.setForeground(Color.ORANGE);
		}else {
			this.lifeBar.setValue(player.getHealth());
			this.lifeBar.setForeground(new Color((headUpDisplay.MAX_RGB * (100 - this.lifeBar.getValue()))/100,headUpDisplay.MAX_RGB * this.lifeBar.getValue()/100,0));
		}
		
	}

	
}
