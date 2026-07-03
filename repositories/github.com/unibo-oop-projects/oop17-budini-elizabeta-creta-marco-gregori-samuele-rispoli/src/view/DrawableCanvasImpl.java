package view;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import utilities.ImageLoader;

/**
 * A decorator class encapsulating a BufferedImage. This class is used to manage
 * the drawing process, foreground and background are separated for efficiency
 * reason.
 * 
 */
public final class DrawableCanvasImpl implements DrawableCanvas {

    private BufferedImage foreGround;
    private BufferedImage backGround;
    private final ImageLoader loader = ImageLoader.getInstance();
    private String backGroundPath;
    private final int height;
    private final int width;

    /**
     * Constructor for the Canvas class, it initializes all the needed fields.
     * 
     * @param width
     *            The width of the game model.
     * @param height
     *            The height of the game model.
     * @param backGroundPath
     *            The path of the current background image.
     */
    public DrawableCanvasImpl(final int width, final int height, final String backGroundPath) {
        super();
        this.height = height;
        this.width = width;
        foreGround = getEmptyLayer();
        this.backGroundPath = backGroundPath;
        initBackGround();
    }

    private void drawOnLayer(final Graphics2D g, final Image toDraw, final Point position, final Dimension dimension) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
        g.drawImage(toDraw, position.x, position.y, dimension.width, dimension.height, null);
        g.dispose();
    }

    @Override
    public void drawEntity(final Sprites toDraw, final int x, final int y) {
        final Graphics2D g = foreGround.createGraphics();
        drawOnLayer(g, toDraw.getIcon().getImage(), new Point(x, y), toDraw.getDimension());
    }

    @Override
    public void drawOnBackground(final Sprites toDraw, final int x, final int y) {
        final Graphics2D g = backGround.createGraphics();
        drawOnLayer(g, toDraw.getIcon().getImage(), new Point(x, y), toDraw.getDimension());

    }

    @Override
    public BufferedImage getforeGround() {
        final BufferedImage bufferedForeGround = foreGround;
        this.foreGround = getEmptyLayer();
        return bufferedForeGround;
    }

    @Override
    public BufferedImage getBackGround() {
        return backGround;
    }

    @Override
    public void setbackGround(final String backGroundPath) {
        this.backGroundPath = backGroundPath;
        initBackGround();
    }

    private BufferedImage getEmptyLayer() {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    private void initBackGround() {
        backGround = getEmptyLayer();
        final Graphics2D g = backGround.createGraphics();
        drawOnLayer(g, loader.getImage(backGroundPath).getImage(), new Point(0, 0), new Dimension(width, height));
    }

}
