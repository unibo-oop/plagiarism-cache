package view.util;

import util.Pair;

/**
 * Utility class meant to provide and internally keep default view values such
 * as fps values and resolutions values.
 */
public final class ViewStaticUtilities {
    private static final int STD_FPS_VALUE = 60;
    private static final Pair<Integer, Integer> STD_RESOLUTION_VALUE = new Pair<Integer, Integer>(800, 450);

    private static int selectedFPS = STD_FPS_VALUE;
    private static Pair<Integer, Integer> selectedResolution = STD_RESOLUTION_VALUE;

    private static boolean godMode;

    private ViewStaticUtilities() {

    }

    /**
     * Whenever the user select a specific fps value, this value is stored for the
     * current "session".
     *
     * @param value
     *            the selected value in {@link OptionsSceneController}
     */
    public static void setSelectedFPS(final int value) {
        selectedFPS = value;
    }

    /**
     * Get the value of fps needed by {@link controller.Controller} to set gameloop
     * speed.
     * 
     * @return the selected fps value if present, otherwise this method return a
     *         default value of 60 fps.
     */
    public static int getSelectedFPS() {
        return selectedFPS;
    }

    /**
     * Getter of the standard view resolution. The scene graph root node should
     * always set it's sizes based on this default value.
     * 
     * @return the default resolution value of 800x450
     */
    public static Pair<Integer, Integer> getStandardResolution() {
        return STD_RESOLUTION_VALUE;
    }

    /**
     * Whenever the user select a specific resolution value, this value is stored
     * for the current "session".
     *
     * @param value
     *            the selected value in {@link OptionsSceneController}
     */
    public static void setSelectedResolution(final Pair<Integer, Integer> value) {
        selectedResolution = value;
    }

    /**
     * Get the value of resolution needed by {@link view.SceneManager} to scale the
     * window contents properly.
     * 
     * @return the selected fps value if present, otherwise this method return a
     *         default value of 60 fps.
     */
    public static Pair<Integer, Integer> getSelectedResolution() {
        return selectedResolution;
    }

    /**
     * Getter of the variable godMode.
     * 
     * @return true if "god mode" is selected, false otherwise.
     */
    public static boolean isGodModeSelected() {
        return godMode;
    }

    /**
     * Whenever the user check the "god mode" CheckBox; this value is stored for the
     * current "session".
     * 
     * @param value
     *            true if god mode is selected, false otherwise
     */
    public static void setGodMode(final boolean value) {
        godMode = value;
    }

}
