package oop.lit.view;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import oop.lit.util.ImageConverter;

//Nota su svantaggi di questa soluzione: se vengono apportati cambiamenti ad una BufferedImage, la Image corrispondente non viene aggiornata.
/**
 * A class used to get a JavaFX image from a BufferedImage.
 * Converted images are stored.
 */
public final class FXImageConverter extends ImageConverter<Image> {
    private static final FXImageConverter SINGLETON = new FXImageConverter();

    /**
     * @return
     *      the instance of this loader.
     */
    public static FXImageConverter get() {
        return SINGLETON;
    }

    private FXImageConverter() {
        super(image -> SwingFXUtils.toFXImage(image, null), new Image("file:res/defaultImage.png"));
    }
}
