package ballblast.view.rendering;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.gameobjects.GameObjectType;
import ballblast.view.imageloader.ImageLoader;
import ballblast.view.imageloader.ImagePath;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import ballblast.model.gameobjects.Ball;
import ballblast.model.gameobjects.BallType;

/**
 * 
 */
public class ImageSprite implements Sprite, Renderer {

    private static final double DEFAULT = 100.0;
    private static final double MAX_ALPHA = 1.0;
    private static final double MIN_ALPHA = 0.0;
    private static final int DECS_OFFSET = 2;
    private static final int CENTS_OFFSET = 2;
    private static final int MAX_TEXT_WIDTH = 10;
    private static final int LARGE_FONT_SIZE = 10;
    private static final int MEDIUM_FONT_SIZE = 8;
    private static final int SMALL_FONT_SIZE = 4;
    private static final Font FONT_LARGE = Font.font("Roboto", LARGE_FONT_SIZE);
    private static final Font FONT_MEDIUM = Font.font("Roboto", MEDIUM_FONT_SIZE);
    private static final Font FONT_SMALL = Font.font("Roboto", SMALL_FONT_SIZE);
    private final GraphicsContext gc;
    private final GameObject gameObject;
    private double gameObjectWidth;
    private double gameObjectHeight;
    private double alpha;
    private Coordinate sourceTopLeft;
    private Vector2D sourceOffset;
    private Coordinate position;
    private Image image;
    private Coordinate gameObjectPosition;
    private Font usingFont;

    /**
     * Creates a new Image sprite with the given GraphicsContext.
     * 
     * @param gc         the {@link GraphicsContext}.
     * @param gameObject the {@link GameObject} to be rendered.
     */
    public ImageSprite(final GraphicsContext gc, final GameObject gameObject) {
        super();
        this.sourceTopLeft = new Coordinate(0, 0);
        this.sourceOffset = Vector2D.create(0, 0);
        this.position = new Coordinate(0, 0);
        this.alpha = MAX_ALPHA;
        this.gc = gc;
        this.image = null;
        this.gameObject = gameObject;
        this.gameObjectHeight = DEFAULT;
        this.gameObjectWidth = DEFAULT;
        this.gameObjectPosition = null;
    }

    @Override
    public final void render() {
        // Draw the gameObject
        this.gc.scale(1, -1);
        this.gc.setGlobalAlpha(this.getAlpha());
        this.gc.setTextBaseline(VPos.TOP);
        this.gc.drawImage(this.image, this.getSourceTopLeftCorner().getX(), this.getSourceTopLeftCorner().getY(),
                this.image.getWidth(), this.image.getHeight(), this.getGameObjectPosition().getX(),
                this.getGameObjectPosition().getY(), this.getGameObjectWidth(), this.getGameObjectHeight());

        if (this.gameObject.getType().equals(GameObjectType.BALL) && ((Ball) this.gameObject).getCurrentLife() > 0) {
            drawLife();
        } else if (this.gameObject.getType().equals(GameObjectType.BALL)
                && (((Ball) this.gameObject).getCurrentLife() <= 0)) {
            this.renderFireworks(gc, gameObjectWidth, gameObjectHeight);
        }
    }

    @Override
    public final void setPosition(final Coordinate coordinate) {
        this.position = coordinate;
    }

    @Override
    public final Coordinate getPosition() {
        return this.position;
    }

    @Override
    public final void setSourceWindow(final Coordinate topLeft, final Vector2D offset) {
        this.sourceTopLeft = topLeft;
        this.sourceOffset = offset;
    }

    @Override
    public final double getImageSourceWidth() {
        return this.image.getWidth();
    }

    @Override
    public final Image getImageSource() {
        return this.image;
    }

    @Override
    public final double getImageSourceHeight() {
        return this.image.getHeight();
    }

    @Override
    public final void setSource(final ImagePath source) {
        this.image = ImageLoader.getLoader().getImage(source);
        this.setSourceWindow(new Coordinate(0, 0),
                new Vector2D(this.getImageSourceWidth(), this.getImageSourceHeight()));
    }

    @Override
    public final void setAlpha(final double alpha) {
        this.alpha = Math.min(MAX_ALPHA, Math.max(MIN_ALPHA, alpha));
    }

    @Override
    public final double getAlpha() {
        return this.alpha;
    }

    @Override
    public final Coordinate getSourceTopLeftCorner() {
        return this.sourceTopLeft;
    }

    /**
     * Returns the offset of the bottom-right corner from the top-left corner.
     * 
     * @return the offset in pixel.
     */
    protected final Vector2D getSourceOffset() {
        return this.sourceOffset;
    }

    @Override
    public final void setGameObjectWidth(final double width) {
        this.gameObjectWidth = width;
    }

    @Override
    public final double getGameObjectWidth() {
        return this.gameObjectWidth;
    }

    @Override
    public final void setGameObjectHeight(final double height) {
        this.gameObjectHeight = height;
    }

    @Override
    public final double getGameObjectHeight() {
        return this.gameObjectHeight;
    }

    @Override
    public final void setGameObjectPosition(final Coordinate position) {
        this.gameObjectPosition = position;
    }

    @Override
    public final Coordinate getGameObjectPosition() {
        return this.gameObjectPosition;
    }

    /**
     * Static method used to render the background.
     * 
     * @param gc     the {@link GraphicsContext} of the canvas.
     * @param width  the width of the canvas.
     * @param height the height of the canvas.
     */
    public static void renderBackground(final GraphicsContext gc, final double width, final double height) {
        gc.drawImage(ImageLoader.getLoader().getImage(ImagePath.BACKGROUND), 0, 0, width, height);
    }

    /**
     * Static method used to render the background.
     * 
     * @param gc     the {@link GraphicsContext} of the canvas.
     * @param width  the width of the canvas.
     * @param height the height of the canvas.
     */
    public void renderFireworks(final GraphicsContext gc, final double width, final double height) {
        gc.drawImage(ImageLoader.getLoader().getImage(ImagePath.FIREWORKS),
                this.getGameObjectPosition().getX() - this.getGameObjectWidth() / 2,
                this.getGameObjectPosition().getY() - this.getGameObjectHeight() / 2, this.getGameObjectWidth() * 2,
                this.getGameObjectHeight() * 2);
    }

    /**
     * 
     */
    public void drawLife() {
        // Set the font
        if (((Ball) this.gameObject).getBallType().getDiameter() == (BallType.LARGE.getDiameter())) {
            this.gc.setFont(FONT_LARGE);
            this.usingFont = FONT_LARGE;
        } else if (((Ball) this.gameObject).getBallType().getDiameter() == (BallType.MEDIUM.getDiameter())) {
            this.gc.setFont(FONT_MEDIUM);
            this.usingFont = FONT_MEDIUM;
        } else if (((Ball) this.gameObject).getBallType().getDiameter() == (BallType.SMALL.getDiameter())) {
            this.gc.setFont(FONT_SMALL);
            this.usingFont = FONT_SMALL;
        }
        if (((Ball) this.gameObject).getCurrentLife() < 10) {
            drawUnits();
        } else if (((Ball) this.gameObject).getCurrentLife() < 100) {
            drawDecs();
        } else {
            drawCents();
        }
        // Draw life inside the ball

    }

    private void drawUnits() {
        this.gc.strokeText(Integer.toString(((Ball) (this.gameObject)).getCurrentLife()),
                this.getGameObjectPosition().getX() + (((Ball) this.gameObject).getBallType().getDiameter() / 2)
                        - (this.usingFont.getSize() / 3),
                this.getGameObjectPosition().getY() + (((Ball) this.gameObject).getBallType().getDiameter() / 2)
                        - (this.usingFont.getSize() / 2),
                MAX_TEXT_WIDTH);
    }

    private void drawDecs() {
        this.gc.strokeText(Integer.toString(((Ball) (this.gameObject)).getCurrentLife()),
                this.getGameObjectPosition().getX() + (((Ball) this.gameObject).getBallType().getDiameter() / 2)
                        - (this.usingFont.getSize() / 3) - DECS_OFFSET,
                this.getGameObjectPosition().getY() + (((Ball) this.gameObject).getBallType().getDiameter() / 2)
                        - (this.usingFont.getSize() / 2),
                MAX_TEXT_WIDTH);
    }

    private void drawCents() {
        this.gc.strokeText(Integer.toString(((Ball) (this.gameObject)).getCurrentLife()),
                this.getGameObjectPosition().getX() + (((Ball) this.gameObject).getBallType().getDiameter() / 2)
                        - (this.usingFont.getSize() / 3) - CENTS_OFFSET,
                this.getGameObjectPosition().getY() + (((Ball) this.gameObject).getBallType().getDiameter() / 2)
                        - (this.usingFont.getSize() / 2),
                MAX_TEXT_WIDTH);
    }

}
