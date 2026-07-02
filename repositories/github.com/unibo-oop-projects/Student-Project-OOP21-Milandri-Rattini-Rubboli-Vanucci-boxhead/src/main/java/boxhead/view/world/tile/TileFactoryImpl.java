package boxhead.view.world.tile;

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
	private TileSet tiles;

	/**
	 * Constructor, URL of the image for TileSet, size of the tile in the image
	 *
	 * @param url
	 * @param s
	 */

	public TileFactoryImpl(final double s) {
		this.tileSize = s;
		this.tiles = new TileSetImpl(this.tileSize);
	}

	@Override
	public void setTileSize(final double size) {
		tileSize = size;
	}

	@Override
	public final Tile createTile(final int t, final Point2D pos, final double s) {

		return new Tile() {

			private ImageView img = new ImageView();
			private SnapshotParameters sP = new SnapshotParameters();
			private Point2D p = pos;
			private double size = s;
			private double scale = 1.0;

			@Override
			public Image getTile() {
				this.img.setImage(tiles.getTile(t));
				return this.img.snapshot(sP, null);
			}

			@Override
			public double getSize() {
				return this.size * scale;
			}

			@Override
			public void setRenderScale(final double scale) {
				this.scale = scale;
				sP.setFill(Color.TRANSPARENT);
				this.img.setFitHeight(s * scale);
				this.img.setFitWidth(s * scale);

			}

			@Override
			public double getRenderScale() {
				return this.scale;
			}

			@Override
			public Point2D getRelativePos() {
				return this.p.multiply(getSize());
			}

			@Override
			public Point2D getPos() {
				return this.p;
			}

			@Override
			public double getX() {
				return this.p.getX();
			}

			@Override
			public double getY() {
				return this.p.getY();
			}

		};
	}

	@Override
	public Set<Tile> createTiles(final Map<Point2D, Integer> tile, final double s) {
		return tile.entrySet().stream().
                <Tile>map(e -> createTile(e.getValue(), e.getKey(), s)).collect(Collectors.toSet());
	}

}
