package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;

/**
 * Enumeration used for place images. A place could be associated with different
 * images, method getImage returned ones randomly.
 */
public enum PlaceImage {

    /**
     * HOME.
     */
    HOME("home.png"),

    /**
     * HOSPITAL.
     */
    HOSPTITAL("hospital.png"),

    /**
     * MEETING_PLACE.
     */
    MEETING_PLACE("restaurant.png", "church.png", "stadium.png", "bank.png");

    private static final String PATH = "place/";
    private final List<Image> images;

    /**
     * @param imagename names of images stored in resources
     */
    PlaceImage(final String... imagename) {
        images = new ArrayList<>();
        Arrays.stream(imagename)
                .forEach(n -> images.add(new Image(ClassLoader.getSystemResource(PATH + n).toExternalForm())));
    }

    /**
     * @return a random image associated with a place
     */
    public Image getImage() {
        final Random r = new Random();
        return images.get(r.nextInt(images.size()));
    }

}
