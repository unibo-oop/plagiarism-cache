package it.unibo.oop.bounce.ball;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import it.unibo.oop.bounce.animations.Animations;
import it.unibo.oop.bounce.manager.State;

public class BallViewImpl extends Animations implements BallView {
	
	private Body body;

	private static final int BOUNDS_WIDTH = 22;
	private static final int BOUNDS_HEIGHT = 22;
	private static final int BIG_BOUNDS_WIDTH = 34;
	private static final int BIG_BOUNDS_HEIGHT = 34;

	private State currentState;
	private State previousState;
	
	private static final int SIZE = 120;

	private TextureRegion ballStand;
	private Animation rollingBall;
	private float stateTimer;
	private Ball ball;
	private boolean rollingRight;

	public BallViewImpl() {
		rollingRight = false;
		ballStand = new TextureRegion(atlas.findRegion("Animation_light_ball-1"), 0, 0, SIZE, SIZE);
		rollingBall = setAnimation(0, 4, "Animation_light_ball-1", SIZE, SIZE);
		setBounds(0, 0, BOUNDS_WIDTH, BOUNDS_HEIGHT);

	}

	private TextureRegion setSprite(final float delta) {
		TextureRegion region = new TextureRegion();
		region = getFrame(delta);
		if ((body.getLinearVelocity().x < 0 || !rollingRight) && !region.isFlipX()) {
			region.flip(true, false);
			rollingRight = true;
		} else if (rollingRight && region.isFlipX()) {
			region.flip(true, false);
			rollingRight = false;
		}
		return region;
	}

	@Override
	public final void setBall(final Ball ball) {
		this.ball = ball;
		this.body = ball.getBody();

	}

	@Override
	public final float getStateTimer() {
		return stateTimer;
	}

	@Override
	public final void update(final float dt) {
		rollingRight = ball.isRolling();
		setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
		setRegion(setSprite(dt));

	}


	private TextureRegion getFrame(final float dt) {
		previousState = currentState;
		currentState = ball.getState(); // ballimplement
		stateTimer = currentState == previousState ? stateTimer + dt : 0;

		TextureRegion region = new TextureRegion();
		switch (currentState) {
		case ROLLING:
			region = rollingBall.getKeyFrame(stateTimer, true);
			break;
//		 case GROWING: //L'immagine della pallina si setta grande, la funzione che dice che può crescere diventa false
//			 setBounds(0,0,BIG_BOUNDS_WIDTH,BIG_BOUNDS_HEIGHT);
//			 ball.setGROWING_UP(false);
		case FALLING:
		case STANDING:
		default:
			region = ballStand;
			break;
		}
		return region;
	}


	@Override
	public final void draw(final Batch batch) {
		super.draw(batch);

	}

}
