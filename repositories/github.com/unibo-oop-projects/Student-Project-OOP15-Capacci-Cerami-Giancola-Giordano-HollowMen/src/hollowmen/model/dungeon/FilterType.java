package hollowmen.model.dungeon;

/**
 * This enum represents the Category and the Mask used for filtering Collision
 * <br>
 * Short Description of how it works:<br>
 * Each {@link Body} can have one or more {@link Fixture} and each {@code Fixture}
 * has a {@link Filter} object. In Box2D {@link World}, category 
 * @author pigio
 *
 */
//TODO finish the Doc
public enum FilterType {
	
	GROUND(0x0001),
	HERO(0x0002),
	ENEMY(0x0004),
	INTERACTABLE(0x0008),
	HEROATTACK(0x0010),
	ENEMYATTACK(0x0020),
	WALL(0x0040),
	FLY(0x0080),
	FLYLINE(0x0100),
	JUMP(0x0200);
	
	private int value;
	
	
	private FilterType(int i) {
		value = i;
	}
	
	public int getValue() {
		return this.value;
	}
}
