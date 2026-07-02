package it.unibo.oop.crossline.launcher;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.crossline.game.Crossline;
import it.unibo.oop.crossline.io.Settings;

/**
 * This is the class responsible of managing the model of the launcher.
 */
public class LauncherModelImpl implements LauncherModel {

    private static final int DEFAULT_VOLUME = 50;

    private Settings settings;

    @Override
    public final Settings getSettings() {
        if (settings == null) {
            final String settingsPath = Settings.DEFAULT_PATH;
            final File settingsFile = new File(settingsPath);
            if (settingsFile.exists()) {
                try {
                    settings = Settings.load(Settings.DEFAULT_PATH);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                settings = new Settings();
                settings.setFullscreen(false);
                final GraphicsDevice defualtScreen = getDefaultScreen();
                settings.setScreen(Arrays.asList(getAvailableScreens()).indexOf(defualtScreen));
                settings.setResolution(getScreenResolution(defualtScreen));
                settings.setVolume(DEFAULT_VOLUME);
            }
        }
        return settings;
    }

    @Override
    public final void saveSettings() {
        try {
            getSettings().save(Settings.DEFAULT_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final GraphicsDevice[] getAvailableScreens() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
    }

    @Override
    public final GraphicsDevice getDefaultScreen() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    }

    @Override
    public final Dimension getScreenResolution(final GraphicsDevice screen) {
        final int width = screen.getDisplayMode().getWidth();
        final int height = screen.getDisplayMode().getHeight();
        return new Dimension(width, height);
    }

    @Override
    public final void launchGame() {
        final LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        final Settings settings = getSettings();
        configuration.width = settings.getResolution().width;
        configuration.height = settings.getResolution().height;
        configuration.fullscreen = settings.isFullscreen();
        configuration.resizable = false;
        new LwjglApplication(new Crossline(), configuration);
    }

}
