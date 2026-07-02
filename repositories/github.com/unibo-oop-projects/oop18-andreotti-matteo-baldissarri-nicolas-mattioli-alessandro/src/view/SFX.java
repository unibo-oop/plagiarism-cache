package view;

/**
 *  In-game sound effects.
 */
public enum SFX {

    /**
     * sfx collision sound.
     */
    SFXCOLLISION("/music/collision.wav"),

    /**
     * sfx bonus sound.
     */
    SFXBONUS("/music/bonus.wav");

    private final String path;

    SFX(final String path) {
        this.path = path;
    }

    /**
     *  @return the sound effect path.
     */
    public String getPath() {
        return getClass().getResource(path).toString();
    }
}
