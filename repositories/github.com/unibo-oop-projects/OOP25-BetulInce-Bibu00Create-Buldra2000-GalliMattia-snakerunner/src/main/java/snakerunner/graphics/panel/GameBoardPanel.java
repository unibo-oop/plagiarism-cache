package snakerunner.graphics.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import snakerunner.commons.Point2D;
import snakerunner.controller.WorldController;
import snakerunner.model.Collectible;
import snakerunner.model.Direction;
import snakerunner.model.Door;
import snakerunner.model.Snake;
import snakerunner.model.SnakeSegment;

/**
 * GameBoardPanel define the visualization of the game.
 */
public final class GameBoardPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final transient WorldController worldController;
    private transient Image foodImage;
    private transient Image keyImage;
    private transient Image obstacleImage;
    private transient Image mushroomImage;
    private transient Image flagImage;
    private transient Image bombImage;
    private transient Image clockImage;
    private transient Image snakeHeadUp; 
    private transient Image snakeHeadDown;
    private transient Image snakeHeadLeft;
    private transient Image snakeHeadRight;
    private transient Image snakeTailUp;
    private transient Image snakeTailDown;
    private transient Image snakeTailLeft;
    private transient Image snakeTailRight;
    private transient Image snakeBodyVertical;
    private transient Image snakeBodyHorizontal;
    private transient Image doorClose;
    private transient Image doorOpen;

    /**
     * Constructor for GameBoardPanel.
     * 
     * @param worldController WorldController.
     */
    public GameBoardPanel(final WorldController worldController) {
        this.worldController = worldController;
        setOpaque(true);
        setBackground(Color.GRAY);
        loadImages();
    }

    /**
     * Draw all Components.
     * 
     * @param g Graphics g.
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        if (worldController == null) {
            return;
        }

        drawGrid(g);
        drawSnake(g);
        drawObstacle(g);
        drawCollectibles(g);
        drawDoors(g);
    }

    private void loadImages() {
        doorClose = loadImage("images/door_close.png");
        doorOpen = loadImage("images/door_open.png");
        foodImage = loadImage("images/food.png");
        clockImage = loadImage("images/clock.png");
        bombImage = loadImage("images/bomb.png");
        keyImage = loadImage("images/key.png");
        flagImage = loadImage("images/flag.png");
        mushroomImage = loadImage("images/mushroom.png");
        obstacleImage = loadImage("images/obstacle.png");
        snakeHeadUp = loadImage("images/head_up.png");
        snakeHeadDown = loadImage("images/head_down.png");
        snakeHeadLeft = loadImage("images/head_left.png");
        snakeHeadRight = loadImage("images/head_right.png");
        snakeTailUp = loadImage("images/tail_up.png");
        snakeTailDown = loadImage("images/tail_down.png");
        snakeTailLeft = loadImage("images/tail_left.png");
        snakeTailRight = loadImage("images/tail_right.png");
        snakeBodyVertical = loadImage("images/body_vertical.png");
        snakeBodyHorizontal = loadImage("images/body_horizontal.png");
    }

    private Image loadImage(final String path) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                return null;
            }

            return ImageIO.read(is);

        } catch (final IOException e) {
            throw new IllegalStateException("Load Images Error", e);
        }
    }

    /**
     * Draw Grid.
     * 
     * @param g Graphics g.
     */
    private void drawGrid(final Graphics g) {
    g.setColor(Color.BLACK);

    final int cellSize = getCellSize();
    final int cols = worldController.getLevel().getGrid().getWidth();
    final int rows = worldController.getLevel().getGrid().getHeight();
    final int gridWidth = cols * cellSize;
    final int gridHeight = rows * cellSize;

    for (int x = 0; x <= cols; x++) {
        g.drawLine(x * cellSize, 0, x * cellSize, gridHeight);
    }

    for (int y = 0; y <= rows; y++) {
        g.drawLine(0, y * cellSize, gridWidth, y * cellSize);
    }
}

    private int getCellSize() {
        final int cols = worldController.getLevel().getGrid().getWidth();
        final int rows = worldController.getLevel().getGrid().getHeight();
        return Math.min(getWidth() / cols, getHeight() / rows);
    }

    /**
     * Draw snake.
     * 
     * @param g Graphics g.
     */
    private void drawSnake(final Graphics g) {
        final Snake snake = worldController.getSnake();

        if (snake == null || snake.getFullBody().isEmpty()) {
            return;
        }

        final List<SnakeSegment> body = snake.getFullBody();

        for (int i = 0; i < body.size(); i++) {
            final SnakeSegment segment = body.get(i);
            final Point2D<Integer, Integer> pos = segment.getPos();
            final int x = pos.getX() * getCellSize();
            final int y = pos.getY() * getCellSize();

            final Image segmentImage;

            if (i == 0) {
                segmentImage = getHeadImage(worldController.getDirection());
            } else if (i == body.size() - 1) {
                final Direction tailDirection = getDirection(body.get(i - 1).getPos(), pos);
                segmentImage = getTailImage(tailDirection);
            } else {
                final Point2D<Integer, Integer> prevPos = body.get(i - 1).getPos();
                segmentImage = getBodyImage(prevPos, pos);
            }

                g.drawImage(segmentImage, x, y, getCellSize(), getCellSize(), this);
        }
    }

    /**
     * Get head image based on direction.
     * 
     * @param direction The direction of the snake.
     * @return the {@Link image} corresponding to the given direction.
     */
    private Image getHeadImage(final Direction direction) {
        return switch (direction) {
            case UP -> snakeHeadUp;
            case DOWN -> snakeHeadDown;
            case LEFT -> snakeHeadLeft;
            case RIGHT -> snakeHeadRight;
        };
    }

    /**
     * Get tail image based on direction.
     * 
     * @param direction The direction of the snake.
     * @return the {@Link image} corresponding to the given direction.
     */
    private Image getTailImage(final Direction direction) {
        return switch (direction) {
            case UP -> snakeTailUp;
            case DOWN -> snakeTailDown;
            case LEFT -> snakeTailLeft;
            case RIGHT -> snakeTailRight;
        };
    }

    /**
     * Determines the correct body segment image (vertical or horizontal) based 
     * on the displacement between two points.
     * 
     * @param prev The coordinates of the previous segment.
     * @param current The coordinates of the current segment.
     * @return The {@link Image} representing a vertical segment.
     */
    private Image getBodyImage(final Point2D<Integer, Integer> prev, final Point2D<Integer, Integer> current) {

        final int dx = current.getX() - prev.getX();

        if (dx == 0) {
            return snakeBodyVertical;
        }

        final int dy = current.getY() - prev.getY();

        if (dy == 0) {
            return snakeBodyHorizontal;
        }

        return snakeBodyHorizontal;
    }

    /**
     * Calculate direction from one point to another.
     * 
     * @param from from The starting point.
     * @param to The destination point.
     * @return The {@link Direction} from the starting point to the destination.
     */
    private Direction getDirection(final Point2D<Integer, Integer> from, final Point2D<Integer, Integer> to) {
        final int dx = to.getX() - from.getX();

        if (dx > 0) {
            return Direction.RIGHT;
        }
        if (dx < 0) {
            return Direction.LEFT;
        }

        final int dy = to.getY() - from.getY();

        if (dy > 0) {
            return Direction.DOWN;
        }
        if (dy < 0) {
            return Direction.UP;
        }

        return Direction.RIGHT;
    }

    /**
     * Draw obstacle.
     * 
     * @param g Graphics g.
     */
    private void drawObstacle(final Graphics g) {
        g.setColor(Color.RED);

        for (final Point2D<Integer, Integer> p : worldController.getObstacles()) {
            final int x = p.getX() * getCellSize();
            final int y = p.getY() * getCellSize();
            g.drawImage(obstacleImage, x, y, getCellSize(), getCellSize(), this);
        }
    }

    /**
     * Draw collectibles.
     * 
     * @param g Grapgics g.
     */
    private void drawCollectibles(final Graphics g) {
       for (final Collectible collectible : worldController.getCollectibles()) {
        final Point2D<Integer, Integer> p = collectible.getPosition();
        final int x = p.getX() * getCellSize();
        final int y = p.getY() * getCellSize();

        switch (collectible.getType()) {
            case FOOD -> g.drawImage(foodImage, x, y, getCellSize(), getCellSize(), this);
            case CLOCK -> g.drawImage(clockImage, x, y, getCellSize(), getCellSize(), this);
            case KEY -> g.drawImage(keyImage, x, y, getCellSize(), getCellSize(), this);
            case LIFE_BOOST -> g.drawImage(mushroomImage, x, y, getCellSize(), getCellSize(), this);
            case FLAG -> g.drawImage(flagImage, x, y, getCellSize(), getCellSize(), this);
            case BOMB -> g.drawImage(bombImage, x, y, getCellSize(), getCellSize(), this);
            // default -> {
            //     g.setColor(Color.YELLOW);
            //     g.fillOval(x, y, CELL, CELL);
            // }
        }
       }
    }

    /**
     * Draw doors.
     * 
     * @param g Graphics g.
     */
    private void drawDoors(final Graphics g) {
        final List<Door> doors = worldController.getDoors();

        if (doors == null) {
            return;
        }

        for (final Door door : doors) {
            final Point2D<Integer, Integer> p = door.getPosition();
            final int x = p.getX() * getCellSize();
            final int y = p.getY() * getCellSize();

            final Image doorImage = door.isOpen() ? doorOpen : doorClose;

            if (doorImage != null) {
                g.drawImage(doorImage, x, y, getCellSize(), getCellSize(), this);
            }

        }
    }
}
