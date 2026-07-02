package view.utils;

/**
 *
 */
public final class ViewConstraints {

    /**
     * Where are located images, under the folder resources.
     */
    public static final String IMAGE_PATH = "/images";

    /**
     * The title of the main stage that will be shown.
     */
    public static final String TITLE = "TrafficSimulator";

    /**
     *
     */
    public static final String MAIN_SCENE_PATH = "layouts/base.fxml";

    /**
     *
     */
    public static final long MS_BETWEEN_FRAMES = 200;

    /**
     *
     */
    public static final double MAX_UPDATE_BOUND = 0.06;

    /**
     * Used to convert from milliseconds to seconds.
     */
    public static final double MSEC_TO_SEC = 0.001;

    private ViewConstraints() {
    }

}
