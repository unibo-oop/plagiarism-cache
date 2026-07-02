package zombieversity.view.world.graphics;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class TileFactoryImpl implements TileFactory {
    private double tileSize;
    private TileSet tSet;


    /**
     * Constructor, required the URL of the image for TileSet, and the tilesize in the image.
     * @param url - position of the image used as tileset.
     * @param ts - is different from the tilesize given in createTil that is game logic, this one is Image - dependent.
     */
    public TileFactoryImpl(final String url, final double ts) {
        this.tileSize = ts;
        this.tSet = new TileSetImpl(url);
        this.tSet.loadTiles((int) tileSize);
    }
    @Override
    public final Tile createTile(final Integer t, final Point2D p, final double s) {
        return new Tile() {

            private ImageView img  = new ImageView(tSet.getTile(t));
            private SnapshotParameters sp = new SnapshotParameters();
            private Point2D pos = p;
            private double size = s;
            private double scale = 1.0;

            @Override
            public double getSize() {
                return this.size * scale;
            }
            @Override
            public void setRenderScale(final double scale) {
                this.scale = scale;
                sp.setFill(Color.TRANSPARENT);
                this.img.setFitHeight(s * scale);
                this.img.setFitWidth(s * scale);
            }
            @Override
            public double getRenderScale() {
                return this.scale;
            }
            @Override
            public Point2D getRelativePos() {
                return this.pos.multiply(getSize());
            }
            @Override
            public Point2D getAbsolutePos() {
                return this.pos;
            }
            @Override
            public double getX() {
                return this.pos.getX();
            }
            @Override
            public double getY() {
                return this.pos.getY();
            }
            @Override
            public Image getTile() {
                return this.img.snapshot(sp, null);
            }

        };
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<Tile> createTiles(final Map<Point2D, Integer> tile, final double s) {
        return tile.entrySet().stream().
                <Tile>map(e -> createTile(e.getValue(), e.getKey(), s)).collect(Collectors.toSet());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void setTileProperties(final double size) {
        this.tileSize = size;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final Image createUniqueBackground(final int id, final double w, final double h, final double s) {
        return this.createTile(id, new Point2D(0, 0), s).getTile();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void setTileSet(final TileSet tSet) {
        this.tSet = tSet;
    }

}
