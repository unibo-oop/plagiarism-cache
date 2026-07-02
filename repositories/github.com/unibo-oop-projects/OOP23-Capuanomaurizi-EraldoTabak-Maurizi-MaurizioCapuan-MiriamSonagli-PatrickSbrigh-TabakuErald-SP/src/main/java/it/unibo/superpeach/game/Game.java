package it.unibo.superpeach.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Logger;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.superpeach.gameentities.blocks.BlocksHandler;
import it.unibo.superpeach.gameentities.enemies.EnemiesHandler;
import it.unibo.superpeach.gameentities.player.Peach;
import it.unibo.superpeach.gameentities.player.PlayerHandler;
import it.unibo.superpeach.gameentities.powerups.PowerupsHandler;
import it.unibo.superpeach.graphics.PeachMenu;
import it.unibo.superpeach.graphics.Texturer;
import it.unibo.superpeach.keyboard.Keyboard;
import it.unibo.superpeach.level.Camera;
import it.unibo.superpeach.level.LevelHandler;

/**
 * Implementation of the runnable game.
 * 
 * @author Maurizio Capuano
 */
@SuppressFBWarnings(value = {
        "H", "D", "ST"
}, justification = "Static fields assignments wich lead the whole game running (e. g. getTexturer())")
public final class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 10L;

    private static int gameScale = 2;

    private static final int MILLS_PER_SECOND = 1000;
    private static final int NANOS_PER_SECOND = 1_000_000_000;
    private static final int GRAPHICS_BUFFERS = 3;
    private static final double TICKS_PER_SECOND = 60.0;
    private static final String GAME_NAME = "Super Peach";
    private static final int WINDOW_WIDTH = 480;
    private static final int WINDOW_HEIGHT = 360;
    private static final int PLAYER_DEFAULT_X = 240;
    private static final int PLAYER_DEFAULT_Y = WINDOW_HEIGHT / 2;
    private static final int PLAYER_DEFAULT_WID_HEIG = 16;
    private static final int GAME_LIVES = 3;
    private static final int GAME_COINS = 7;
    private static final int GAMEOVER_X_PADDING = 7;
    private static final int GAMEOVER_Y_PADDING = 25;

    private boolean running;
    private boolean gameOver;
    private int gameOverBuffers;

    private transient BlocksHandler blocksHandler;
    private static Texturer texturer;
    private transient Camera camera;
    private static PeachMenu window;
    private transient PlayerHandler playerHandler;
    private transient EnemiesHandler enemiesHandler;
    private transient PowerupsHandler powerupsHandler;
    private transient Scoreboard scoreboard;

    /**
     * Creation of the window frame with the constructor call of a Game object
     * within parameters.
     * 
     * @param args possible command line arguments
     */
    public static void main(final String[] args) {
        window = new PeachMenu(GAME_NAME, WINDOW_WIDTH, WINDOW_HEIGHT, gameScale, new Game());
    }

    /**
     * Initialization of game variables and call of the game start method.
     */
    public void init() {
        texturer = new Texturer();
        blocksHandler = new BlocksHandler();
        enemiesHandler = new EnemiesHandler();
        playerHandler = new PlayerHandler();
        powerupsHandler = new PowerupsHandler();
        scoreboard = new Scoreboard(GAME_LIVES, GAME_COINS, gameScale);
        playerHandler.takePlayer(new Peach(PLAYER_DEFAULT_X, PLAYER_DEFAULT_Y, PLAYER_DEFAULT_WID_HEIG,
                PLAYER_DEFAULT_WID_HEIG, gameScale, blocksHandler, enemiesHandler, powerupsHandler, scoreboard));
        LevelHandler levelHandler;
        levelHandler = new LevelHandler(blocksHandler, gameScale, enemiesHandler);
        levelHandler.parseDrawLevel();
        camera = new Camera(WINDOW_WIDTH, gameScale);
        this.addKeyListener(new Keyboard(playerHandler, this));
        start();
    }

    /**
     * Game loop thread creation and thread starting method.
     */
    private void start() {
        final Thread mainGameLoop = new Thread(this);
        mainGameLoop.start();
        running = true;
    }

    /**
     * Restarting the whole game with a new scale for the window and every internal
     * graphic component.
     * 
     * @param newScale new scale of the game to restart from
     */
    public void changeScale(final int newScale) {
        gameScale = newScale;
        window.closeWindow();
        window = new PeachMenu(GAME_NAME, WINDOW_WIDTH, WINDOW_HEIGHT, gameScale, new Game());
    }

    /**
     * Game restarting method either called while playing or after game over.
     */
    public void restart() {
        stop();
        window.closeWindow();
        window.stopBackgroundMusic();
        window = new PeachMenu(GAME_NAME, WINDOW_WIDTH, WINDOW_HEIGHT, gameScale, new Game());
    }

    private void stop() {
        running = false;
    }

    /**
     * Override of Thread run() method, implementing the actual game loop with
     * framerate and tickrate.
     */
    @SuppressFBWarnings(value = {
            "M", "C", "FL"
    }, justification = "Cannot avoid using delta in while() loop since its value oscillates between approximately 0 and 1")
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double ticksAmount = TICKS_PER_SECOND;
        final double ns = NANOS_PER_SECOND / ticksAmount;
        double delta = 0;
        long timer = System.currentTimeMillis();

        // GAMELOOP
        while (running) {
            final long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            if (System.currentTimeMillis() - timer >= MILLS_PER_SECOND) {
                timer += MILLS_PER_SECOND;
            }
        }
    }

    private void tick() {
        playerHandler.tick();
        enemiesHandler.tickEnemies();
        powerupsHandler.tickPowerups();
        camera.tick(playerHandler.getPlayer());
        if (playerHandler.getPlayer().hasLost() || playerHandler.getPlayer().hasWon()) {
            gameOver = true;
        }
    }

    private void render() {
        try {
            final BufferStrategy buffStrat = this.getBufferStrategy();
            if (buffStrat == null) {
                this.createBufferStrategy(3);
                return;
            }
            final Graphics g = buffStrat.getDrawGraphics();
            if (!gameOver) {
                g.setColor(Color.PINK);
                g.fillRect(0, 0, WINDOW_WIDTH * gameScale, WINDOW_HEIGHT * gameScale);
                g.translate(camera.getCameraX(), 0);
                blocksHandler.renderBlocks(g);
                enemiesHandler.renderEnemies(g);
                playerHandler.render(g);
                powerupsHandler.renderPowerups(g);
                scoreboard.render(g, playerHandler.getPlayer().getX());
            } else {
                gameOverBuffers++;
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, WINDOW_WIDTH * gameScale, WINDOW_HEIGHT * gameScale);
                g.translate(0, 0);
                g.setColor(Color.PINK);
                g.setFont(new Font("Monospaced", Font.BOLD, GAMEOVER_Y_PADDING * gameScale));
                if (playerHandler.getPlayer().hasWon()) {
                    g.drawString("You WON!", WINDOW_WIDTH * gameScale / 3,
                            WINDOW_HEIGHT * gameScale / 2 - GAMEOVER_Y_PADDING * gameScale);
                    g.drawString("SCORE: " + playerHandler.getPlayer().getScore(), WINDOW_WIDTH * gameScale / 3,
                            WINDOW_HEIGHT * gameScale / 2 + 10 * gameScale);
                } else {
                    g.drawString("Game Over", WINDOW_WIDTH * gameScale / 3,
                            WINDOW_HEIGHT * gameScale / 2 - GAMEOVER_Y_PADDING * gameScale);
                    g.drawString("You LOSE", WINDOW_WIDTH * gameScale / 3 + GAMEOVER_X_PADDING * gameScale,
                            WINDOW_HEIGHT * gameScale / 2 + 10 * gameScale);
                }
                if (gameOverBuffers == GRAPHICS_BUFFERS) {
                    stop();
                }
            }
            g.dispose();
            buffStrat.show();
        } catch (IllegalStateException e) {
            Logger.getLogger("IllegalStateException in Game.render()");
        }
    }

    /**
     * @return the object that contains every sprite of all game objects
     */
    public static Texturer getTexturer() {
        return texturer;
    }

}
