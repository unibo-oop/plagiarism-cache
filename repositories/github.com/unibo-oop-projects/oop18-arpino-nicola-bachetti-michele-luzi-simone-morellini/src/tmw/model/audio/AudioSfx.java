package tmw.model.audio;

import java.net.URL;

/**
 * Audio effects (SFX) container. Here there are all sound effects to use in
 * game.
 * 
 */
public enum AudioSfx {

    /**
     * Sound for item picking.
     */
    ITEMPICK("itemPick.mp3"),

    /**
     * Sound for enemy death.
     */
    ENEMY_KILLED("enemyDeath.mp3"),

    /**
     * Sound effect for destroyed obstacle.
     */
    OBSTACLE_DESTROYED("enemyDeath.mp3"),

    /**
     * Basic bullet hit sound.
     */
    BULLET_HIT("hit.mp3"),

    /**
     * Death player sound.
     */
    PLAYER_DEATH("playerDeath.mp3"),

    /**
     * Shoot sound.
     */
    SHOOT("hit.mp3");

    private final String path;

    AudioSfx(final String path) {
        this.path = path;
    }

    /**
     * Path builder. Generate a path for audio tracks.
     * 
     * @return path in string form
     */
    public String getPath() {
        final URL url = getClass().getClassLoader().getResource("sfx/" + path);
        return url.toString();
    }

}
