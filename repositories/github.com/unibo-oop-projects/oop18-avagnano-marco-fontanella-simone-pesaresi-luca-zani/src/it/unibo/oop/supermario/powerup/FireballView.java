package it.unibo.oop.supermario.powerup;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.utils.CharacterAnimation;

/**
 * This class creates the textures and sprites of fireballs.
 */
public class FireballView extends CharacterAnimation {
    private final Animation firing;
    private final TextureRegion hitting;
    private FireballModel fireball;
    private float stateTime;
    private Body b2body;
    private static final String FIREBALL = "FireBall";

    /**
     * Constructor for Fireball View.
     */
    public FireballView() {
        // firing animation
        final Array<TextureRegion> keyFrames = new Array<TextureRegion>();
        keyFrames.add(new TextureRegion(atlas.findRegion(FIREBALL), 0, 0, 8, 8));
        keyFrames.add(new TextureRegion(atlas.findRegion(FIREBALL), 8, 0, 8, 8));
        keyFrames.add(new TextureRegion(atlas.findRegion(FIREBALL), 0, 8, 8, 8));
        keyFrames.add(new TextureRegion(atlas.findRegion(FIREBALL), 8, 8, 8, 8));
        firing = new Animation(0.1f, keyFrames);

        // hitting
        hitting = new TextureRegion(atlas.findRegion(FIREBALL), 20, 4, 8, 8);

        setRegion(firing.getKeyFrame(0, true));
        setBounds(getX(), getY(), 8 / GameManager.PPM, 8 / GameManager.PPM);
    }

    /**
     * Every frame check if a fireball is fired and displays it.
     * 
     * @param dt delta time between the previous and next frames
     */
    public void render(final float dt) {
        update(dt);
        if (!this.fireball.isHit()) {
            setRegion(firing.getKeyFrame(stateTime, true));
            setFlip(true, false);
        } else {
            if (!this.fireball.isToDestroy()) {
                setRegion(hitting);
            }
        }
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    /**
     * Every frame update the data of Fireball.
     * 
     * @param dt delta time between the previous and next frames
     */
    public void update(final float dt) {
        this.fireball.update(dt);
        stateTime += dt;
    }

    /**
     * Set the fireball to display.
     * 
     * @param model Fireball's model
     */
    public void setFireball(final FireballModel model) {
        this.fireball = model;
        this.b2body = this.fireball.getBody();
    }

}
