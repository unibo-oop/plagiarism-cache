package controller;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import game.Game;
import game.GameMode;
import game.GameState;
import view.EndScreen;
import view.MenuPanel;
import view.MenuPanel.MenuType;
import view.View;

/**
 * The main thread of the game, while it runs it calls the game to make every entity update, and make it check for collisions.
 * The gameLoop communicates with the view to update what is shown on the screen.
 */
public final class GameLoop implements Runnable { 

    /**
     * The FPS at which the game is supposed to run.
     */
    public static final int FPS = 60;
    private static final double NANOSECONDS_PER_FRAME = 1000000000 / FPS;
    private static final int NANO_TO_MILLI = 1000000;
    private volatile GameLoopState state;
    private final Game game;
    private final View view;
    private final HighscoreManager highscore;
    private final KeyInput input;
    private final MenuPanel pauseMenu;

    /**
     * @param game the model of the game
     * @param view the view of the game
     * @param highscore the highscoreManager
     * @param input the class responsible for converting input into actions
     */
    public GameLoop(final Game game, final View view, final HighscoreManager highscore, final KeyInput input) {
        this.game = game;
        this.view = view;
        this.highscore = highscore;
        this.input = input;
        this.state = GameLoopState.READY;
        this.pauseMenu = new MenuPanel(view, MenuType.Pause);
    }
 
    /**
     * 
     */
    public synchronized void start() {
        final Thread thread;
        thread = new Thread(this);
        if (this.state.equals(GameLoopState.READY)) {
            thread.start();
            this.state = GameLoopState.RUNNING;
        }
    }

    /**
     * 
     */
    public synchronized void stop() {
            this.state = GameLoopState.PAUSED;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (!this.state.equals(GameLoopState.ENDED)) {
            if (this.state.equals(GameLoopState.PAUSED)) {
                this.view.switchWindow(this.pauseMenu, MenuPanel.TITLE_PAUSE);
            }
            while (this.state.equals(GameLoopState.PAUSED)) {
                try {
                    Thread.sleep((long) (NANOSECONDS_PER_FRAME / NANO_TO_MILLI));
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                lastTime = System.nanoTime();
            }
            final long now = System.nanoTime();
            delta += (now - lastTime) / NANOSECONDS_PER_FRAME;
            lastTime = now;
            while (delta >= 1) {
                this.input.update();
                this.game.update();
                this.game.checkCollisions();
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
            if (!this.game.getState().equals(GameState.RUNNING)) {
                this.state = GameLoopState.ENDED;
            }
            frames++;
            if (lastTime - System.nanoTime() + NANOSECONDS_PER_FRAME > 0) {
                try {
                    Thread.sleep((long) (lastTime - System.nanoTime() + NANOSECONDS_PER_FRAME) / NANO_TO_MILLI);
                } catch (Exception e) {
                    this.state = GameLoopState.ENDED;
                }
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS:" + frames);
                frames = 0;
            }
        }
        if (this.game.getMode().equals(GameMode.SINGLEPLAYER)) {
            this.highscore.checkHighscores(game.getScore());
        }
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    GameLoop.this.view.switchWindow(new EndScreen(GameLoop.this.game.getMode(), 
                            GameLoop.this.game.getPlayer(), GameLoop.this.game.getScore(), GameLoop.this.view), EndScreen.TITLE);
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
        this.state = GameLoopState.ENDED;
    }

    /**
     * Resumes the gameLoop.
     */
    public void resume() {
        this.state = GameLoopState.RUNNING;
    }
}
