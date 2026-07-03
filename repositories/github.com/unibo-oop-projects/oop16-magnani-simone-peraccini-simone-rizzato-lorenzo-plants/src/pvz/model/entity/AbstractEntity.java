package pvz.model.entity;

import javafx.geometry.Rectangle2D;
import pvz.utility.Pair;

/**
 * Defines the base implementation for all entities. The update() method is the
 * only piece of code that needs to be defined separately in every subclass;
 * others are <i>template methods</i>.
 */
public abstract class AbstractEntity implements Entity {

	protected double xPos;
	protected double yPos;
	protected boolean toBeRemoved;
	protected Rectangle2D box;

	/**
	 * Constructor for an <code>AbstractEntity</code>.<br>
	 * Position and dimensions need to be specified for the entity to be
	 * correctly placed in the world.
	 * 
	 * @param position
	 *            entity position
	 * @param width
	 *            entity width
	 * @param height
	 *            entity height
	 */
	public AbstractEntity(Pair<Double, Double> position, double width, double height) {
		this.xPos = position.getX();
		this.yPos = position.getY();
		this.toBeRemoved = false;
		this.box = new Rectangle2D(this.xPos - width / 2, this.yPos - height / 2, width, height);
	}

	@Override
	public double getX() {
		return this.xPos;
	}

	@Override
	public double getY() {
		return this.yPos;
	}

	@Override
	public abstract void update();

	@Override
	public boolean collidesWith(Rectangle2D box) {
		return this.box.intersects(box) || this.box.contains(box);
	}

	@Override
	public boolean shouldBeRemoved() {
		return this.toBeRemoved;
	}

	/**
	 * Marks this entity as <i>dead</i>, causing the model to remove it at the
	 * next update cycle.
	 */
	protected void remove() {
		this.toBeRemoved = true;
	}

}
