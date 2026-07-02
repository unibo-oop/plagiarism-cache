package view;

import javafx.scene.image.Image;

/**
 * Enumeration used for Person images.
 */
public enum PersonImage {

    /**
     * SUSCEPTIBLE.
     */
    SUSCEPTIBLE("susceptible.png"),

    /**
     * INFECTED.
     */
    INFECTED("infected.png"),

    /**
     * RECOVERED.
     */
    RECOVERED("recovered.png");

    private static final String PATH = "person/";
    private final Image image;

    /**
     * @param imagename
     *                  name of the image stored in resources
     */
    PersonImage(final String imagename) {
        image = new Image(ClassLoader.getSystemResource(PATH + imagename).toExternalForm());
    }

    /**
     * @return
     *          the person image
     */
    public Image getImage() {
        return this.image;
    }

}
