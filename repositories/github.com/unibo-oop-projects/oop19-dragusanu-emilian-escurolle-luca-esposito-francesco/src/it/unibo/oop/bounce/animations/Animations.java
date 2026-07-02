package it.unibo.oop.bounce.animations;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

public abstract class Animations extends Sprite {
	
	/**
	 * Con Texture atlas carichiamo il file atlas da dove prenderemo le texture.
	 */
	protected TextureAtlas atlas = new TextureAtlas("sprites/Animation_light_Ball.atlas");

	/**
	 * il body dell'oggetto.
	 */
	protected Body body;
	
	/**
	 *  ballstand è la texture del file .atlas.
	 */
	protected TextureRegion ballStand;
	/**
	 * frames contiene l'array di immagini  per l'animazione.
	 */
	protected Array<TextureRegion> frames;
	
	/**
	 * il tempo in cui la pallina è nello stato di animations
	 */
	protected float stateTimer;
	private static final int BOUNDS_WIDTH = 90;
	private static final int BOUNDS_HEIGHT = 90;
	private static final int WIDTH = 120;
	private static final int HEIGHT = 120;
	private static final int PIXEL = 120;

	public Animations() {
	}

	public Animations(final String nameRegion) {
		setRegion(new TextureRegion(atlas.findRegion(nameRegion), 0, 0, WIDTH, HEIGHT));
		setBounds(0, 0, BOUNDS_WIDTH, BOUNDS_HEIGHT);
	}

	public final Animation setAnimation(final int startFrame, final int endFrame, final String animationName,
            final int height, final int width) {
        final float duration = 0.1f;
        frames = new Array<>();
        for (int i = startFrame; i < endFrame; i++) {
            frames.add(new TextureRegion(atlas.findRegion(animationName), i * PIXEL, 0, height, width));
        }
        return new Animation(duration, frames);
	}
	
	public final void cleanFrames() {
		frames.clear();
	}
	
	public final void dispose() {
		atlas.dispose();
	}
	
	public void draw(final Batch batch) {
		super.draw(batch);
	}
	
	protected abstract void update(float dt);
	
	
}
