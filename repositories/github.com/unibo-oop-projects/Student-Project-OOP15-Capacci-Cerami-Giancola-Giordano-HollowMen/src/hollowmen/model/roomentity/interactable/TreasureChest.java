package hollowmen.model.roomentity.interactable;

import java.util.Collection;

import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import hollowmen.model.Lootable;
import hollowmen.model.dungeon.DungeonSingleton;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.roomentity.UselessInteractable;
import hollowmen.model.utils.Constants;
import hollowmen.model.utils.Algorithms;
import hollowmen.model.utils.Box2DUtils;

/**
 * This class extends {@link UselessInteractable}<br>
 * 
 * @author pigio
 *
 */
public class TreasureChest extends UselessInteractable{
	
	private Lootable loot;
	
	//TODO improve the choose of the Item
	public TreasureChest() {
		super(new InfoImpl(RoomEntityName.TREASURE.toString()), Constants.TREASURE_SIZE);
		this.loot = Algorithms.treasureLoot();
	}

	/**
	 * This method let the {@link Hero} pick this {@code TreasureChest}'s {@link Lootable} object
	 */
	@Override
	public void interact() throws IllegalStateException {
		super.interact();
		DungeonSingleton.getInstance().getHero().pick(loot);
		this.dispose();
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((loot == null) ? 0 : loot.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreasureChest other = (TreasureChest) obj;
		if (loot == null) {
			if (other.loot != null)
				return false;
		} else if (!loot.equals(other.loot))
			return false;
		return true;
	}

	@Override
	public BodyDef defBody() {
		return Box2DUtils.bodyDefBuilder().type(BodyType.STATIC).build();
	}

	@Override
	public Collection<FixtureDef> defFixture() {
		return this.generateRectangleFix(this.standardFilter(), true);
	}

}
