package view.rendering;

import java.util.Set;

import javafx.scene.image.PixelWriter;

/**
 * This interface defines an object that can be draw on a WritableImage.
 *
 * @param <T> the type of object to draw on the image.
 */
@FunctionalInterface
public interface Drawable<T> {

    /**
     * Allows to write an object in a Image.
     *
     * @param writer the {@link PixelWriter} of the image on which the object should
     *               be drawn
     * @param object the object to be drawn
     */
    void draw(PixelWriter writer, T object);

    /**
     * Allows to write a set of objects in a Image.
     *
     * @param writer  the {@link PixelWriter} of the image on which the objects
     *                should be drawn
     * @param objects the objects to be drawn
     */
    default void drawAll(PixelWriter writer, Set<T> objects) {
        for (final T o : objects) {
            draw(writer, o);
        }
    }
}
