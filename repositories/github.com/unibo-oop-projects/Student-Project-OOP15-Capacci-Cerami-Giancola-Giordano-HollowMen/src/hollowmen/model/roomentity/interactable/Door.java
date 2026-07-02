package hollowmen.model.roomentity.interactable;

import java.util.Collection;

import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Filter;
import org.jbox2d.dynamics.FixtureDef;

import hollowmen.model.dungeon.DungeonSingleton;
import hollowmen.model.dungeon.FilterType;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.roomentity.UselessInteractable;
import hollowmen.model.utils.Constants;
import hollowmen.model.utils.Box2DUtils;

/**
 * This class extends {@link UselessInteractable}<br>
 * This class Override {@code isInteractAllowed()} so that it returns {@code false} until every {@code Enemy}
 * inside the current {@code Room} have been slaying, then return {@code super.isInteractAllowed()}
 * <br>
 * This class Override {@code interact()} method and calls Dungeon's {@code changeRoom()}
 * using his doorNumber as input
 * 
 * 
 * @author pigio
 *
 */
public class Door extends UselessInteractable{

	private int roomNumber;
	
	public Door(String name, int doorNumber) {
		super(new InfoImpl(name), Constants.DOOR_SIZE);
		this.roomNumber = doorNumber;
	}
	
	/**
	 * @return {@code false} until every {@code Enemy}
	 * inside the current {@code Room} have been slaying, then return {@code super.isInteractAllowed()}
	 */
	@Override
	public boolean isInteractAllowed() {
		return DungeonSingleton.getInstance().getCurrentRoom().getEnemies().isEmpty() ? 
				super.isInteractAllowed() : false;
	}
	
	/**
	 * This method will change the current {@code Room} based on this doorNumber
	 */
	@Override
	public void interact() throws IllegalStateException {
		super.interact();
		DungeonSingleton.getInstance().changeRoom(roomNumber);		
	}

	/**
	 * This method gives this doorNumber
	 * @return {@code int} this doorNumber
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	@Override
	public BodyDef defBody() {
		return Box2DUtils.bodyDefBuilder().type(BodyType.STATIC).build();
	}

	@Override
	public Collection<FixtureDef> defFixture() {
		Filter filter = Box2DUtils.filterBuilder()
							.addCategory(FilterType.INTERACTABLE.getValue())
							.addMask(FilterType.GROUND.getValue())
							.addMask(FilterType.HERO.getValue())
							.build();
		return this.generateRectangleFix(filter, true);
	}

}
