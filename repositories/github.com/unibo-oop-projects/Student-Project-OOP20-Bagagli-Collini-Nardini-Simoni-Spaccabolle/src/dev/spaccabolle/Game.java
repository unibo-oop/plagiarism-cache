package dev.spaccabolle;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import dev.spaccabolle.display.Display;
import dev.spaccabolle.entity.CollectBall;
import dev.spaccabolle.gfx.Assets;
import dev.spaccabolle.input.KeyManager;
import dev.spaccabolle.input.MouseManager;
import dev.spaccabolle.states.State;
import dev.spaccabolle.states.StateGame;
import dev.spaccabolle.states.StateMenu;


/**
 * The Class Game.
 */
public class Game implements Runnable {

	/** The display. */
	private Display display;
	
	/** The height. */
	private int width, height;
	
	/** The title. */
	public String title;
	
	/** The running. */
	private boolean running = false;
	
	/** The thread. */
	private Thread thread;
	
	/** The bs. */
	private BufferStrategy bs;
	
	/** The g. */
	private Graphics g;
	
	/** The game state. */
	//States
	public State gameState;
	
	/** The menu state. */
	public State menuState;
	
	/** The game over state. */
	public State gameOverState;
	
	/** The key manager. */
	//Input
	private KeyManager keyManager;
	
	/** The mouse manager. */
	private MouseManager mouseManager;
	
	/** The handler. */
	//Handler
	private Handler handler;
	
	/**
	 * Instantiates a new game.
	 *
	 * @param title the title
	 * @param width the width
	 * @param height the height
	 */
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	/**
	 * Inits the.
	 */
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init();
		
		handler = new Handler(this);
		this.menuState = new StateMenu(this.handler);
		this.gameState = new StateGame(this.handler);
		State.setState(this.menuState);
	}
	
	/**
	 * Restart.
	 */
	public void restart() {
		Assets.init();
		gameState = new StateGame(handler);
	}
	
	/**
	 * Tick.
	 */
	private void tick(){
		keyManager.tick();
		
		if(State.getState() != null)
			State.getState().tick();
		
		if (CollectBall.gameOver) {
			if(KeyManager.restart) {
				restart();
				CollectBall.gameOver = false;
			}
		}
		if(StateMenu.home) {
			restart();
			StateMenu.home = false;
		}
	}
	
	/**
	 * Render.
	 */
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
				
		if(State.getState() != null) {
			State.getState().render(g);
		}
		bs.show();
		g.dispose();
	}
	
	/**
	 * Run.
	 */
	public void run(){
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;		
		@SuppressWarnings("unused")
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}
	
	/**
	 * Gets the key manager.
	 *
	 * @return the key manager
	 */
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	/**
	 * Gets the mouse manager.
	 *
	 * @return the mouse manager
	 */
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * Start.
	 */
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Stop.
	 */
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
