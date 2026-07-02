package view.utils.images;

import java.util.EnumMap;
import java.util.Map;

import javafx.scene.image.Image;

public class ImageCache {

    private final Map<Tiles, Image> tileMap;

    public ImageCache() {
        tileMap = new EnumMap<>(Tiles.class);
    }

    /**
     * Return a cached {@link Image} from the given tile type. If the image was not
     * yet cached caches it.
     *
     * @param tile   The tile to be loaded
     * @param width  width of the requested tile
     * @param height height of the requested tile
     * @return An {@link Image} corresponding to the type of tile requested
     */
    public Image getTileImage(final Tiles tile, final int width, final int height) {
        tileMap.computeIfAbsent(tile, k -> TileCreator.getTileImage(tile, width, height));
        return tileMap.get(tile);
    }
}
