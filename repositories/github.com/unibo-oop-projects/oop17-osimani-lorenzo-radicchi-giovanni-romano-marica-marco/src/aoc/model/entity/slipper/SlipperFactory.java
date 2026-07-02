package aoc.model.entity.slipper;

import aoc.model.entity.EntityInterface;
import aoc.utilities.Vector;

/**
 * This class implements a slipper factory.
 * It mainly creates a new slipper from the informations that receives from the caller.
 */
public class SlipperFactory implements SlipperFactoryInterface {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EntityInterface createSlipper(final Projectile type, final Vector position) {
		return new Slipper(position, type);
	}

}
