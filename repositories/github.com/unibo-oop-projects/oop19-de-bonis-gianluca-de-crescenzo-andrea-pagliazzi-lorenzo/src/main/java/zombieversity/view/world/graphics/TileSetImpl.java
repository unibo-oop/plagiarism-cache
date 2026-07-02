package zombieversity.view.world.graphics;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class TileSetImpl implements TileSet {

    private Image source;
    private List<WritableImage> tiles;
    /**
     * Constructor with given URL of the image.
     * @param url
     */
    public TileSetImpl(final String url) {
        this();
        this.loadImage(url);
    }
    /**
     * Empty constructor if image of TileSet is not known yet.
     */
    public TileSetImpl() {
        this.tiles = new LinkedList<>();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void loadImage(final String url) {
        this.source = new Image(getClass().getResourceAsStream(url));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void loadTiles(final int tileSz) {
      final PixelReader pxR = this.source.getPixelReader();
      final int w = tileSz;
      final int h = tileSz;
      for (int i = 0; i < this.source.getHeight() / tileSz; i++) {
          for (int j = 0; j < this.source.getWidth() / tileSz; j++) {
              this.tiles.add(new WritableImage(pxR, j * tileSz, i * tileSz, w, h));
          }
      }
     }
    /**
     * {@inheritDoc}
     */
    @Override
    public final WritableImage getTile(final int index) {
        return this.tiles.get(index);
    }

}
