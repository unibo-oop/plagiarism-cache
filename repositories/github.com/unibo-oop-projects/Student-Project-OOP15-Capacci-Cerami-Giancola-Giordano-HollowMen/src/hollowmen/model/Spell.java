package hollowmen.model;

import java.util.Collection;

/**
 * This interface represents a Spell.
 * Spell is an object that can be activated when its cooldown reaches 0.
 * Everytime it's activated the cooldown is set to his limit.
 * Spells have Parameter.
 * @author pigio
 *
 */
public interface Spell extends InformationUser{

	/**
	 * This method will active this spell
	 * @throws IllegalStateException If cooldown isn't 0
	 */
	public void active() throws IllegalStateException;
	
	/**
	 * This method give a {@code LimitedCounter} which value is the cooldown current state and
	 * limit is the value that will be set every time the spell is activated
	 * @return {@link LimitedCounter}
	 */
	public LimitedCounter getCooldown();
	
	/**
	 * This method gives the {@code Parameter}s that define this {@code Spell} 
	 * @return {@code Collection} <{@link Parameter}>
	 */
	public Collection<Parameter> getParameter();
}
