package it.unibo.oop.supermario.utils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import it.unibo.oop.supermario.gamemanager.GameManager;

/**
 * CharacterAnimation class.
 *
 */
public abstract class CharacterAnimation extends Sprite {
    /**
     * Texture region in file .atlas.
     */
    protected TextureRegion texture;
    /**
     * Body of object.
     */
    protected Body b2body;
    private Array<TextureRegion> frames;
    /**
     * Radius of shape.
     */
    protected float radius = 6 / GameManager.PPM;
    /**
     * Time spent in the state by animation.
     */
    protected float stateTimer;
    /**
     * New TextureAtlas.
     */
    protected TextureAtlas atlas = new TextureAtlas("actors.atlas");

    public CharacterAnimation() {
    }

    /**
     * CharacterAnimation constructor.
     * 
     * @param nameRegion name of the region in file .atlas
     */
    public CharacterAnimation(final String nameRegion) {
        setRegion(new TextureRegion(atlas.findRegion(nameRegion), 0, 0, 16, 16));
        setBounds(getX(), getY(), 16 / GameManager.PPM, 16 / GameManager.PPM);
    }

    /**
     * Sets objects animation.
     * 
     * @param nFrames       number of frames in the animation
     * @param animationName name of animation region in file .atlas
     * @param time          duartion of animation
     * @return new Animation
     */
    public Animation setAnimation(final int nFrames, final String animationName, final float time) {
        frames = new Array<>();
        for (int i = 0; i < nFrames; i++) {
            frames.add(new TextureRegion(atlas.findRegion(animationName), i * 16, 0, 16, 16));
        }
        return new Animation(time, frames);
    }

    /**
     * 
     * @param startFrame    frame where the animation starts in file .atlas
     * @param endFrame      frame where the animation ends in file .atlas
     * @param animationName name of animation region in file .atlas
     * @param height        height of image
     * @param width         width of image
     * @return new Animation
     */
    public Animation setAnimation(final int startFrame, final int endFrame, final String animationName,
            final int height, final int width) {
        final float duration = 0.1f;
        frames = new Array<>();
        for (int i = startFrame; i < endFrame; i++) {
            frames.add(new TextureRegion(atlas.findRegion(animationName), i * 16, 0, height, width));
        }
        return new Animation(duration, frames);
    }

    /**
     * Clean up all frames of the animation.
     */
    public void cleanFrames() {
        frames.clear();
    }

    /**
     * Releases all resources associated with this TextureAtlas instance.
     */
    public void dispose() {
        atlas.dispose();
    }

    /**
     * Draws object on the screen.
     * @param batch conteiner
     */
    public void draw(final Batch batch) {
        super.draw(batch);
    }

    /**
     * Abstract update for the extended classes.
     * 
     * @param dt delta time
     */
    protected abstract void update(float dt);
}