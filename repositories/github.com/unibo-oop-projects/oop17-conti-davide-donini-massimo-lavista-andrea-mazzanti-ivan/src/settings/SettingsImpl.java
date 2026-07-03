package settings;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * 
 * Implements the necessaries methods to change the game settings.
 *
 */
public final class SettingsImpl implements Settings {

    private static final int DEFAULT_X_RESOLUTION = 800;
    private static final int DEFAULT_Y_RESOLUTION = 600;
    private static final int FIRST_SCREEN_PROPORTION = 3;
    private static final int LAST_SCREEN_PROPORTION = 8;
    private static final Pair<Integer, Integer> DEFAULT_RESOLUTION = new ImmutablePair<>(DEFAULT_X_RESOLUTION,
            DEFAULT_Y_RESOLUTION);
    private static final Pair<Integer, Integer> SCREEN_RESOLUTION = new ImmutablePair<Integer, Integer>(
            (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
            (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());

    private final List<Pair<Integer, Integer>> supportedresolutions = new ArrayList<>();
    private Pair<Integer, Integer> selectedresolution = new ImmutablePair<>(
            SettingsImpl.SCREEN_RESOLUTION.getKey() * 3 / 4, SettingsImpl.SCREEN_RESOLUTION.getValue() * 3 / 4);

    private static final int DEFAULT_FPS = 60;
    private final Set<Integer> supportedFPS = new TreeSet<>(Arrays.asList(10, 20, 30, 60, GraphicsEnvironment
            .getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate()));
    private int selectedFPS = DEFAULT_FPS;

    private boolean fullScreen;
    private boolean backgroundaudio = true;
    private boolean trainingMode;

    private static SettingsImpl singleton;

    private SettingsImpl() {

    }

    /**
     * @return the Settings instance.
     */
    public static SettingsImpl getSettings() {
        if (Objects.isNull(singleton)) {
            singleton = new SettingsImpl();
        }
        return singleton;
    }

    @Override
    public void setSelectedResolution(final Pair<Integer, Integer> selectedresolution) {
        if (this.supportedresolutions.isEmpty()) {
            this.setScreenResolutions();
        }

        // Required if the selected resolution is not suitable for this screen.
        if (!this.supportedresolutions.contains(selectedresolution)) {
            this.selectedresolution = this.supportedresolutions.get(this.supportedresolutions.size() / 2);
        } else {
            this.selectedresolution = selectedresolution;
        }

        if (this.selectedresolution.getKey().equals(SettingsImpl.SCREEN_RESOLUTION.getKey())
                && this.selectedresolution.getValue().equals(SettingsImpl.SCREEN_RESOLUTION.getValue())) {
            this.setFullScreen(true);
        } else {
            this.setFullScreen(false);
        }
    }

    @Override
    public Pair<Integer, Integer> getSelectedresolution() {
        return this.selectedresolution;
    }

    @Override
    public int getSelectedFPS() {
        return this.selectedFPS;
    }

    @Override
    public void setSelectedFPS(final int selectedFPS) {
        this.selectedFPS = selectedFPS;
    }

    @Override
    public double getScaleFactor() {
        return Math.min((double) this.selectedresolution.getValue() / SettingsImpl.DEFAULT_RESOLUTION.getValue(),
                (double) this.selectedresolution.getKey() / SettingsImpl.DEFAULT_RESOLUTION.getKey());
    }

    @Override
    public Set<Integer> getSupportedFps() {
        return this.supportedFPS;
    }

    @Override
    public List<Pair<Integer, Integer>> getSupportedResolutions() {
        this.setScreenResolutions();
        return this.supportedresolutions.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public boolean isFullScreen() {
        return this.fullScreen;
    }

    @Override
    public void setFullScreen(final boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    @Override
    public boolean isBackGroundAudioActivated() {
        return this.backgroundaudio;
    }

    @Override
    public void setBackGroundAudio(final boolean backgroundaudio) {
        this.backgroundaudio = backgroundaudio;
    }

    @Override
    public boolean isTrainingMode() {
        return this.trainingMode;
    }

    @Override
    public void setTrainingMode(final boolean trainingMode) {
        this.trainingMode = trainingMode;
    }

    /* Provides some resolutions supported by this screen. */
    private void setScreenResolutions() {
        this.supportedresolutions.clear();
        for (int i = SettingsImpl.FIRST_SCREEN_PROPORTION; i <= SettingsImpl.LAST_SCREEN_PROPORTION; i++) {
            this.supportedresolutions.add(new ImmutablePair<>((int) SettingsImpl.SCREEN_RESOLUTION.getKey() * i / 8,
                    (int) SettingsImpl.SCREEN_RESOLUTION.getValue() * i / SettingsImpl.LAST_SCREEN_PROPORTION));
        }
    }

}
