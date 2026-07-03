/**
 * This class is the main class of the game. It creates the canvas on which the whole game will be displayed.
 * @author Francesco Gori
 * @version 1.0
 */

package tq2.implementations;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFrame;

import tq2.interfaces.*;

/**
 * The GameImpl class implements the interface Game. It creates the window and the canvas on which the contents of
 * the application will be displayed.
 * 
 * @author Francesco Gori
 */

public class GameImpl extends Canvas implements Runnable, Game {
	
	/** Serializable. */
	private static final long serialVersionUID = 1L;

	/** The preferred width of the frame. */
	protected static final Integer WIDTH = 320;
	
	/** The preferred height of the frame. */
	protected static final Integer HEIGHT = 240;
	
	/** The scale factor, by which all graphics will be multiplied. */
	protected static final Integer SCALE = 2;
	
	/** The title displayed on the top of the frame. */
	protected static final String TITLE = "Toa's Quest 2";
	
	/** The frame that displays the game. */
	public JFrame frame;
	
	/** The thread. */
	protected Thread thread;
	
	/** Whether the game is running. */
	protected boolean running = false;
	
	/** The Handler. */
	public Handler handler;
	
	/** The list of the name of level classes. */
	public LinkedList<String> levelList;
	
	/** The current level. */
	protected Level currentLevel;
	
	/** The index of the current level. */
	protected Integer levelIndex;
	
	/** The camera. */
	protected Camera camera;
	
	/** Whether the game is loading a level. */
	public Boolean loading = true;

	
	/**
	 * This instantiates a Game object.
	 *
	 * @param levelList the list of names of the level classes to load
	 * @param frame the frame
	 */
			
	public GameImpl (LinkedList<String> levelList, JFrame frame) {
		
		//the preferred size of the window is set to be WIDTH x SCALE
		Dimension size = new Dimension( (WIDTH * SCALE), (HEIGHT * SCALE));
		this.setMinimumSize(size);
		
		this.levelIndex = 0;
		this.handler = new HandlerImpl(this);
		
		this.levelList = levelList;
		this.frame = frame;
		
		this.camera = new CameraImpl(this);
	}
	
	/**
	 * Initializes the Game loading the level that matches the index "currentLevel".
	 */
	protected void init() {
		this.loading = true;
		
		//if there's a level already loaded, clear it and remove its listeners
		if (this.currentLevel != null) {
			this.removeKeyListener(currentLevel.getControls());
			this.removeMouseListener(currentLevel.getControls());
			this.currentLevel.clearLevel();
		}
		
		try {
			//if there's a level corresponding to the specified index
			if (levelList.size()>levelIndex) {
				
				//the level is created starting from its name
				this.currentLevel = ((Level) Class.forName(this.levelList.get(this.levelIndex)).getConstructor(Handler.class).newInstance(this.handler));

				//If the level features a custom cursor, it will be added. By default this won't affect the default cursor.
			    frame.setCursor(this.currentLevel.getCursor());
			    
				this.currentLevel.loadLevel();
				
				addKeyListener (this.currentLevel.getControls());
				addMouseListener (this.currentLevel.getControls());
			}
			
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException
				| ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("An error occoured while loading level " + levelIndex);

		}
		
		this.loading = false;
		
	}
	
	/**
	 * Starts the game thread.
	 */
	protected synchronized void start() {
		if (!this.running) {
			this.running = true;
			this.thread = new Thread(this, "Thread");
			this.thread.start();
			System.out.println("");
		}
	}
	
	/**
	 * Stops the game thread.
	 */
	protected synchronized void stop() {
		if (this.running) {
			this.running = false;
			try {
				this.thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		init();

		requestFocus();
		Long lastTime = System.nanoTime();
		Long timer = System.currentTimeMillis();
		Double delta = 0.0;
		Double ns = 1000000000.0/60.0;
		Integer frames = 0;
		Integer ticks = 0;
		
		while (this.running) {
			
			long now = System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime = now;
			
			while (delta>=1) {
				
				if (!this.loading) {
				tick();
				ticks++;

				if (ticks%3==0){
				render();
				}
				frames++;
				}
				
				delta--;
			}
				
			if (System.currentTimeMillis()-timer>1000) {
				timer+=1000;
//				System.out.println(frames + " frames per second\n" + ticks + " updates per second\n");
				frames = 0;
				ticks = 0;
			}
		}
		
		this.stop();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Component#getWidth()
	 */
	@Override
	public int getGameWidth() {
		return new Double((super.getWidth())/this.getScale()).intValue();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Component#getHeight()
	 */
	@Override
	public int getGameHeight() {
		return new Double((super.getHeight())/this.getScale()).intValue();
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Game#getScale()
	 */
	@Override
	public int getScale() {
		return SCALE;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Game#getWindowBounds()
	 */
	@Override
	public Rectangle getWindowBounds(LevelLayer layer) {
		
		return new Rectangle (new Double(this.getCamera().getX()*layer.getParallaxX()).intValue(), new Double(this.getCamera().getY()*layer.getParallaxY()).intValue(), this.getGameWidth(), this.getGameHeight());

	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Game#getCamera()
	 */
	@Override
	public Camera getCamera() {
		return this.camera;
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Game#getCurrentLevel()
	 */
	@Override
	public Level getCurrentLevel() {
		return currentLevel;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Game#getCurrentLevelIndex()
	 */
	@Override
	public Integer getCurrentLevelIndex() {
		return this.levelIndex;
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Game#render()
	 */
	@Override
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.scale(this.getScale(), this.getScale());
		
		g.setRenderingHint(
				RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

		g.setBackground(Color.DARK_GRAY); 
		if(this.running) this.getHandler().render(g);
		
		g.dispose();
		bs.show();
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Game#tick()
	 */
	@Override
	public void tick() {
		this.currentLevel.tick();
		this.getHandler().tick();
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Game#nextLevel()
	 */
	@Override
	public void nextLevel() {
		if (levelList.size() > levelIndex) {
			this.levelIndex++;
			this.jumpToLevel(levelIndex);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Game#jumpToLevel(java.lang.Integer)
	 */
	@Override
	public void jumpToLevel(Integer levelIndex) {
		if (levelIndex < levelList.size() && levelIndex > 0) {
			this.levelIndex = levelIndex;
			this.init();
		}
	}
	
	/**
	 * This is the main of the application. It creates a frame and adds the Game component to it,
	 * then it starts the game loop.
	 *
	 * @param args the arguments
	 */
	
	public static void main(String[] args) {
		
		LinkedList<String> levelList = new LinkedList<>();

		InputStream	is = GameImpl.class.getResourceAsStream("/levelList.txt");
		Scanner s = new Scanner(is);
		
		while(s.hasNextLine()){
			levelList.add(s.nextLine());
		}
		s.close();
		
		//Create the frame
		JFrame frame = new JFrame(TITLE);
		frame.setBackground(Color.BLACK);
		GameImpl game = new GameImpl(levelList, frame);
	    
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(game, BorderLayout.CENTER);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		frame.setVisible(true);
		
		game.start();
	}

	/* (non-Javadoc)
	 * @see com.tq2.interfaces.Game#setSize(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void setSize(Integer width, Integer height) {
		this.getWindowFrame().setSize(width*SCALE, height*SCALE);
		this.getWindowFrame().setLocationRelativeTo(null);		
	}
	
	/**
	 * Returns the window frame.
	 *
	 * @return the window frame
	 */
	protected JFrame getWindowFrame() {
		return this.frame;
	}

	/* (non-Javadoc)
 * @see com.tq2.interfaces.Game#getHandler()
 */
@Override
	public Handler getHandler() {
		return this.handler;
	}
}
