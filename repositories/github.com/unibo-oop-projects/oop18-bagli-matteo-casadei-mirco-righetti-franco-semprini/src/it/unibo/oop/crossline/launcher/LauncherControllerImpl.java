package it.unibo.oop.crossline.launcher;

import java.io.IOException;

import it.unibo.oop.crossline.io.Settings;

/**
 *  This is the class responsible of bridging the view and the model of the launcher.
 */
public class LauncherControllerImpl implements LauncherController {

    private final LauncherView view;
    private final LauncherModel model;

    /**
     * Initialize the launcher controller.
     * @param view the view to use
     * @param model the model to use
     */
    public LauncherControllerImpl(final LauncherView view, final LauncherModel model) {
        this.view = view;
        this.model = model;
        final Settings settings = model.getSettings();
        loadSettings(settings);
        view.setPlayListener(e -> {
            saveSettings(settings);
            view.setVisibility(false);
            model.launchGame();
        });
    }

    @Override
    public final void loadSettings(final Settings settings) {
        view.setAvailableScreens(model.getAvailableScreens().length);
        view.setSelectedScreen(settings.getScreen());
        view.setFullscreen(settings.isFullscreen());
        view.setResolution(settings.getResolution());
        view.setVolume(settings.getVolume());

    }

    @Override
    public final void saveSettings(final Settings settings) {
        settings.setScreen(view.getSelectedScreen());
        settings.setResolution(view.getResolution());
        settings.setFullscreen(view.isFullscreen());
        settings.setVolume(view.getVolume());
        try {
            model.getSettings().save(Settings.DEFAULT_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
