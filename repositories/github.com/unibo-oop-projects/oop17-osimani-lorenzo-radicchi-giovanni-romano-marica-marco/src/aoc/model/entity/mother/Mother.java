package aoc.model.entity.mother;

import aoc.utilities.Direction;
import aoc.utilities.ObjectObserver;
import aoc.utilities.ObservedObject;
import aoc.utilities.Vector;
import aoc.model.entity.slipper.Projectile;
import aoc.model.entity.slipper.SlipperFactory;
import aoc.model.entity.slipper.SlipperFactoryInterface;
import aoc.model.WorldConstants;
import aoc.model.entity.Entity;
import aoc.model.entity.EntityInterface;

/**
 * This class implements the mother, which is the character controlled by the player.
 */
public class Mother extends Entity implements MotherInterface, ObservedObject {
	
	/**
	 * This field represents the type of projectile shot by the mother during the game.
	 */
	private Projectile shoots;
	
	/**
	 * This field contains the reference to the slipper factory which creates 
	 * the slippers shot by the mother during the game.
	 */
	private SlipperFactoryInterface slipperFactory;
	
	/**
	 * This field contains the reference to the observer of the mother.
	 */
	private ObjectObserver observer;
	

	public Mother(final Vector position, final Projectile projectile) {
		super(position, new Vector(0, 0), "MOTHER");
		this.shoots = projectile;
		this.slipperFactory = new SlipperFactory();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EntityInterface attack() {
		EntityInterface thrown = this.slipperFactory.createSlipper(shoots, new Vector (this.getPosition().getX(), this.getPosition().getY()));
		this.observer.notify(thrown);
		return thrown;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void move(final Direction direction) {
		this.setPosition(this.getPosition().increaseY(direction.getDir()*WorldConstants.CELL_WIDTH));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void attach(ObjectObserver observer) {
		this.observer = observer;
	}
	
}
