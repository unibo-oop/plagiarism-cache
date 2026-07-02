package it.unibo.oop.supermario.enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.utils.CharacterAnimation;

/**
 * This class create textures and sprites of Goomba.
 */
public class GoombaView extends CharacterAnimation {

    private final TextureRegion mashedGoomba;
    private final Animation walkAnimation;
    private float stateTime;
    private final GoombaImpl goomba;

    /**
     * Duration in seconds of the animation.
     */
    public static final float DURATION = 0.4f;

    /**
     * Constructor for GoombaView.
     * 
     * @param goomba Goomba's model.
     */
    public GoombaView(final GoombaImpl goomba) {
        this.goomba = goomba;
        this.b2body = goomba.getBody();
        final Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < 2; i++) {
            frames.add(new TextureRegion(atlas.findRegion("Goomba"), i * 16, 0, 16, 16));
        }
        walkAnimation = new Animation(DURATION, frames);
        frames.clear();
        mashedGoomba = new TextureRegion(atlas.findRegion("Goomba"), 32, 0, 16, 16);
        frames.clear();
        setBounds(getX(), getY(), 16 / GameManager.PPM, 16 / GameManager.PPM);
    }

    /**
     * Update Goomba's view every frame.
     *
     * @param dt delta time of the frame
     */
    public void update(final float dt) {
        stateTime += dt;
        TextureRegion region = new TextureRegion();
        if ((goomba.isToDestroy() && !goomba.isDestroyed()) || goomba.stateEnemies()) {
            if (!goomba.isSmashed() && !goomba.stateEnemies()) {
                region = walkAnimation.getKeyFrame(dt);
                region.flip(false, true);
                setRegion(region);
            } else if (goomba.stateEnemies()) {
                region = walkAnimation.getKeyFrame(dt);
                setRegion(region);
            } else {
                setRegion(mashedGoomba);
            }
            stateTime = 0;
        } else if (!goomba.isDestroyed()) {
            setRegion(walkAnimation.getKeyFrame(stateTime, true));
        }
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    @Override
    public final void draw(final Batch batch) {
        if (!goomba.isDestroyed() || stateTime < 1) {
            super.draw(batch);
        }
    }

    /** 
     * Get the body and position of Goomba.
     * 
     *  @return Body 
     */
    public Body getBody() {
        return this.b2body;
    }
}
