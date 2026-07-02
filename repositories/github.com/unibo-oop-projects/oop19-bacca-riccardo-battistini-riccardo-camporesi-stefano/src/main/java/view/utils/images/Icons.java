package view.utils.images;

import static view.utils.ViewConstraints.IMAGE_PATH;

/**
 * This enumeration lists the type of icons that the application will recognize.
 */
public enum Icons {
    /**
     * 64x64 pixels icon.
     */
    ICON_64("/icons/icon_64.png"),

    /**
     * 32x32 pixels icon.
     */
    ICON_32("/icons/icon_32.png"),

    /**
     * 16x16 pixels icon.
     */
    ICON_16("/icons/icon_16.png");

    private final String path;

    Icons(final String path) {
        this.path = path;
    }

    /**
     * Returns the path for the icon stored on the disk.
     *
     * @return the path.
     */
    public String getPath() {
        return IMAGE_PATH + path;
    }
}
