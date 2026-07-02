package bubbleshooter.controller.sound;

/**
 * Enumeration class which contains all the path to sound's resources in the project.
 */
public enum SoundNames {

    /**
     *The main background music of the Game.
     */
    BACKGROUND("/sound/Background.wav"),

    /**
     *Another background music of the Game.
     */
    GEOMETRY("/sound/Geometry.wav"),

    /**
     *Another background music of the Game.
     */
    COFFIN("/sound/Coffin.wav"),

    /**
     *The sound used when a {@link Bubble} is shot.
     */
    SHOT("/sound/shot.wav"),

    /**
     *The sound used when a {@link Bubble} pop.
     */
    POP("/sound/pop.wav"),

    /**
     *The sound used when a {@link Bubble} snap.
     */
    SNAP("/sound/snap.wav"),

    /**
     *The sound used when a {@link Bubble} explode.
     */
    BOMB("/sound/bomb.wav");

    /**
     * The path of the resource in the project.
     */
    private final String path;

    /** 
    *@param path
    */
    SoundNames(final String path) {
        this.path = path;
    }

    /**
     * @return the path of the resource in the project.
     */
    public String getPath() {
        return this.path;
    }
}
