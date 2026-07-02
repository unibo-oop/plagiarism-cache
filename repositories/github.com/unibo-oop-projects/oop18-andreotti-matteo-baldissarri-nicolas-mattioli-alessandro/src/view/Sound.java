package view;

/**
 * 
 * All path of music and SFX sound.
 *
 */
public enum Sound {

    /**
     * Menu track.
     */
    MENUTRACK("/music/menu.wav"),

    /**
     * In Game track.
     */
    GAMETRACK("/music/inGame.wav");

    private final String path;

    Sound(final String path) {
        this.path = path;
    }

    /**
     * @return the music path.
     */
    public String getPath() {
        return path;
    }
}
