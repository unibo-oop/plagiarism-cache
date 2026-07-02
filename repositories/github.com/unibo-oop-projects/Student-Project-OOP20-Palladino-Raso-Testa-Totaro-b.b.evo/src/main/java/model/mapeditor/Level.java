package model.mapeditor;

import java.io.Serializable;
import java.util.Set;

import model.entities.Brick;
import resource.routing.BackGround;
import resource.routing.BallTexture;
import resource.routing.PaddleTexture;
import resource.routing.PersonalSounds;

/**
 * Provides to create a simple level.
 *
 */
public class Level implements Serializable {

    private static final long serialVersionUID = -3269378075735300995L;
    private final Set<Brick> bricks;
    private final String levelName;
    private final PersonalSounds music; 
    private final BackGround background;
    private final BallTexture ball;
    private final PaddleTexture paddle;

    /**
     * Set all required variables. 
     * @param bricks set of brick to put on board
     * @param levelName name of the level
     * @param music that will play when game start
     * @param background set on board
     * @param ball texture set on board
     * @param paddle texture set on board
     */
    public Level(final Set<Brick> bricks, final String levelName, final PersonalSounds music, final BackGround background,
                                                                  final BallTexture ball, final PaddleTexture paddle) {
        this.bricks = bricks;
        this.levelName = levelName;
        this.music = music;
        this.background = background;
        this.ball = ball;
        this.paddle = paddle;
    }

    /**
     * 
     * @return the amount of bricks in the map
     */
    public Set<Brick> getBricks() {
        return this.bricks;
    }

    /**
     * 
     * @return the name of the level
     */
    public String getLevelName() {
        return this.levelName;
    }

    /**
     * 
     * @return the music
     */
     public PersonalSounds getMusic() {
         return music;
    }

    /**
     * @return the background
     */
    public BackGround getBackground() {
        return background;
    }

    /**
     * @return ball texture
     */
    public BallTexture getBallTexture() {
        return ball;
    }

    /**
     * @return paddle texture
     */
    public PaddleTexture getPaddleTexture() {
        return paddle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((levelName == null) ? 0 : levelName.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
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
        final Level other = (Level) obj;
        if (levelName == null) {
            if (other.levelName != null) {
                return false;
            }
        } else if (!levelName.equals(other.levelName)) {
            return false;
        }
        return true;
    }
}
