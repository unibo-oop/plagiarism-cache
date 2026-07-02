package hollowmen.model.roomentity;

import hollowmen.model.Actor;
import hollowmen.model.Actor.Direction;

/**
 * This class implements {@link MovePattern} and simply move the {@link Enemy} from right to left and viceversa
 * @author pigio
 *
 */
public class DumbMovePattern implements MovePattern{

	private EnemyAbs enemy;
	private String direction = Direction.RIGHT.toString();
	
	public DumbMovePattern(EnemyAbs en) {
		this.enemy = en;
	}

	/**
	 * {@inheritDoc MovePattern}
	 */
	@Override
	public String getDirection() {
		if(enemy.isHittingWall()) {
			if(direction.equals(Actor.Direction.RIGHT.toString()))
				direction = Actor.Direction.LEFT.toString();
			else {
				direction = Actor.Direction.RIGHT.toString();
			}
		}
		return direction;
	}
	
	
}
