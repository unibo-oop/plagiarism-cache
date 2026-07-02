package view.utils.images;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Interface used to create images of tiles that should be displayed. It allows
 * the creation of different type of tiles.
 */
public interface TileCreator {

    /**
     * States how much thick are the borders of the tile.
     */
    int BORDER_THICKNESS = 3;

    /**
     * Return an {@link Image} from the given tile type.
     *
     * @param tile   the type of tile that should be created
     * @param width  width of the tile to be created
     * @param height height of the tile to be created
     * @return The image created
     */
    static Image getTileImage(final Tiles tile, final int width, final int height) {
        final WritableImage tileImage = new WritableImage(width, height);
        final PixelWriter writer = tileImage.getPixelWriter();

        final Set<Integer> excludes = polishBorders(width, height);

        IntStream.range(0, height).boxed().flatMap(x -> IntStream.range(0, width).boxed().map(y -> Pair.of(x, y)))
                .forEach(p -> {
                    if (tile.isPolished() && (excludes.contains(p.getKey()) || excludes.contains(p.getValue()))) {
                        writer.setColor(p.getLeft(), p.getRight(), Color.rgb(64, 64, 64));
                    } else {
                        writer.setColor(p.getLeft(), p.getRight(), tile.getColor());
                    }
                });

        return tileImage;
    }

    /**
     * This method improves the visual appearance of a tile.
     *
     * @return a set containing the number representing the borders that should be
     *         drawn in a way that make the vehicle seem smaller.
     * @param width  width of the tile to polish
     * @param height height of the tile to polish
     */
    private static Set<Integer> polishBorders(final int width, final int height) {
        if (width != height) {
            throw new UnsupportedOperationException("This method can be used only on square tiles");
        }

        final Set<Integer> excludes = new HashSet<>();

        for (int i = 0; i < BORDER_THICKNESS; i++) {
            excludes.add(i);
        }

        for (int i = width - BORDER_THICKNESS; i < width; i++) {
            excludes.add(i);
        }

        return excludes;

    }

}
