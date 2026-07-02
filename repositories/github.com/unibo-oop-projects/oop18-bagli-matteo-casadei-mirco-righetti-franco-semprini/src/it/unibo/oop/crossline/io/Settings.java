package it.unibo.oop.crossline.io;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Settings class for initial screen, resolution and audio settings.
 */
public class Settings implements Serializable {

    /**
     * Default settings file path.
     */
    public static final String DEFAULT_PATH = new File(".").getAbsolutePath().toString() + "settings.ser";

    private static final long serialVersionUID = -8583321555253130663L;

    private boolean fullscreen;
    private int screen;
    private Dimension resolution;
    private int volume;


    /**
     * Can be called only after the launcher as been closed.
     * 
     * @param path the path of settings file
     * @return settings object
     * @throws IOException when file is missing
     */
    public Settings getSettings(final String path) throws IOException {
        return load(path);
    }

    /**
     * @return fullscreen boolean
     */
    public final boolean isFullscreen() {
        return fullscreen;
    }

    /**
     * @param fullscreen boolean
     */
    public final void setFullscreen(final boolean fullscreen) {
        this.fullscreen = fullscreen;
    }

    /**
     * @return screen identifier value
     */
    public final int getScreen() {
        return screen;
    }

    /**
     * @param screen the screen identifier value
     */
    public final void setScreen(final int screen) {
        this.screen = screen;
    }

    /**
     * @return resolution dimension
     */
    public final Dimension getResolution() {
        return resolution;
    }

    /**
     * @param resolution the resolution dimension
     */
    public final void setResolution(final Dimension resolution) {
        this.resolution = resolution;
    }

    /**
     * @return volume value
     */
    public final int getVolume() {
        return volume;
    }

    /**
     * @param volume the volume value
     */
    public final void setVolume(final int volume) {
        this.volume = volume;
    }

    /**
     * Static method for load settings from file.
     * 
     * @param path the settings file path
     * @return settings object
     * @throws IOException when file is missing
     */
    public static final Settings load(final String path) throws IOException {
        final FileInputStream file = new FileInputStream(path);
        final ObjectInputStream in = new ObjectInputStream(file);
        Settings settings = new Settings();
        try {
            settings = (Settings) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        in.close();
        file.close();
        return settings;
    }

    /**
     * Save current setting to a settings file.
     * 
     * @param path the settings file path
     * @throws IOException when file is missing
     */
    public final void save(final String path) throws IOException {
        final FileOutputStream file = new FileOutputStream(path);
        final ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(this);
        out.close();
        file.close();
    }

    /**
     * HashCode method.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        final int magic1 = 1231;
        final int magic2 = 1237;
        result = prime * result + (fullscreen ? magic1 : magic2);
        result = prime * result + ((resolution == null) ? 0 : resolution.hashCode());
        result = prime * result + screen;
        result = prime * result + volume;
        return result;
    }

    /**
     * Equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Settings other = (Settings) obj;
        if (fullscreen != other.fullscreen) {
            return false;
        }
        if (resolution == null) {
            if (other.resolution != null) {
                return false;
            }
        } else if (!resolution.equals(other.resolution)) {
            return false;
        }
        if (screen != other.screen) {
            return false;
        } else if (volume != other.volume) {
            return false;
        }
        return true;
    }

    /**
     * ToString method.
     */
    @Override
    public String toString() {
        return "Settings [fullscreen=" + fullscreen + ", screen=" + screen + ", resolution=" + resolution + ", volume="
                + volume + "]";
    }
}
