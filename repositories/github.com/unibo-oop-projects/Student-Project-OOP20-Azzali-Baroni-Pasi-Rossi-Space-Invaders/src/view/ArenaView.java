package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import controller.Input;
import model.Entity;
import model.GameImpl;
import model.PlayerImpl;
import utility.Pair;

/**
 * The Class ArenaView.
 */
public class ArenaView extends JPanel {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant TITLE. */
	public static final String TITLE ="Game";
	
	/** The playerview. */
	private final headUpDisplay playerview;
	
	/** The back. */
	private final Background back;
    
    /** The entityview. */
    private final EntityView entityview;
	
	/** The Entity game. */
	private final List<Pair<Entity, Image>> EntityGame;
	
	/** The width proportion. */
	private double widthProportion;
	
	/** The height proportion. */
	private double heightProportion;
	
	/**
	 * Instantiates a new arena view.
	 *
	 * @param input the input
	 */
	public ArenaView(final Input input){
		super();
		this.setLayout(new BorderLayout());
	    this.entityview = new EntityView();
		this.back = new Background(TITLE);
		this.EntityGame = Collections.synchronizedList(new LinkedList<>());
		this.setFocusable(true);
		this.setDoubleBuffered(true);
		this.playerview = new playerView();
		this.setPreferredSize(new Dimension(ViewImpl.SCREEN_WIDTH,ViewImpl.SCREEN_HEIGHT));
		this.add(this.playerview,BorderLayout.NORTH);
		this.addKeyListener(input);
		if (!this.hasFocus()) {
            this.requestFocusInWindow();
        }
		
	}

	/**
	 * Paint component.
	 *
	 * @param g the g
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(back.loadImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		this.EntityGame.forEach(e->{
			g.drawImage(e.getY(),
					(int) (e.getX().getHitbox().getMinX() * this.widthProportion),
					(int) (e.getX().getHitbox().getMinY() * this.heightProportion),
					(int) (e.getX().getHitbox().getWidth() * this.widthProportion),
					(int) (e.getX().getHitbox().getHeight() * this.heightProportion),this);
		});
	}
	
	/**
	 * Render.
	 *
	 * @param gameEntity the game entity
	 * @param level the level
	 * @param score the score
	 */
	public void render(final List<Entity> gameEntity,final int level,final int score) {
		if (!this.hasFocus()) {
            this.requestFocusInWindow();
        }
		this.EntityGame.clear();
		widthProportion = (double) this.getWidth() /(double) GameImpl.ARENA_WIDTH;
		heightProportion = (double) this.getHeight() /(double) GameImpl.ARENA_HEIGHT;
	    gameEntity.stream().filter(e -> e.getHitbox() != null).map(e -> new Pair<>(e, entityview.loadImage(e)))
	         .forEach(p -> this.EntityGame.add(p));
		this.repaint();
		
		final PlayerImpl player = (PlayerImpl) gameEntity.get(0);
		playerview.render(player, score, level);
	         
	}
	
	

}
