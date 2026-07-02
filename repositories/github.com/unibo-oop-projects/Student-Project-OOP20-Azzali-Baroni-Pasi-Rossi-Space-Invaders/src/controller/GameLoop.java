package controller;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import model.Game;
import model.GameStatus;
import view.GameOverView;
import view.MenuP;
import view.MenuP.MenuState;
import view.View;

/**
 * The Class GameLoop that manages the loop of the game.
 */
public final class GameLoop implements Runnable{
	
	/** The Constant FPS. */
	public static final int FPS = 60;
	
	/** The Constant NANOSECONDS_FRAME. */
	private static final double NANOSECONDS_FRAME = 1000000000 / FPS;
	
	/** The Constant NANO_MILLISECONDS. */
	private static final int NANO_MILLISECONDS = 1000000;
	
	/** The game loop status. */
	private volatile GameLoopStatus gameLoopStatus;
    
    /** The game. */
    private final Game game;
    
    /** The view. */
    private final View view;
    
    /** The highscore. */
    private final ScoreController highscore;
    
    /** The input. */
    private final Input input;
    
    /** The pause menu. */
    private final MenuP pauseMenu;
	
    
    /**
     * Instantiates a new game loop.
     *
     * @param game the game
     * @param view the view
     * @param highscore the highscore
     * @param input the input
     */
    public GameLoop(final Game game, final View view , final ScoreController highscore, final Input input) {
        this.game = game;
        this.view = view;
        this.highscore = highscore;
        this.input = input;
        this.gameLoopStatus = GameLoopStatus.READY;
        this.pauseMenu = new MenuP(view, MenuState.Pause);
    }
	
	
	
	/**
	 * Start.
	 */
	public synchronized void start() {
		final Thread thread;
		thread = new Thread(this);
        if (this.gameLoopStatus.equals(GameLoopStatus.READY)) {
            thread.start();
            this.gameLoopStatus = GameLoopStatus.RUNNING;
        }
	}
	
	/**
	 * Stop.
	 */
	public synchronized void stop() {
		this.gameLoopStatus = GameLoopStatus.PAUSED;
	}
	
	/**
	 * Run.
	 */
	@Override
	public void run() {
		long lastTime = System.nanoTime();
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (!this.gameLoopStatus.equals(GameLoopStatus.ENDED)) {
            if (this.gameLoopStatus.equals(GameLoopStatus.PAUSED)) {
                this.view.switchWindow(this.pauseMenu, MenuP.PAUSE_T);
            }
            while (this.gameLoopStatus.equals(GameLoopStatus.PAUSED)) {
                try {
                    Thread.sleep((long) (NANOSECONDS_FRAME / NANO_MILLISECONDS));
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                lastTime = System.nanoTime();
            }
            final long now = System.nanoTime();
            delta += (now - lastTime) / NANOSECONDS_FRAME;
            lastTime = now;
            while (delta >= 1) {
                this.input.update();
                this.game.update();
                this.game.checkCollision();
                delta -= 1;
            }
            try {
                SwingUtilities.invokeAndWait(new Runnable() {
                    public void run() {
                        GameLoop.this.view.draw(GameLoop.this.game.getEntities(), GameLoop.this.game.getScore(), GameLoop.this.game.getLevel());
                    }
                });
            } catch (InvocationTargetException | InterruptedException e1) {
                e1.printStackTrace();
            }
            
            if (!this.game.getStatus().equals(GameStatus.RUNNING)) {
                this.gameLoopStatus = GameLoopStatus.ENDED;
            }
            if (lastTime - System.nanoTime() + NANOSECONDS_FRAME > 0) {
                try {
                    Thread.sleep((long) (lastTime - System.nanoTime() + NANOSECONDS_FRAME) / NANO_MILLISECONDS);
                } catch (Exception e) {
                    this.gameLoopStatus = GameLoopStatus.ENDED;
                }
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
            this.highscore.checkScore(game.getScore());
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    GameLoop.this.view.switchWindow(new GameOverView(GameLoop.this.game.getPlayer(), 
                             GameLoop.this.game.getScore(), GameLoop.this.view), GameOverView.TITLE);
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	 /**
     * Aborts the gameLoop.
     */
    public void abort() {
        this.gameLoopStatus = GameLoopStatus.ENDED;
    }

    /**
     * Resumes the gameLoop.
     */
    public void resume() {
        this.gameLoopStatus = GameLoopStatus.RUNNING;
    }
	
}
