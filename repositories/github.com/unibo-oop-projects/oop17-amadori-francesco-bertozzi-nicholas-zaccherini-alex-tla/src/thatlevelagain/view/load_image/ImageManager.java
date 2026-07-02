package thatlevelagain.view.load_image;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import thatlevelagain.CaricatoreImmagini;


/**
 * 
 */
public final class ImageManager {
    private static List<BufferedImage> loader = new LinkedList<>();

    /**
     * 
     */
    private ImageManager() {
    }

    /**
     * Inizialize all image inside the enum ImagePath.
     */
    public static void init() {
        final List<String> imageList = Stream.of(ImagePath.values())
                    .map(ImagePath::getPathImage)
                    .collect(Collectors.toList());
        imageList.forEach(path -> {
            loader.add(CaricatoreImmagini.getCaricatoreImmagini().carica(path));
        });
    }

    /**
     * @return the list of all BufferedImage.
     */
    public static List<BufferedImage> getListLoader() {
        return loader;
    }

}
