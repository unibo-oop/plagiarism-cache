package javawulf.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javawulf.controller.GameLoop;
import javawulf.controller.GameLoopImpl;
import javawulf.controller.PlayerStatus;
import javawulf.scoreboard.ResultImpl;
import javawulf.scoreboard.ScoreBoardImpl;
import javawulf.scoreboard.Scoreboard;
import javawulf.view.gamemenu.GameMenuPanel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * JPanel where videogame match is drawn.
 */
public final class GamePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    // Screen settings
    /** Original Tile Size (even entities and game objects have the dimension of a tile). */
    public static final int ORIGINAL_TILE_SIZE = 24;
    /** Constant used to calculate SCALE.  */
    private static final int SCALE_FACTOR = 400;
    /** Elements scaling field. It is proportional to display resolution. */
    public static final int SCALE = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / SCALE_FACTOR;
    /** Scaled dimension of a tile. */
    public static final int TILESIZE = ORIGINAL_TILE_SIZE * SCALE;
    /** Max columns of tile (width). */
    public static final int MAX_SCREEN_COL = 15;
    /** Max rows of tile (height). */
    public static final int MAX_SCREEN_ROW = 15;

    @SuppressFBWarnings(
        value = {
            "M", "V", "Se"
        },
        justification = "This object does not have to be serializable"
    )
    private final List<Drawer> drawers = new ArrayList<>();
    private final JFrame frame;

    /**
     * Used to inizialize GamePanel. 
     * It starts an importart part of in-gaming controller: Game Loop.
     * It sets several default view details, like drawers of components.
     * 
     * @param frame The JFrame that creates it
     */
    @SuppressFBWarnings(
        value = {
            "M", "V", "EI2"
        },
        justification = "The frame is used to reset the frame itself to the main menu "
            + "and update the scoreboard with the information coming from the controller"
    )
    public GamePanel(final JFrame frame) {
        this.frame = frame;
        final GameLoop gameLoopController = new GameLoopImpl(this);
        this.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        this.setBackground(java.awt.Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(new CommandListener(gameLoopController.getPlayerController()));
        this.setFocusable(true);
        final PlayerStatus playerStatus = gameLoopController.getPlayer();
        this.drawers.add(new MapDrawer(gameLoopController.getMap(), this));
        this.drawers.add(new PlayerDrawer(playerStatus, this));
        this.drawers.add(new PawnDrawer(playerStatus, this, gameLoopController.getPawns()));
        this.drawers.add(new ItemDrawer(this, gameLoopController.getItems(), playerStatus));
        this.drawers.add(new AmuletPiecesDrawer(this, playerStatus, gameLoopController.getAmuletPieces()));
        this.drawers.add(new PowerUpsDrawer(this, gameLoopController.getPowerUps(), playerStatus));
        this.drawers.add(new HUDDrawer(playerStatus, gameLoopController.getAmuletPieces(), this));
        gameLoopController.startGameLoopThread();
    }

    @Override
    @SuppressFBWarnings(
        value = {
            "L", "D", "BC"
        },
        justification = "The cast made here does not cause exceptions"
    )
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Graphics2D graphics2d = (Graphics2D) graphics;

        drawers.forEach(d -> {
            d.draw(graphics2d);
        });
        graphics2d.dispose();
    }

    /**
     * Resets the frame that created GamePanel in order to show the main menu.
     * 
     * @param gameWon Boolean that inidcates whether the player has won or not
     * @param score The amount of points obtained by the player by the end of the game
     */
    public void resetFrame(final boolean gameWon, final int score) {
        final String value = gameWon ? "CONGRATULATIONS! You escaped sucessfully"
            : "Oh no, Game Over. Better luck next time!";
        final String username = JOptionPane.showInputDialog(value + "\n Your point total is " + score
            + "\n\n Insert your USERNAME:" 
            + "\n(if empty will not be saved)");
        this.setVisible(false);
        // Save scoreBoard
        if (!username.isEmpty()) {
            final Scoreboard scoreboard = new ScoreBoardImpl();
            scoreboard.loadScoreBoardFromFile();
            scoreboard.addNewScore(new ResultImpl(username, score, gameWon));
            scoreboard.saveScoreBoard();
        }
        // Clear the frame
        frame.getContentPane().removeAll();
        // Open gameMenuPanel
        GameMenuPanel.createMenuGUI(frame);
    }
}
