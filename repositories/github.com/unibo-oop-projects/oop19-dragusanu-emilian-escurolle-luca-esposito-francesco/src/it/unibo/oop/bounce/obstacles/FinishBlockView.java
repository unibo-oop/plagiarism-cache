package it.unibo.oop.bounce.obstacles;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;

import it.unibo.oop.bounce.animations.Animations;

public class FinishBlockView extends Animations {
	
	private final Animation animation;
	private static final int SIZE = 11;

	public FinishBlockView() {
		super("portal_sheet@3x-1.png");
		this.animation = setAnimation(0, SIZE, "portal_sheet@3x-1.png (1)", 64, 64);
		super.cleanFrames();
	}

	@Override
	protected final void update(final float dt) {
		stateTimer += dt;
		setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);

	}

	public final void render(final float dt) {
		update(dt);
		setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);

	}

	public final void draw(final Batch batch) {
		this.draw(batch);
	}

	public final Animation getAnimation() {
		return animation;
	}

}
