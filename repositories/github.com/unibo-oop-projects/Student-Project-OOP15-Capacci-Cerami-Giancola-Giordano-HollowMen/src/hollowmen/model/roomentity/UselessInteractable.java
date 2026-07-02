package hollowmen.model.roomentity;

import org.jbox2d.dynamics.Filter;

import hollowmen.model.Information;
import hollowmen.model.Interactable;
import hollowmen.model.dungeon.FilterType;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

/**
 * This class extends {@link RoomEntityAbs} and implements {@link Interactable}<br>
 * This class holds the behavior of an {@code Interactable} object
 * @author pigio
 *
 */
public abstract class UselessInteractable extends RoomEntityAbs implements Interactable{

	private boolean canInteract = false;
	
	
	public UselessInteractable(Information name, Pair<Float,Float> size) {
		super(name, size);
	}
	
	/**
	 * {@inheritDoc Interactable}
	 */
	@Override
	public boolean isInteractAllowed() {
		return this.canInteract;
	}

	/**
	 * {@inheritDoc Interactable}
	 */
	@Override
	public void setInteractAllowed(boolean isAllowed) {
		this.canInteract = isAllowed;
	}

	/**
	 * {@inheritDoc Interactable}
	 */
	@Override
	public void interact() throws IllegalStateException {
		ExceptionThrower.checkIllegalState(this, d -> !d.isInteractAllowed());
	}

	/**
	 * This method gives the standard {@link Filter} for {@code Interactable} object
	 * @return {@link Filter} for {@code Interactable} object
	 */
	public Filter standardFilter(){
		return Box2DUtils.filterBuilder()
				.addCategory(FilterType.INTERACTABLE.getValue())
				.addMask(FilterType.GROUND.getValue())
				.addMask(FilterType.HERO.getValue())
				.build();
	}
}
