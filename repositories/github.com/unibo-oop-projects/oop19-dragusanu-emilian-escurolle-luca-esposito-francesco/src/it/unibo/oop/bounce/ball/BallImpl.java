package it.unibo.oop.bounce.ball;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import it.unibo.oop.bounce.screens.Hud;
import it.unibo.oop.bounce.screens.PlayScreen;
import it.unibo.oop.bounce.game.Bounce;
import it.unibo.oop.bounce.manager.Manager;
import it.unibo.oop.bounce.manager.State;

public class BallImpl extends Entity implements Ball {
	
	private State currentState;
	private State size;


	private static final int X_POS = 128;
	private static final int Y_POS = 64;
	private float respawnX = X_POS;
	private float respawnY = Y_POS;
	private static final float SIZE = 110;
	private static final float SIZE2 = -10000;
	private static final float SIZE3 = -0.01f;
	private static final float SIZE4 = 0.01f;
	private static final float SMALLRADIUS = 11;
	private static final float BIGRADIUS = 17;

	private boolean rolling;
	private boolean jumping;
	private boolean getBig;
	private boolean getSmall;
	private final Hud hud;
	private Bounce game;
	private PlayScreen playscreen;


	public BallImpl(final PlayScreen playscreen, final float x, final float y, final Bounce game) {
		super(playscreen, x, y);
		rolling = false;
		getBig = false;
		getSmall = true;
		setJumping(false);
		currentState = State.STANDING;
		size = State.SMALL;
		hud = playscreen.getHud();
		this.setGame(game);
		this.setPlayscreen(playscreen);

	}

	@Override
	public final Body getBody() {
		return this.body;
	}

	@Override
	public final void setEntity(final int shapet, final int shapetype) {
		final BodyDef setEnt = new BodyDef();
		setEnt.position.set(X_POS, Y_POS);
		setEnt.type = BodyDef.BodyType.DynamicBody;
		body = getWorld().createBody(setEnt);
		final FixtureDef fixDef = new FixtureDef();
		final CircleShape shape = new CircleShape();
		shape.setRadius(SMALLRADIUS);

		fixDef.shape = shape;
		fixDef.filter.categoryBits = Manager.BOUNCE_ID;
		fixDef.filter.maskBits = Manager.BLOCK_ID | Manager.CHECKPOINT_ID | Manager.DEFLATER_ID | Manager.END_BLOCK_ID
				| Manager.FINISH_BLOCK_ID | Manager.LIFE_ID | Manager.PUMPER_ID | Manager.RING_ID | Manager.THORN_ID;

		body.createFixture(fixDef).setUserData(this);
	}

	public final void bigBall() {
		final Vector2 position = body.getPosition();
		final Vector2 movSpeed = body.getLinearVelocity();

		getWorld().destroyBody(body);

		this.size = State.BIG;

		final BodyDef setEnt = new BodyDef();
		setEnt.position.set(position);
		setEnt.type = BodyDef.BodyType.DynamicBody;
		setEnt.linearVelocity.set(movSpeed);
		body = getWorld().createBody(setEnt);

		final FixtureDef fixDef = new FixtureDef();
		final CircleShape shape = new CircleShape();
		shape.setRadius(BIGRADIUS);
		shape.setPosition(new Vector2(0, 0));
		fixDef.shape = shape;
		fixDef.filter.categoryBits = Manager.BOUNCE_ID;
		fixDef.filter.maskBits = Manager.BLOCK_ID | Manager.CHECKPOINT_ID | Manager.DEFLATER_ID | Manager.END_BLOCK_ID
				| Manager.FINISH_BLOCK_ID | Manager.LIFE_ID | Manager.PUMPER_ID | Manager.RING_ID | Manager.THORN_ID;

		body.createFixture(fixDef).setUserData(this);
	}

	public final void smallBall() {

		final Vector2 position = body.getPosition();
		final Vector2 movSpeed = body.getLinearVelocity();

		getWorld().destroyBody(body);

		this.size = State.SMALL;

		final BodyDef setEnt = new BodyDef();
		setEnt.position.set(position);
		setEnt.type = BodyDef.BodyType.DynamicBody;
		setEnt.linearVelocity.set(movSpeed);
		body = getWorld().createBody(setEnt);

		final FixtureDef fixDef = new FixtureDef();
		final CircleShape shape = new CircleShape();
		shape.setRadius(SMALLRADIUS);
		shape.setPosition(new Vector2(0, 0));
		fixDef.shape = shape;
		fixDef.filter.categoryBits = Manager.BOUNCE_ID;
		fixDef.filter.maskBits = Manager.BLOCK_ID | Manager.CHECKPOINT_ID | Manager.DEFLATER_ID | Manager.END_BLOCK_ID
				| Manager.FINISH_BLOCK_ID | Manager.LIFE_ID | Manager.PUMPER_ID | Manager.RING_ID | Manager.THORN_ID;

		body.createFixture(fixDef).setUserData(this);
	}

	@Override
	public final boolean isBig() {
		if (this.size == State.BIG) {
			return true;
		}
		return false;
	}
	
	@Override
	public final boolean isSmall() {
		if (this.size == State.SMALL) {
			return true;
		}
		return false;
	}

	@Override
	public final boolean isDead() {
		return false;
	}

	@Override
	public final void setBig() {
		bigBall();
	}

	@Override
	public final void setSmall() {
		smallBall();
	}

	@Override
	public final void big() {
		getBig = true;
		getSmall = false;

	}

	@Override
	public final void small() {
		getSmall = true;
		getBig = false;

	}

	@Override
	public final void jump() {
		if (this.currentState != State.JUMPING && this.currentState != State.FALLING
				&& this.currentState != State.DYING) {
			this.body.applyLinearImpulse(new Vector2(0, SIZE), this.body.getLocalCenter(), true);
		}
	}

	@Override
	public final void movement(final float speed) {
		this.rolling = true;
		if (speed > 0) {
			this.body.applyLinearImpulse(new Vector2(speed, 0), this.body.getWorldCenter(), true);
		} else {
			this.body.applyLinearImpulse(new Vector2(speed, 0), this.body.getWorldCenter(), true);
		}
	}

	@Override
	public final boolean isRolling() {
		return this.rolling;
	}

	@Override
	public final Vector2 getPosition() {
		return this.body.getPosition();
	}

	@Override
	public final State getState() {
		return this.currentState;
	}

	private void setState() {

		if (body.getLinearVelocity().y > 0) {
			this.currentState = State.JUMPING;
		} else if (body.getLinearVelocity().y < 0) {
			this.currentState = State.FALLING;
			this.body.applyLinearImpulse(new Vector2(0, SIZE2), this.body.getLocalCenter(), true);
		} else if (body.getLinearVelocity().x != 0) {
			this.currentState = State.ROLLING;
		} else {
			this.currentState = State.STANDING;
		}
	}

	@Override
	public final void update(final float dt) {
		if (body.getPosition().x <= SIZE3) {
			body.setTransform(body.getPosition().x + SIZE4, body.getPosition().y, 0);
			body.setLinearVelocity(new Vector2(0, 0));
		}
		if (getBig) {
			setBig();
		} else if (getSmall) {
			setSmall();
		}
		setState();
	}

	@Override
	public final void setRespawnX(final float x) {
		respawnX = x;
	}

	@Override
	public final void setRespawnY(final float y) {
		respawnY = y;

	}

	public final PlayScreen getPlayscreen() {
		return playscreen;
	}

	public final void setPlayscreen(final PlayScreen playscreen) {
		this.playscreen = playscreen;
	}

	public final Bounce getGame() {
		return game;
	}

	public final void setGame(final Bounce game) {
		this.game = game;
	}

	public final Hud getHud() {
		return hud;
	}

	public final boolean isJumping() {
		return jumping;
	}

	public final void setJumping(final boolean jumping) {
		this.jumping = jumping;
	}


}
