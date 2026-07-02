package boxhead.model.entities.utils;

import javafx.geometry.BoundingBox;

public class Collision {
	
	private Collision() {
	}
	
	
	/**
	 * Return true if two bounding boxes are colliding using intersect method
	 * @param BoundingBox firstBB first entity's bounding box
	 * @param BoundingBox secondBB second entity's bounding box 
	 * @return boolean true if the two Bounding Boxes are intersecting
	 */
	public static boolean isColliding(final BoundingBox firstBB, final BoundingBox secondBB) {
		return firstBB.intersects(secondBB);
	}
}
