package it.unibo.oop.bounce.ball;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.maps.MapObject;

import it.unibo.oop.bounce.screens.PlayScreen;

public abstract class Entity {
	
	private World world;
	private PlayScreen playscreen;

	private boolean isTaken;
	protected Body body;
	private MapObject object;
	private Vector2 placement;
	private int ballShapeType;
	private int shapeType;
	private int obstacleId;

	public Entity(final PlayScreen playscreen, final float x, final float y) {
		this.playscreen = playscreen;
		this.world = playscreen.getWorld();
		this.placement = new Vector2(x, y);
		// toDestroy = false;
		isTaken = false;
		setEntity(ballShapeType, ballShapeType);
	}

	public Entity(final PlayScreen playscreen, final float x, final float y, final MapObject object) {
		this.playscreen = playscreen;
		this.world = playscreen.getWorld();
		this.placement = new Vector2(x, y);
		this.object = object;
		// toDestroy = false;
		isTaken = false;
		setEntity(shapeType, obstacleId);
	}

	public abstract void setEntity(int shape, int obstacleId);

	public final PlayScreen getPlayScreen() {
		return playscreen;
	}

	public Body getBody() {
		return body;
	}

	public final World getWorld() {
		return world;
	}

	public final MapObject getObject() {
		return object;
	}

	public final Vector2 getPlacement() {
		return placement;
	}

	public final void destroyEntity() {
		world.destroyBody(body);
		taken();
	}

	public final void taken() {
		isTaken = true;
	}

	public final boolean getTaken() {
		return isTaken;
	}

}
