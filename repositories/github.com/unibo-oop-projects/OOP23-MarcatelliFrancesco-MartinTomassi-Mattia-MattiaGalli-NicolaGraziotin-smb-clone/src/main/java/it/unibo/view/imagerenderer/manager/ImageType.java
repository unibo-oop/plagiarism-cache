package it.unibo.view.imagerenderer.manager;

/**
 * The ImageType enum represents different types of images used in the application.
 */
public enum ImageType {
    /**
     * Background image.
     */
    BACKGROUND("background.png"),

    /**
     * Image representing the character Meat Boy.
     */
    MEAT_BOY("meatboy.png");

    private final String imageName;

    /**
     * Constructs an ImageType enum constant with the specified image name.
     *
     * @param imageName The name of the image file.
     */
    ImageType(final String imageName) {
        this.imageName = imageName;
    }

    /**
     * Returns the name of the image file associated with this ImageType.
     *
     * @return The name of the image file.
     */
    @Override
    public String toString() {
        return this.imageName;
    }

}
