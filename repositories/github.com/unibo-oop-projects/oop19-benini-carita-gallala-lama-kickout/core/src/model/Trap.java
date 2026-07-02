package model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.physics.box2d.World;
import java.awt.Point;
/**
 * An entity subclass that defines and contains the characteristics of a trap
 */
public class Trap extends Entity {

    private boolean status;
    private Timer.Task restart;
    private Timer.Task duration;
    private Body body;
    private final float width;
    private final float height;
    private static final float RESTART_TIME = 10;
    private static final float TRAP_DURATION = 3;
    /**
     * Every time a Trap is created, two tasks are also created, restart() reactivates the trap
     * after it has been triggered, duration() defines the time the fighter is afflicted by the trap
     * @param point
     *          The starting point of the trap
     * @param width
     *          The trap's width
     * @param height
     *          The trap's height
     * @param type
     * 			The trap's BodyType
     * @param world
     *          The world in which to create the trap
     */
    public Trap(final Point point, final float width, final float height, final BodyType type, final World world) {
		super(new Vector2(point.x, point.y), width, height, type, world);
		this.width = width;
		this.height = height;
		this.newFixiture();
		this.getBody().setUserData(this);
		this.status = false;
		this.restart = new Task() {
		    @Override
			public void run() {
				status = true;
			}
		};
		Timer.schedule(this.restart, RESTART_TIME);

		this.duration = new Task() {
		    @Override
			public void run() {
				body.setGravityScale(1);
			}
		};
	}

	private void newFixiture() {
		this.getBody().getFixtureList().first().setSensor(true);
	}

	/**
     * @return the status of the trap
     */
	public boolean getStatus() {
		return this.status;
	}

	/**
     * Sets the status of the trap
     * @param status
     */
	public void setStatus(final boolean status) {
		Timer.schedule(restart, RESTART_TIME);
		this.status = status;
	}

	/**
     * Activates the trap and is applied to the fighter
     * @param body
     */
	public void trap1(final Body body) {

		if (this.status) {
			this.body = body;
			this.body.setGravityScale(2);
			Timer.schedule(this.duration, TRAP_DURATION);
			this.setStatus(false);
		}
	}

	/**
     * @return the trap's width
     */
	public float getWidth() {
		return this.width;
	}

	/**
     * @return the trap's height
     */
	public float getHeight() {
		return this.height;
	}
}
