package view.utils.images;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

/**
 * This class allows to create images of tiles, store them and make them
 * available on request.
 *
 * In addition it allows the loading of icons.
 *
 * This is a singleton.
 */
public class ImageManager {

    private static final ImageManager IMAGE_MANAGER = new ImageManager(new ImageCache());
    private final ImageCache imageCache;

    public ImageManager(final ImageCache imageCache) {
        this.imageCache = imageCache;
    }

    public static ImageManager getImageLoaderInstance() {
        return IMAGE_MANAGER;
    }

    /**
     * Return an {@link Image} from the given tile type.
     *
     * @param tile   the tile to be loaded
     * @param width  width of the requested tile
     * @param height height of the requested tile
     * @return an {@link Image} corresponding to the type of tile requested
     */
    public Image getTileImage(final Tiles tile, final int width, final int height) {
        return imageCache.getTileImage(tile, width, height);
    }

    /**
     * Returns a {@link PixelReader} from the image loaded from the given type.
     *
     * @param tile   the tile to be loaded
     * @param width  width of the requested tile
     * @param height height of the requested tile
     * @return a PixelReader from the image loaded from the given type
     */
    public PixelReader getTileReader(final Tiles tile, final int width, final int height) {
        return getTileImage(tile, width, height).getPixelReader();
    }

    /**
     * @return a list of the icons to be used by the stage
     */
    public List<Image> getIconsList() {
        final List<Image> iconList = new ArrayList<>();
        for (final Icons s : Icons.values()) {
            iconList.add(new Image(ImageManager.class.getResourceAsStream(s.getPath())));
        }
        return iconList;
    }

}
