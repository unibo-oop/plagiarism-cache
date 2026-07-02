package it.unibo.oop.supermario.enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import it.unibo.oop.supermario.enemies.KoopaImpl.State;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.utils.CharacterAnimation;

/**
 * This class create textures and sprites of Koopa.
 */
public class KoopaView extends CharacterAnimation {

    /** Speed in left direction. */
    public static final int KICK_LEFT = -2;
    /** Speed in right direction. */
    public static final int KICK_RIGHT = 2;
    /** Duration time in seconds of koopa in standing shell. */
    public static final int WAIT_TIME = 5;
    /** Duration time in seconds of the walk animation. */
    public static final float DURATION = 0.2f;
    /** Dimension of Koopa. */
    public static final float WIDTH = 16 / GameManager.PPM, 
            HEIGHT = 24 / GameManager.PPM;

    private final  Animation walkAnimation;
    private final TextureRegion shell;
    private float stateTime;
    private final KoopaImpl koopa;

    /**
     * Constructor for KoopaView.
     * 
     * @param koopa Koopa's model.
     */
    public KoopaView(final KoopaImpl koopa) {
        this.koopa = koopa;
        this.b2body = koopa.getBody();
        final Array<TextureRegion> frames;
        frames = new Array<TextureRegion>();
        for (int i = 0; i < 2; i++) {
            frames.add(new TextureRegion(atlas.findRegion("Koopa"), i * 16, 0, 16, 32));
        }
        walkAnimation = new Animation(DURATION, frames);
        frames.clear();
        shell = new TextureRegion(atlas.findRegion("Koopa"), 64, 0, 16, 32);
        frames.clear();
        setBounds(getX(), getY(), WIDTH, HEIGHT);
    }

    /**\
     * Get the region to set as sprite in a specific time.
     * 
     * @param dt the delta time
     * @return region of koopa
     */
    public TextureRegion getFrame(final float dt) {
        TextureRegion region;
        switch (koopa.getCurrentState()) {
        case MOVING_SHELL:
        case STANDING_SHELL:
            region = shell;
            break;
        case DYING:
            region = shell;
            region.flip(false, true);
            break;
        case STOPPED:
            region = walkAnimation.getKeyFrame(dt);
            break;
        case WALKING:
        default:
            region = walkAnimation.getKeyFrame(stateTime, true);
            break;
        }
        if (koopa.getVelocity().x > 0 && !region.isFlipX()) {
            region.flip(true, false);
        } else if (koopa.getVelocity().x < 0 && region.isFlipX()) {
            region.flip(true, false);
        }
        stateTime = koopa.getCurrentState().equals(koopa.getPreviousState()) ? stateTime + dt : 0;
        koopa.setPreviousState(koopa.getCurrentState());
        return region;
    }

    /**
     * Update Koopa's view every frame.
     *
     * @param dt delta time of the frame
     */
    public void update(final float dt) {
        setRegion(getFrame(dt));
        if ((koopa.isToDestroy() && !koopa.isDestroyed()) || koopa.stateEnemies()) {
            if (!koopa.isDestroyed() && !koopa.stateEnemies()) {
                koopa.setCurrentState(State.STANDING_SHELL);
            } else if (koopa.stateEnemies()) {
                koopa.setCurrentState(State.STOPPED);
            } else {
                koopa.setCurrentState(State.DYING);
            }
            koopa.setVelocity(0);
            stateTime = 0;
        } else if (!koopa.isDestroyed() &&  koopa.getCurrentState().equals(State.STANDING_SHELL) && stateTime > WAIT_TIME) {
            koopa.setCurrentState(State.WALKING);
            koopa.setVelocity(1);
        }
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - 8 / GameManager.PPM);
    }

    /**
     * Draw the design of Koopa in game.
     * 
     * @param batch 
     */
    public void draw(final Batch batch) {
        if (!koopa.isDestroyed() || stateTime < 1) {
            super.draw(batch);
        }
    }

    /** 
     * Get the body and position of Koopa.
     * 
     *  @return Body 
     */
    public Body getBody() {
        return this.b2body;
    }
}

