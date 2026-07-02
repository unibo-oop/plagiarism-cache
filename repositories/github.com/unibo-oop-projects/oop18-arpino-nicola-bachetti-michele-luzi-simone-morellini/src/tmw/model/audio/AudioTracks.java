package tmw.model.audio;

import java.net.URL;

/**
 * In there are stored game audio tracks.
 * 
 */
public enum AudioTracks {

    /**
     * MainMenu track.
     */
    MAINMENU_TRACK("menu.mp3"),

    /**
     * Tutorial track.
     */
    TUTORIAL_TRACK("tutorial.mp3"),

    /**
     * Select level menu track.
     */
    SELECT_LEVEL_TRACK("menu.mp3"),

    /**
     * Track to be reproduced in any level.
     */
    LEVELS_TRACK("level.mp3"),

    /**
     * Boss level track.
     */
    BOSS_TRACK("boss.mp3");

    private final String name;

    AudioTracks(final String name) {
        this.name = name;
    }

    /**
     * Path builder. Generate a path for audio tracks.
     * 
     * @return path in string form
     */
    public String getPath() {
        final URL url = getClass().getClassLoader().getResource("audio/" + name);
        return url.toString();
    }
}

