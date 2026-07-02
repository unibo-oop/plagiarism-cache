package zombieversity.view.world;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import zombieversity.view.world.graphics.Tile;
import zombieversity.view.world.graphics.TileFactory;
/**
 * Class implementing worldView concept.
 * {@link}
 */
public class WorldViewImpl implements WorldView {

    private final TileFactory tf;
    private final Set<Tile> tiles;
    private Set<Pair<Point2D, Image>> rendered;
    private Set<Pair<Point2D, Image>> background;
    private final double w;
    private final double h;
    private final double ts;
    private double scale;
    private final Integer bkg;
    /**
     * Constructor of the worldView.
     * @param blocks - collection of blocks to be translated in relative tiles.
     * @param tf - tileFactory to create tiles.
     * @param ts - tileSize 
     * @param bg - index of the background used to create the layer background.
     * @param w - width of the world.
     * @param h - height of the world.
     */
    public WorldViewImpl(final Map<Point2D, Integer> blocks, final TileFactory tf,
            final double ts, final Integer bg, final double w, final double h) {
        this.tf = tf;
        tiles = tf.createTiles(blocks, ts);
        this.bkg = bg;
        this.ts = ts;
        this.w = w;
        this.h = h;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<Tile> getMap() {
        return this.tiles;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void setScale(final double scale) {
        this.scale = scale;
        tiles.forEach(t -> t.setRenderScale(scale));
        rendered = null;
        background = null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<Pair<Point2D, Image>> renderMap() {
        Set<Pair<Point2D, Image>> result;
        if (rendered == null) {
            result = new HashSet<>();
            tiles.stream()
            .forEach(t -> {
                result.add(new Pair<>(t.getRelativePos(), t.getTile()));
                });
            rendered = result;
        } else {
            result = rendered;
        }
        return result;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<Pair<Point2D, Color>> renderMiniMap(final Point2D start, final Point2D end, final Point2D rel) {
       return new HashSet<>();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<Pair<Point2D, Image>> renderBackground() {
        Set<Pair<Point2D, Image>> result;
        if (background == null) {
         result = new HashSet<>();
         for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                   final Tile t = tf.createTile(bkg, new Point2D(x * ts * this.scale, y * ts * this.scale), ts * this.scale);
                    result.add(new Pair<>(t.getAbsolutePos(), t.getTile()));
                }
             }
         background = result;
        } else {
            result = background;
        }
        return result;
    }
}
