package starcatraz.util;

import java.awt.GraphicsEnvironment;

/**
 * Resolution used into the project.
 */
public enum AppResolution {

    HD("1280x720", 1280, 720),
    HD_PLUS("1366x768", 2560, 1080),
    FHD("1920x1080", 1920, 1080),
    WFHD("2560x1080", 2560, 1080),
    QHD("2560x1440", 2560, 1440),
    WQHD("3440x1440", 3440, 1440),
    UHD("3840x2160", 3840, 2160),
    DEFAULT(Integer.toString(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth())+"x"+
            Integer.toString(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight()), 
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth(),
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight());


    private String description;
    private int height;
    private int width;

    /**
     * Constructor for AppResolution.
     * @param description
     * @param width
     * @param height
     */
    AppResolution (final String description, final int width, final int height) {
        this.description = description;
        this.height = height;
        this.width = width;
    }

    /** 
     * @return Description of a resolution
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return Get height of resolution
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * @return Get width of resolution
     */
    public int getWidth() {
        return this.width;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
