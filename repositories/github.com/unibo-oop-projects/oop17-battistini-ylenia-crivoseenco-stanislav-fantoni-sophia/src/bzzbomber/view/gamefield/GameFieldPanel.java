package bzzbomber.view.gamefield;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import javax.swing.JPanel;

import bzzbomber.controller.Controller;
import bzzbomber.game.Game;
import bzzbomber.model.Level;
import bzzbomber.model.entities.BzzBomber;
import bzzbomber.model.entities.Entity;
import bzzbomber.model.entities.Explosion;
import bzzbomber.view.TileImg;

/**
 * Represents a graphical representation of Game field grid 2D of Tiles.
 */
public class GameFieldPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int TILE_MULTIPLIER = 3;
    private static final double BOMBER_MULTIPLIER = 0.1;

    private final Controller controller;
    private final Canvas gameFieldCanvas;
    private final int tileWidth;
    private final int tileHeight;

    private final GameField gameField;

    /**
     * @param c
     *            Reference of Controller.
     * @param width
     *            Desired width of the panel.
     * @param height
     *            Desired height of the panel.
     */
    public GameFieldPanel(final Controller c, final int width, final int height) {
        super();
        this.controller = c;

        this.gameField = new GameFieldImpl(Game.MAP_ROWS, Game.MAP_COLUMNS);

        this.gameFieldCanvas = new Canvas();
        this.gameFieldCanvas.setSize(new Dimension(width, height));
        this.gameFieldCanvas.setPreferredSize(new Dimension(width, height));
        this.gameFieldCanvas.setMinimumSize(new Dimension(width, height));
        this.gameFieldCanvas.setMaximumSize(new Dimension(width, height));
        // Remove focus from canvas in order to handle key input from JFrame
        this.gameFieldCanvas.setFocusable(false);

        this.tileWidth = Game.TILE_WIDTH;
        this.tileHeight = Game.TILE_HEIGHT;

        this.initGameField();
    }

    private void initGameField() {
        final Level level = this.controller.getCurrentLevel();
        final int[][] map = level.getGameMap();
        for (int y = 0; y < level.getRows(); y++) {
            for (int x = 0; x < level.getCols(); x++) {

                final int tileToDecode = map[y][x];
                TileImg tileToInsert = null;
                for (final TileImg tmpTile : TileImg.values()) {
                    if (tmpTile.getVal().getFirst() == tileToDecode) {
                        tileToInsert = tmpTile;
                    }
                }

                final Point p = new Point(x, y);
                this.gameField.addTile(p, new TileForGameField(tileToInsert, this.tileWidth, this.tileHeight));
            }
        }
    }

    /**
     * When a game level has been changed this method has to be called to reinit game field grid of Tiles.
     */
    public void levelChanged() {
        this.initGameField();
    }

    @Override
    public final Dimension getPreferredSize() {
        return new Dimension(Game.GAME_FIELD_WIDTH, Game.GAME_FIELD_HEIGHT);
    }

    @Override
    public final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, this.gameFieldCanvas.getWidth(), this.gameFieldCanvas.getHeight());
        drawGround(g, this.tileWidth, this.tileHeight);
        drawEntities(g, this.tileWidth, this.tileHeight);
        drawHero(g, this.tileWidth, this.tileHeight);
    }

    /**
     * Has to be called to repaint game Model status at the moment.
     */
    public void draw() {
        this.repaint();
    }

    private void drawGround(final Graphics g, final int w, final int h) {
        for (int y = 0; y < this.gameField.getRows(); y++) {
            for (int x = 0; x < this.gameField.getColumns(); x++) {
                final Tile tile = this.gameField.getMap()[y][x];
                final Point position = new Point(x * w, y * h);
                g.drawImage(tile.getImage(), (int) position.getX(), (int) position.getY(), w, h, null);
            }
        }
    }

    private synchronized void drawEntities(final Graphics g, final int w, final int h) {
        final List<Entity> entities = this.controller.getEntities();
        for (int i = 0; i < entities.size(); i++) {
            final Entity e = entities.get(i);
            if (e instanceof Explosion) {
                g.drawImage(e.getTile().getImage(), e.getPosition().x, e.getPosition().y, w * TILE_MULTIPLIER,
                        h * TILE_MULTIPLIER, null);
                g.drawImage(e.getTile().getImage(), e.getPosition().x, e.getPosition().y, w, h, null);
                g.drawImage(e.getTile().getImage(), e.getPosition().x + (w * (TILE_MULTIPLIER - 1)), e.getPosition().y,
                        w, h, null);
                g.drawImage(e.getTile().getImage(), e.getPosition().x, e.getPosition().y + (h * (TILE_MULTIPLIER - 1)),
                        w, h, null);
                g.drawImage(e.getTile().getImage(), e.getPosition().x + (w * (TILE_MULTIPLIER - 1)),
                        e.getPosition().y + (h * (TILE_MULTIPLIER - 1)), w, h, null);

            } else {
                final Point position = new Point((int) e.getPosition().getX(), (int) e.getPosition().getY());
                g.drawImage(e.getTile().getImage(), (int) (position.getX()), (int) (position.getY()), w, h, null);
            }
        }
    }

    private synchronized void drawHero(final Graphics g, final int w, final int h) {
        final BzzBomber bomber = this.controller.getBomber();
        g.drawImage(bomber.getTile().getImage(),
                (int) (bomber.getPosition().getX() - bomber.getCollisionBox().width * BOMBER_MULTIPLIER),
                (int) (bomber.getPosition().getY() - bomber.getCollisionBox().height * BOMBER_MULTIPLIER), w, h, null);

    }

}
