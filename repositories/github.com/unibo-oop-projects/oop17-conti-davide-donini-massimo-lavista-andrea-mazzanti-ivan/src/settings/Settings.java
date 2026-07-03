package settings;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

/**
 * 
 * Provides the necessaries methods to change the game settings.
 *
 */
public interface Settings {

    /**
     * Sets the game resolution selected by the user.
     * 
     * @param selectedresolution
     *            the resolution selected by the user.
     */
    void setSelectedResolution(Pair<Integer, Integer> selectedresolution);

    /**
     * 
     * @return the current selected game resolution.
     */
    Pair<Integer, Integer> getSelectedresolution();

    /**
     * 
     * @return the game FPS selected by the user.
     */
    int getSelectedFPS();

    /**
     * Sets the game FPS selected by the user.
     * 
     * @param selectedFPS
     *            the game FPS selected by the user.
     */
    void setSelectedFPS(int selectedFPS);

    /**
     * Provides the factor by which coordinates are scaled about the center of the
     * object.
     * 
     * @return the scale factor.
     */
    double getScaleFactor();

    /**
     * Provides the default FPS set.
     * 
     * @return a Set of the default FPS.
     */
    Set<Integer> getSupportedFps();

    /**
     * Provides all the resolutions supported by the current screen.
     * 
     * @return a list of the resolutions supported by the current screen.
     */
    List<Pair<Integer, Integer>> getSupportedResolutions();

    /**
     * 
     * @return true if the full screen is enabled, false otherwise.
     */
    boolean isFullScreen();

    /**
     * Causes the Game to be set in full screen.
     * 
     * @param fullScreen
     *            It is true if the full screen is enabled, false otherwise.
     */
    void setFullScreen(boolean fullScreen);

    /**
     * 
     * @return true if the background audio of the game is enabled, false otherwise.
     */
    boolean isBackGroundAudioActivated();

    /**
     * Causes the Game background audio to be enabled or disabled.
     * 
     * @param backgroundaudio
     *            It is true if the game background audio is enabled, false
     *            otherwise.
     */
    void setBackGroundAudio(boolean backgroundaudio);

    /**
     * 
     * @return true if Training Mode is selected, false otherwise.
     */
    boolean isTrainingMode();

    /**
     * Causes the Training Mode to be enabled or disabled.
     * 
     * @param trainingMode
     *            the Training Mode flag. It is true if god mode is selected, false
     *            otherwise.
     */
    void setTrainingMode(boolean trainingMode);

}
