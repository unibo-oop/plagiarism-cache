package it.unibo.oop.bounce.ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;

public class MovementImpl implements Movement {

	private final Ball ball;
	private final BallView ballView;
	private boolean blockBall;
	private static final float ROLLING = 100f;

	public MovementImpl(final Ball ball, final BallView ballView) {
		this.ball = ball;
		this.ballView = ballView;
		this.ballView.setBall(this.ball);
		this.blockBall = false;
	}

	@Override
	public final Body getBody() {
		return this.ball.getBody();
	}

	@Override
	public final void update(final float dt) {
		handleInput();
		ball.update(dt);
		ballView.update(dt);

	}

	@Override
	public final void draw(final Batch batch) {
		ballView.draw(batch);
	}

	@Override
	public final void setBlockBall() {
		this.blockBall = true;
	}

	private void handleInput() {
		if (!ball.isDead() && !blockBall) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
				ball.jump();

			}
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				ball.movement(ROLLING);

			}
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				ball.movement(-ROLLING);
			}
		}
	}

	final boolean isLocked() {
		return this.blockBall;
	}

}
