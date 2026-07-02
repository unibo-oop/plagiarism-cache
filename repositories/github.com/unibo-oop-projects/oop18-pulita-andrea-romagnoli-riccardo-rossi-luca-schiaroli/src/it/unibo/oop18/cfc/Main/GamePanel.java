// The GamePanel is the drawing canvas.
// It contains the game loop which
// keeps the game moving forward.
// This class is also the one that grabs key events.

package it.unibo.oop18.cfc.Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import it.unibo.oop18.cfc.Input.KeyInput;
import it.unibo.oop18.cfc.Manager.GameStateManager;
import it.unibo.oop18.cfc.Objects.Entity.Player;
import it.unibo.oop18.cfc.TileMap.Tile;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {
	
	// dimensions

	public static final int WIDTH = 1024; //larghezza della finestra
	public static final int HEIGHT = 512; //altezza della mappa
	public static final int HUDHEIGHT = 128; //altezza dei hud
	public static final int HEIGHT2 = HEIGHT + HUDHEIGHT; // altezza mappa + altezza hub sopra
	public static final int HEIGHT3 = HEIGHT2 + HUDHEIGHT; // altezza mappa + altezza hub sopra + altezza hub sotto
	
	public static final int TOP_BOUND_IN_PIXEL = HUDHEIGHT + Tile.SPRITE_SIZE;
	public static final int LEFT_BOUND_IN_PIXEL = Tile.SPRITE_SIZE;
	public static final int RIGHT_BOUND_IN_PIXEL = WIDTH - (2 * Tile.SPRITE_SIZE);
	public static final int DOWN_BOUND_IN_PIXEL = HEIGHT;


	public static final int SCALE = 1;
	
	// game loop stuff
	private Thread thread;
	private boolean running;
	public static final int FPS = 30;
	private final int TARGET_TIME = 1000 / FPS;
	
	// drawing stuff
	private BufferedImage image;
	private Graphics2D g;
	
	// game state manager
	private GameStateManager gsm;
	
	// constructor
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT3 * SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	// ready to display
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	// run new thread
	public void run() {
		
		init();
		
		long start;
		long elapsed;
		long wait;
		
		// game loop
		while(running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = TARGET_TIME - elapsed / 1000000;
			if(wait < 0) wait = TARGET_TIME;
			
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// initializes fields
	private void init() {
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT3, 1);
		g = (Graphics2D) image.getGraphics();
		gsm = new GameStateManager();
		//Add KeyInput listener to the panel
		super.addKeyListener(new KeyInput(gsm));
	}
	
	// updates game
	private void update() {
		gsm.update();
	}
	
	// draws game
	private void draw() {
		gsm.draw(g);
	}
	
	// copy buffer to screen
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT3 * SCALE, null);
		g2.dispose();
	}	
}
