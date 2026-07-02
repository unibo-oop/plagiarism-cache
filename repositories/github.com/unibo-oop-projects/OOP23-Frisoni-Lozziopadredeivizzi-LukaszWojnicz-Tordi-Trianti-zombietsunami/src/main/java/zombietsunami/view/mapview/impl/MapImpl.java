package zombietsunami.view.mapview.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import zombietsunami.view.TextScene;
import zombietsunami.view.api.KeyHandler;
import zombietsunami.view.api.VController;
import zombietsunami.view.mapview.api.Map;
import zombietsunami.view.mapview.api.TileManager;
import zombietsunami.view.obstacleview.api.DrawObstacle;
import zombietsunami.view.obstacleview.impl.DrawObstacleImpl;
import zombietsunami.view.zombieview.api.DrawZombie;
import zombietsunami.view.zombieview.impl.DrawZombieImpl;
import zombietsunami.view.personview.api.DrawPerson;
import zombietsunami.view.personview.impl.DrawPersonImpl;

/**
 * This class implements the Map interface
 * {@link zombietsunami.view.mapview.api.Map}; it contains the game engine of
 * the game.
 */
public final class MapImpl extends JPanel implements Map, Runnable {

    private static final long serialVersionUID = 123456788L;

    private static final double NANOSEC = 1_000_000_000.0;
    private static final long MILLISEC = 1_000;
    private static final int FONT_SIZE = 20;
    private static final int RECT_WIDTH = 160;
    private static final int RECT_HEIGHT = 40;
    private static final int INFO_POS_X = 5;
    private static final int INFO_POS_Y = 25;

    private static final String PAUSE = "PAUSE";
    private static final String WIN = "WINNER";
    private static final String GAMEOVER = "LOSE";

    private transient Thread gameThread;
    private final transient VController controller;

    private final transient TileManager drowMap;
    private final transient DrawZombie drawZombie;
    private final transient DrawObstacle drawObstacle;
    private final transient DrawPerson drawPerson;
    private final transient KeyHandler keyH;

    /**
     * Sets the objects in the map like the zombie and the obstacles.
     * 
     * @param c    sets the view controller
     * @param keyH sets the key listener for the class
     */
    @SuppressFBWarnings(justification = "VController and KeyHandleer must be"
            + "passed in this way because the relative changes in these objects"
            + "are strongly necessary for the correct execution of the game.")
    public MapImpl(final VController c, final KeyHandler keyH) {
        this.controller = c;
        this.keyH = keyH;
        this.drowMap = new TileManagerImpl();
        this.drawZombie = new DrawZombieImpl();
        this.drawObstacle = new DrawObstacleImpl();
        this.drawPerson = new DrawPersonImpl();
        this.setPreferredSize(new DimensionUIResource(controller.getScreenWC(), controller.getScreenHC()));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

    }

    @Override
    public void gameLoop() {
        final double drowIntervall = NANOSEC / controller.getFPSC();
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while (!isPause() && !isWin() && !isGameOver()) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drowIntervall;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

            if (timer >= NANOSEC) {
                timer = 0;
            }
        }
    }

    @Override
    public void update() {
        this.drawZombie.handleKeyPress(this.controller, this.keyH);
        this.controller.collisionZombieOstacleC();
        this.controller.collisionZombiePersonsC();
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (g instanceof Graphics2D) {
            final Graphics2D g2 = (Graphics2D) g;
            this.drowMap.drawMap(g2, controller.titleSizeC(), controller.tileElementsC(), controller.mapIndexListC(),
                    controller.screenTilePosC(), this.controller);
            this.drawObstacle.drawObstacleV(g2, controller.obstacleIndexListC(), controller.screenTilePosC(),
                    controller.titleSizeC(), this.controller);
            this.drawPerson.drawPersonV(g2, controller.personIndexListC(), controller.screenTilePosC(),
                    controller.titleSizeC(), this.controller);
            this.drawZombie.drawZombieV(g2, controller);
            drawInfo(g2);
            if (isPause()) {
                TextScene.scene(g2, PAUSE);
            }
            if (isWin()) {
                TextScene.scene(g2, WIN);
            }
            if (isGameOver()) {
                TextScene.scene(g2, GAMEOVER);
            }
            g2.dispose();
        }
    }

    @Override
    public void run() {
        while (gameThread != null) {
            if (isPause()) {
                repaint();
            } else if (isWin() || isGameOver()) {
                isOver();
            } else {
                gameLoop();
            }
        }
    }

    @Override
    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    /**
     * @param g2 is the graphic to draw the info elements on the screen
     */
    private void drawInfo(final Graphics2D g2) {
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0, 0, RECT_WIDTH, RECT_HEIGHT);
        g2.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        g2.setColor(Color.WHITE);
        g2.drawString("Strenght x " + controller.getStrength(), INFO_POS_X, INFO_POS_Y);
    }

    /**
     * @return if the game is on pause or not
     */
    private boolean isPause() {
        return this.keyH.isOnPause();
    }

    /**
     * @return if you win or not
     */
    private boolean isWin() {
        return this.controller.isWinC();
    }

    /**
     * @return if is Game Over or not
     */
    private boolean isGameOver() {
        return this.controller.isNotEnoughC() || this.controller.isStrenghtZeroC();
    }

    /**
     * This method end the game after 3 seconds.
     */
    @SuppressFBWarnings(justification = "After we interrupt the thread, the use"
     + " of System.out() is a wanted choice of design for this game, that will" 
     + "shut down the entire virtual machine")
    private void isOver() {
        final Logger logger = Logger.getLogger(MapImpl.class.getName());
        try {
            Thread.sleep(MILLISEC * 3);
            Thread.currentThread().interrupt();
            System.exit(0);
        } catch (InterruptedException e) {
            logger.severe("Error: " + e.getMessage());
        }

    }
}
