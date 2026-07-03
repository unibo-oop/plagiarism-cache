package it.lttply.model.domain;

/**
 * This enumerator provides commons resolutions for the images: those parameters
 * must be used only for {@link http://www.themoviedb.org}.
 * 
 * Remember that not all the parameters are valid for all kind of images, check
 * the {@link http://www.themoviedb.org} documentation.
 */
public enum PictureFormat {

    /**
     * The commons resolutions.
     */
    W_45("w45"), W_92("w92"), W_150("w150"), W_154("w154"), W_185("w185"), W_300("w300"), W_342("w342"), H_632("h632"), W_500("w500"), W_780("w780"), W_HD_READY("w1280"), ORIGINAL("original");

    private final String value;

    /**
     * The private constructor of the enumerator
     * 
     * @param value
     *            the {@link String} which represents the resolution
     */
    PictureFormat(final String value) {
        this.value = value;
    }

    /**
     * @return the resolution value for {@link http://www.themoviedb.org} API
     */
    public String getValue() {
        return this.value;
    }
}
