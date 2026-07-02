package justanotherchessgame.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Class used to generate images and stage icons.
 */
public final class ImageGenerator {

    private static final String FOLDER = "res/img/";

    /**
     * Private constructor for the static utility class.
     */
    private ImageGenerator() {
    };

    /**
     * Function used to generate a piece image.
     * @param path is the image path.
     * @return is the image generated.
     */
    public static ImageView generateImage(final String path) {
        Image img = null;
        final ImageView imageView = new ImageView();
        try (InputStream is = Files.newInputStream(Paths.get(FOLDER + path))) {
            img = new Image(is);
        } catch (IOException e) {
            System.out.println("Couldn't load " + Paths.get(FOLDER + path) + " image");
        }
        imageView.setImage(img);
        return imageView;
    }

    /**
     * Function used to generate and set the icon of a stage.
     * @param stage is the stage we want to set the icon.
     * @param path is the path of the icon.
     */
    public static void iconGenerator(final Stage stage, final String path) {
        try (InputStream is = Files.newInputStream(Paths.get(FOLDER + path))) {
            final Image img = new Image(is);
            stage.getIcons().add(img);
        } catch (IOException e) {
            System.out.println("Couldn't load image" + Paths.get(FOLDER + path));
        }
    }
}
