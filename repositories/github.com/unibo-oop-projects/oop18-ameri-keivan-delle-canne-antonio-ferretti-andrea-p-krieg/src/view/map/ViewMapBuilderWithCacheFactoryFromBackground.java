package view.map;

import java.util.Map;
import java.util.stream.Collectors;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import util.Coordinates;
import util.image.ImageFactory;
import util.image.ImageFactoryWithCache;
import util.mapbuilder.GameMapBuilder;
import util.mapbuilder.GameMapBuilderFactory;

/**
 * return builders of the view game map with the background already set. It's
 * useful since the background never changes.
 */
public class ViewMapBuilderWithCacheFactoryFromBackground implements GameMapBuilderFactory<GridPane> {
    private static final ImageFactory IMAGE_FACTORY = new ImageFactoryWithCache();
    private final Map<Coordinates, Image> base;
    private final Pair<Integer, Integer> size;

    /**
     * 
     * @param background the background of the map, this will be cached for better performance
     * @param size the size of the map
     */
    public ViewMapBuilderWithCacheFactoryFromBackground(final Map<Coordinates, String> background, final Pair<Integer, Integer> size) {
        this.base = background.entrySet().stream()
                .map(e -> new Pair<>(e.getKey(), IMAGE_FACTORY.getImageFromRelativePath(e.getValue())))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
        this.size = size;

    }

    /**{@inheritDoc}**/@Override
    public GameMapBuilder<GridPane> get() {
        return new ViewMapBuilderWithCache(this.base, this.size);
    }
}
