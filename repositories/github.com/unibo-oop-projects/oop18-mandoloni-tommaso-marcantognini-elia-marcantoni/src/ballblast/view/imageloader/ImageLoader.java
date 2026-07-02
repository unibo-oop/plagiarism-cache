package ballblast.view.imageloader;


import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

import javafx.scene.image.Image;

/**
 * A simple image loader with caching.
 */
public class ImageLoader {
    private static final ImageLoader SINGLETON = new ImageLoader();
    private final Map<ImagePath, Image> imageMap;

    /**
     * Simple constructor.
     */
    public ImageLoader() {
        this.imageMap = new EnumMap<>(ImagePath.class);
    }

    /**
     * 
     * @return the {@link ImageLoader}.
     */
    public static ImageLoader getLoader() {
        return SINGLETON;
    }

    /**
     * @param imagePath the path of the image to get.
     * @return the image of the object required.
     */
    public Image getImage(final ImagePath imagePath) {
        if (imagePath.equals(ImagePath.BALL) && !this.imageMap.containsKey(imagePath)) {
            final Image img = this.loadImage(BallColors.getRandomColor());
            this.imageMap.put(imagePath, img);
            return img;
        } else if (!this.imageMap.containsKey(imagePath)) {
            final Image img = this.loadImage(imagePath.getPath());
            this.imageMap.put(imagePath, img);
            return img;
        } else {
            return this.imageMap.get(imagePath);
        }
    }

    /**
     * Loads all images.
     */
    public void loadAll() {
        Arrays.stream(ImagePath.values()).forEach(this::getImage);
    }

    /**
     * Remove ball image to change it for another game.
     */
    public void removeBall() {
        this.imageMap.remove(ImagePath.BALL, imageMap.get(ImagePath.BALL));
    }

    private Image loadImage(final String imagePath) {
        return new Image(ImageLoader.class.getResourceAsStream(imagePath));
    }
}
