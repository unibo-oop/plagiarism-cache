package model.util.format;

/**
 * Contains all Mustashi allowed format.
 */
public enum Format {
    /**
     * 
     */
    jpeg("*.jpeg"), jpg("*.jpg"), png("*.png"), bmp("*.bmp"), gif("*.gif"), tiff("*.tiff"),
    /**
     * mshi is Mustashi's proprietary format, contains a complete image's history.
     */
    mshi(".mshi"),
    /**
     * cnv is Mustashi's proprietary format, contains a setted convolution's effect.
     */
    cnv(".cnv"), json(".json");

    private String formatWithDot;

    Format(final String setFormat) {
        this.formatWithDot = setFormat;
    }

    /**
     * @return allowed format preceded by dot "."
     */
    public String toString() {
        return this.formatWithDot;
    }
}
